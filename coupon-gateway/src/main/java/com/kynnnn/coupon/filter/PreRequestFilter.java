package com.kynnnn.coupon.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 在过滤器中存储客户端发起请求的时间戳
 * @author Zhudiwei
 * @version 1.0
 * @date 2020/4/16 1:05
 */
@Slf4j
@Component
public class PreRequestFilter extends  AbstractZuulFilter{
    @Override
    protected Object cRun() {
        context.set("startTime",System.currentTimeMillis());
        return success();
    }

    @Override
    public String filterType() {
        return null;
    }

    @Override
    public int filterOrder() {
        return 0;
    }
}
