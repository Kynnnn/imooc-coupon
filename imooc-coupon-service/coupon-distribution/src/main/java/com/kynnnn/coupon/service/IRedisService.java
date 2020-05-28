package com.kynnnn.coupon.service;

import com.kynnnn.coupon.entity.Coupon;
import com.kynnnn.coupon.exception.CouponException;

import java.util.List;

/**
 * Redis 相关的操作服务接口定义
 * 功能：
 * 1.用户的三个状态优惠券 Cache相关操作
 * 2.优惠券模板生成的优惠券码 Cache 操作
 *
 * @author Zhudiwei
 */
public interface IRedisService {

    /**
     * 根据 userId 和状态找到缓存的优惠券列表数据
     *
     * @param userId 用户id
     * @param status 优惠券状态 {@link com.kynnnn.coupon.constant.CouponStatus}
     * @return {@link Coupon} 注意，能返回null，表示从来没有过记录
     */
    List<Coupon> getCachedCoupons(Long userId, Integer status);

    /**
     * 保存空的优惠券列表到缓存中
     * 防止缓存穿透
     *
     * @param userId 用户id
     * @param status 优惠券状态列表
     */
    void saveEmptyCouponListToCache(Long userId, List<Integer> status);

    /**
     * 尝试从Cache中获取优惠券码
     *
     * @param templateId 优惠券模板主键
     * @return 优惠券码
     */
    String tryToAcquireCouponCodeFromCache(Integer templateId);

    /**
     * 将优惠券保存到Cache中
     *
     * @param userId  用户id
     * @param coupons {@link Coupon}s
     * @param status  优惠券状态
     * @return 保存成功的个数
     * @throws CouponException
     */
    Integer addCouponToCache(Long userId, List<Coupon> coupons, Integer status) throws CouponException;
}
