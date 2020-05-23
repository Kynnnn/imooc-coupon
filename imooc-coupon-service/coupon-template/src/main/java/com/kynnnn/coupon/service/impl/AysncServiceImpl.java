package com.kynnnn.coupon.service.impl;

import com.google.common.base.Stopwatch;
import com.kynnnn.coupon.constant.Constant;
import com.kynnnn.coupon.dao.CouponTemplateDao;
import com.kynnnn.coupon.entity.CouponTemplate;
import com.kynnnn.coupon.service.IAsyncService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author Zhudiwei
 */
@Slf4j
@Service
@SuppressWarnings("all")
public class AysncServiceImpl implements IAsyncService {

    @Autowired
    private CouponTemplateDao templateDao;

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 根据模板异步地创建优惠券码
     *
     * @param template {@link CouponTemplate} 优惠券模板实体
     */
    @Async("getAsyncExecutor")
    @Override
    public void asyncConstructCouponByTemplate(CouponTemplate template) {
        Stopwatch watch = Stopwatch.createStarted();
        Set<String> couponCodes = buildCouponCode(template);
        String redisKey = String.format("%s%s",
                Constant.RedisPrefix.COUPON_TEMPLATE, template.getId().toString());
        log.info("Push CouponCode To Redis:{}",
                redisTemplate.opsForList().rightPushAll(redisKey, couponCodes));
        template.setAvailable(true);
        templateDao.save(template);

        watch.stop();
        log.info("Contruct CouponCode By Template Cost: {}ms",
                watch.elapsed(TimeUnit.MILLISECONDS));
        //TODO 发短信或邮件通知优惠券模板已经可用
        log.info("CouponTemplate({}) Is Available!", template.getId());
    }

    /**
     * 构造优惠券码
     * 优惠券码（对于每一张优惠券）共18位
     * 前4位：产品线（1位）+ 类型（3位）
     * 中6位：日期的随机（200523）
     * 后8位：0~9的随机数
     *
     * @param template {@link CouponTemplate}
     * @return Set<String> 与 template.count 相同个数的优惠券码
     */
    private Set<String> buildCouponCode(CouponTemplate template) {
        Stopwatch watch = Stopwatch.createStarted();
        Set<String> result = new HashSet<>(template.getCount());

        //前4位
        String prefix4 = template.getProductLine().getCode().toString() +
                template.getCategory().getCode();

        String date = new SimpleDateFormat("yyMMdd").format(template.getCreateTime());

        for (int i = 0; i < template.getCount(); i++) {
            result.add(prefix4 + buildCouponCodeSuffix14(date));
        }

        while (result.size() < template.getCount()) {
            result.add(prefix4 + buildCouponCodeSuffix14(date));
        }

        assert result.size() == template.getCount();
        watch.stop();
        log.info("Build Coupon Code Cost: {}ms", watch.elapsed(TimeUnit.MILLISECONDS));

        return result;
    }

    /**
     * 构造优惠券码后14位
     *
     * @param date 创建优惠券的日期
     * @return 14位优惠券码
     */
    private String buildCouponCodeSuffix14(String date) {

        char[] bases = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9'};
        //中间六位
        List<Character> chars = date.chars()
                .mapToObj(e -> (char) e)
                .collect(Collectors.toList());
        //洗牌
        Collections.shuffle(chars);
        // 洗完牌后还是char类型的，转换为字符串类型，然后连接起来
        String mid6 = chars.stream().map(Objects::toString).collect(Collectors.joining());
        //后8位
        String suffix8 = RandomStringUtils.random(1, bases) + RandomStringUtils.randomNumeric(7);

        return mid6 + suffix8;
    }
}
