package com.kynnnn.coupon.service;

import com.kynnnn.coupon.entity.Coupon;
import com.kynnnn.coupon.exception.CouponException;
import com.kynnnn.coupon.vo.AcquireTemplateRequest;
import com.kynnnn.coupon.vo.CouponTemplateSDK;
import com.kynnnn.coupon.vo.SettlementInfo;

import java.util.List;

/**
 * 用户服务相关的接口定义
 * 1.用户三类状态优惠券信息展示服务
 * 2.查看用户当前可以领取的优惠券模板
 * 3.用户领取优惠券服务
 * 4.用户消费优惠券服务 - coupon-settlement 微服务
 *
 * @author Zhudiwei
 */
public interface IUserService {

    /**
     * 根据 用户id 和状态查询优惠券记录
     *
     * @param userId 用户id
     * @param status 优惠券状态
     * @return {@link Coupon}s
     * @throws CouponException
     */
    List<Coupon> findCouponByStatus(Long userId, Integer status) throws CouponException;

    /**
     * 根据用户id查找当前可以领取的优惠券模板
     *
     * @param userId 用户 id
     * @return {@link CouponTemplateSDK}
     * @throws CouponException
     */
    List<CouponTemplateSDK> findAvailableTemplate(Long userId) throws CouponException;

    /**
     * 用户领取优惠券
     *
     * @param request {@link AcquireTemplateRequest}
     * @return {@link Coupon}
     * @throws CouponException
     */
    Coupon acquireTemplate(AcquireTemplateRequest request) throws CouponException;

    /**
     * 核销优惠券
     *
     * @param info {@link SettlementInfo}
     * @return {@link SettlementInfo}
     * @throws CouponException
     */
    SettlementInfo settlement(SettlementInfo info) throws CouponException;
}
