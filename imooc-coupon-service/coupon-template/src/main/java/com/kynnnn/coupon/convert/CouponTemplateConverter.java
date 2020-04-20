package com.kynnnn.coupon.convert;

import com.kynnnn.coupon.constant.CouponCategory;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * 优惠券分类枚举属性转换器
 * AttributeConverter<X,Y> X是实体属性的类型  Y是数据库字段的类型
 * @author Zhudiwei
 * @version 1.0
 * @date 2020/4/20 21:17
 */
@Converter
public class CouponTemplateConverter  implements AttributeConverter<CouponCategory,String> {

    /**
     * 将实体属性X转化为Y，存到数据库中
     * @param couponCategory
     * @return
     */
    @Override
    public String convertToDatabaseColumn(CouponCategory couponCategory) {
        return couponCategory.getCode();
    }

    /**
     * 将数据库中的字段Y，转化为实体属性X，查询操作时执行的动作
     * @param code
     * @return
     */
    @Override
    public CouponCategory convertToEntityAttribute(String code) {
        return CouponCategory.of(code);
    }
}
