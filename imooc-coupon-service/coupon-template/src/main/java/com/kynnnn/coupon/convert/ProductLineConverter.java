package com.kynnnn.coupon.convert;

import com.kynnnn.coupon.constant.ProductLine;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * 产品线枚举属性转换器
 *
 * @author Zhudiwei
 * @version 1.0
 * @date 2020/4/20 21:24
 */
@Converter
public class ProductLineConverter implements AttributeConverter<ProductLine, Integer> {

    /**
     * 将实体属性X转化为Y，存到数据库中
     *
     * @param productLine
     * @return
     */
    @Override
    public Integer convertToDatabaseColumn(ProductLine productLine) {
        return productLine.getCode();
    }

    /**
     * 将数据库中的字段Y，转化为实体属性X，查询操作时执行的动作
     *
     * @param code
     * @return
     */
    @Override
    public ProductLine convertToEntityAttribute(Integer code) {
        return ProductLine.of(code);
    }
}
