package com.kynnnn.coupon.vo;

import com.kynnnn.coupon.constant.PeriodType;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 优惠券规则对象定义
 *
 * @author Zhudiwei
 * @version 1.0
 * @date 2020/4/20 12:07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemplateRule implements Serializable {

    /** 折扣券过期规则 */
    private Expiration expiration;

    /** 折扣 */
    private Discount discount;

    /** 每人最多领几张 */
    private Integer limitation;

    /** 使用范围：地域+商品类型 */
    private Usage usage;

    /** 权重：可以和哪些优惠券叠加使用，同一类的优惠券一定不能叠加 list[]，优惠券唯一编码 */
    private String weight;

    /** 校验功能 */
    public  boolean validate(){
        return expiration.validate() &&
                discount.validate() &&
                limitation>0 &&
                usage.validate() &&
                StringUtils.isNotEmpty(weight);
    }

    /**
     * 内部类，有效期限规则
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Expiration {
        //有效期规则，对应 PeriodType 的 code 字段
        private Integer period;

        //有效间隔，只对变动型的有效期有效
        private Integer gap;

        //优惠券模板的失效日期，对PeriodType的两类过期类型都有效
        private Long deadLine;

        //最简化校验
        boolean validate() {
            return null != PeriodType.of(period) && gap > 0 && deadLine > 0;
        }
    }

    /**
     * 内部类，折扣，需要与类型配合决定
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Discount {

        //额度，满减(20)，折扣(85)
        private Integer quota;

        //基准，需要满多少才可用
        private Integer base;

        boolean validate() {
            return quota > 0 && base > 0;
        }

    }

    /**
     * 使用范围
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Usage {

        //省份
        private String province;

        //城市
        private String city;

        //商品类型， list[文娱，生鲜，家具，全品类]
        private String goodsType;

        boolean validate() {
            return StringUtils.isNotEmpty(province) &&
                    StringUtils.isNotEmpty(city) &&
                    StringUtils.isNotEmpty(goodsType);
        }
    }


}
