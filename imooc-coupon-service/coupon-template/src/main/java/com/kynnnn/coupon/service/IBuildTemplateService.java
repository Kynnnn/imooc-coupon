package com.kynnnn.coupon.service;

import com.kynnnn.coupon.entity.CouponTemplate;
import com.kynnnn.coupon.exception.CouponException;
import com.kynnnn.coupon.vo.TemplateRequest;

/**
 * 构建优惠券模板接口定义
 *
 * @author Zhudiwei
 */
public interface IBuildTemplateService {

    /**
     * 创建优惠券模板
     *
     * @param request {@link TemplateRequest} 模板信息请求对象
     * @return {@link CouponTemplate} 优惠券模板实体
     * @throws CouponException
     */
    CouponTemplate buildTemplate(TemplateRequest request) throws CouponException;
}
