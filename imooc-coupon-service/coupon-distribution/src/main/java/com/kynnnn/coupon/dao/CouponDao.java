package com.kynnnn.coupon.dao;

import com.kynnnn.coupon.constant.CouponStatus;
import com.kynnnn.coupon.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 优惠券 Dao接口定义
 * @author Zhudiwei
 */
public interface CouponDao extends JpaRepository<Coupon,Integer> {

    /**
     * 根据 userId+状态查找优惠券记录
     * @param userId
     * @param status
     * @return
     */
    List<Coupon> findAllByUserIdaAndStatus(Long userId, CouponStatus status);

}
