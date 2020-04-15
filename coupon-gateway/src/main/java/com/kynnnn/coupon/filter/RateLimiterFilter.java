package com.kynnnn.coupon.filter;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 限流过滤器
 *
 * @author Zhudiwei
 * @version 1.0
 * @date 2020/4/15 23:57
 */
@Slf4j
@Component
public class RateLimiterFilter extends AbstractPreZuulFilter {

    //每秒可以取到两个令牌
    RateLimiter rateLimiter = RateLimiter.create(2.0);

    @Override
    protected Object cRun() {
        HttpServletRequest request = context.getRequest();
        if (rateLimiter.tryAcquire()) {
            log.info("get rate token successfully");
            return success();
        }
        return null;
    }

    @Override
    public int filterOrder() {
        return 2;
    }
}
