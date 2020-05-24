package com.kynnnn.coupon.schedule;

import com.kynnnn.coupon.dao.CouponTemplateDao;
import com.kynnnn.coupon.entity.CouponTemplate;
import com.kynnnn.coupon.vo.TemplateRule;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 定时清理已过期的优惠券模板
 *
 * @author Zhudiwei
 */
@Slf4j
@Component
public class ScheduleTask {

    private final CouponTemplateDao templateDao;

    public ScheduleTask(CouponTemplateDao templateDao) {
        this.templateDao = templateDao;
    }

    /**
     * 下线已过期的优惠券模板
     */
    @Scheduled(fixedRate = 60 * 60 * 1000)
    private void offlineCouponTemplate() {

        log.info("Start to Expire CouponTemplate");
        List<CouponTemplate> templates = templateDao.findAllByExpired(false);
        if (CollectionUtils.isEmpty(templates)) {
            log.info("Done to Expire CouponTemplate.");
            return;
        }
        Date cur = new Date();
        List<CouponTemplate> expiredTemplates = new ArrayList<>(templates.size());
        templates.forEach(t -> {
            TemplateRule rule = t.getRule();
            if (rule.getExpiration().getDeadLine()<cur.getTime()) {
                t.setExpired(true);
                expiredTemplates.add(t);
            }
        });

        if (CollectionUtils.isNotEmpty(expiredTemplates)) {
            log.info("Expired CouponTemplate Num: {}",templateDao.saveAll(expiredTemplates));
        }
        
        log.info("Done to Expire CouponTemplate.");
    }
}
