package com.kynnnn.coupon.service;

import com.kynnnn.coupon.entity.CouponTemplate;

/**
 * 异步服务接口定义
 *
 * @author Zhudiwei
 * @version 1.0
 * @date 2020/4/21 14:43
 */
public interface IAsyncService {

    /**
     * 根据模板异步地创建优惠券码
     *
     * @param template {@link CouponTemplate} 优惠券模板实体
     */
    void asyncConstructCouponByTemplate(CouponTemplate template);
}
