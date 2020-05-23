package com.kynnnn.coupon.service;

import com.kynnnn.coupon.entity.CouponTemplate;
import com.kynnnn.coupon.exception.CouponException;
import com.kynnnn.coupon.vo.CouponTemplateSDK;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 优惠券模板服务基础（view delete）服务定义
 * @author Zhudiwei
 */
@SuppressWarnings("all")
public interface ITemplateBaseService {

    /**
     * 根据优惠券模板id获取优惠券模板信息
     * @param id 模板id
     * @return {@link CouponTemplate}
     * @throws CouponException
     */
    CouponTemplate buildTemplateInfo(Integer id) throws CouponException;

    /**
     * 查找所有可用的优惠券模板
     *
     * @return {@link CouponTemplateSDK}s
     */
    List<CouponTemplateSDK> findAllUsableTemplate();

    /**
     * 获取模板 ids 到 CouponTemplateSDK 的映射
     *
     * @param ids 模板ids
     * @return Map<Key：模板id,value：CouponTemplateSDK>
     */
    Map<Integer,CouponTemplateSDK> findIds2TemplateSDK(Collection<Integer> ids);


}
