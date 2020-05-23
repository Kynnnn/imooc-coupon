package com.kynnnn.coupon.service.impl;

import com.kynnnn.coupon.dao.CouponTemplateDao;
import com.kynnnn.coupon.entity.CouponTemplate;
import com.kynnnn.coupon.exception.CouponException;
import com.kynnnn.coupon.service.ITemplateBaseService;
import com.kynnnn.coupon.vo.CouponTemplateSDK;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 优惠券模板基础服务接口实现
 *
 * @author Zhudiwei
 */
@Service
@Slf4j
public class TemplateBaseServiceImpl implements ITemplateBaseService {

    private final CouponTemplateDao templateDao;

    public TemplateBaseServiceImpl(CouponTemplateDao templateDao) {
        this.templateDao = templateDao;
    }

    /**
     * 根据优惠券模板id获取优惠券模板信息
     *
     * @param id 模板id
     * @return {@link CouponTemplate}
     * @throws CouponException
     */
    @Override
    public CouponTemplate buildTemplateInfo(Integer id) throws CouponException {

        Optional<CouponTemplate> couponTemplate = templateDao.findById(id);
        if (!couponTemplate.isPresent()) {
            throw new CouponException("Template Is Not Exists: " + id);
        }

        return couponTemplate.get();
    }

    /**
     * 查找所有可用的优惠券模板
     *
     * @return {@link CouponTemplateSDK}s
     */
    @Override
    public List<CouponTemplateSDK> findAllUsableTemplate() {

        List<CouponTemplate> couponTemplates =
                templateDao.findAllByAvailableAndExpired(true, false);

        return couponTemplates.stream()
                .map(this::template2TemplateSDK)
                .collect(Collectors.toList());
    }

    /**
     * 获取模板 ids 到 CouponTemplateSDK 的映射
     *
     * @param ids 模板ids
     * @return Map<Key ： 模板id, value ： CouponTemplateSDK>
     */
    @Override
    public Map<Integer, CouponTemplateSDK> findIds2TemplateSDK(Collection<Integer> ids) {

        List<CouponTemplate> couponTemplates = templateDao.findAllById(ids);

        return couponTemplates.stream()
                .map(this::template2TemplateSDK)
                .collect(Collectors.toMap(CouponTemplateSDK::getId, Function.identity()));
    }

    /**
     * 将 CouponTemplate 转换为 CouponTemplateSDK
     *
     * @param template
     * @return
     */
    private CouponTemplateSDK template2TemplateSDK(CouponTemplate template) {

        return new CouponTemplateSDK(
                template.getId(),
                template.getName(),
                template.getLogo(),
                template.getDesc(),
                template.getCategory().getCode(),
                template.getProductLine().getCode(),
                //未拼装好
                template.getKey(),
                template.getTarget().getCode(),
                template.getRule()
        );

    }
}
