package com.kynnnn.coupon.filter;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

/**
 * @author Zhudiwei
 * @version 1.0
 * @date 2020/4/15 18:10
 */
public abstract class AbstractPreZuulFilter extends  AbstractZuulFilter{
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }
}
