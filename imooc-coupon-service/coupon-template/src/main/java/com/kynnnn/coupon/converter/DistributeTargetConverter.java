package com.kynnnn.coupon.converter;

import com.kynnnn.coupon.constant.DistributeTarget;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author Zhudiwei
 * @version 1.0
 * @date 2020/4/20 21:28
 */
@Converter
public class DistributeTargetConverter implements AttributeConverter<DistributeTarget, Integer> {

    /**
     * 将实体属性X转化为Y，存到数据库中
     *
     * @param distributeTarget
     * @return
     */
    @Override
    public Integer convertToDatabaseColumn(DistributeTarget distributeTarget) {
        return distributeTarget.getCode();
    }

    /**
     * 将数据库中的字段Y，转化为实体属性X，查询操作时执行的动作
     *
     * @param code
     * @return
     */
    @Override
    public DistributeTarget convertToEntityAttribute(Integer code) {
        return DistributeTarget.of(code);
    }
}
