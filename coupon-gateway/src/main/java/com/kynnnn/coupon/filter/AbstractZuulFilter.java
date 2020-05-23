package com.kynnnn.coupon.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * 通用的抽象过滤器类
 *
 * @author Zhudiwei
 * @version 1.0
 * @date 2020/4/15 17:46
 */
@SuppressWarnings("all")
public abstract class AbstractZuulFilter extends ZuulFilter {

    //用于在过滤器之间传递消息，数据保存在每个请求的 ThreadLocal 中
    //扩展了 Map
    RequestContext context;
    private final static String NEXT = "next";

    @Override
    public boolean shouldFilter() {
        //获取当前线程的 RequestContext
        RequestContext currentContext = RequestContext.getCurrentContext();
        //SpringCloud内置的过滤器并不包含 NEXT，所以不包含默认为true
        return (boolean) currentContext.getOrDefault(NEXT, true);
    }

    @Override
    public Object run() throws ZuulException {
        context = RequestContext.getCurrentContext();
        return cRun();
    }

    //由于这是一个通用的抽象过滤器类，所以不希望在run方法中有具体的实现细节 所以定义一个抽象的run方法
    protected abstract Object cRun();

    //定义通过或不通过的快速返回方法
    Object fail(int code, String msg) {
        context.set(NEXT, false);
        context.setSendZuulResponse(false);
        context.getResponse().setContentType("text/html;charset=UTF-8");
        context.setResponseStatusCode(code);
        context.setResponseBody(String.format("{\"result\": \"%s!\"}", msg));

        return null;
    }

    Object success() {
        context.set(NEXT, true);
        return null;
    }
}
