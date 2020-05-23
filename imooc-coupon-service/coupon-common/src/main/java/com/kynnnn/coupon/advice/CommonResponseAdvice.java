package com.kynnnn.coupon.advice;

import com.kynnnn.coupon.annotation.IgnoreResponseAdvice;
import com.kynnnn.coupon.vo.CommonResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 统一响应
 *
 * @author Zhudiwei
 * @version 1.0
 * @date 2020/4/16 10:20
 */
@RestControllerAdvice
public class CommonResponseAdvice implements ResponseBodyAdvice<Object> {

    /**
     * 需要重写的方法1：判断是否需要对响应进行处理
     *
     * @param methodParameter 当前controller的方法
     * @param aClass
     * @return true需要处理，false不需要处理
     */
    @Override
    @SuppressWarnings("all")
    public boolean supports(MethodParameter methodParameter,
                            Class<? extends HttpMessageConverter<?>> aClass) {

        //如果当前的 类 标识了 @IgnoreResponseAdvice 注解，不需要处理
        if (methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        }
        //如果当前的 方法 标识了 @IgnoreResponseAdvice 注解，不需要处理
        if (methodParameter.getMethod().isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        }
        //对响应进行处理，执行 beforeBodyWrite 方法
        return true;
    }

    /**
     * 需要重写的方法2：
     *
     * @param o                  controller的返回对象
     * @param methodParameter    controller的方法
     * @param mediaType
     * @param aClass
     * @param serverHttpRequest
     * @param serverHttpResponse
     * @return
     */
    @Override
    @SuppressWarnings("all")
    public Object beforeBodyWrite(Object o,
                                  MethodParameter methodParameter,
                                  MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {

        //定义最终的返回对象
        CommonResponse<Object> response = new CommonResponse<>(0, "");
        // 如果o是null，即返回类型是Null，则不需要设置data
        if (null == o) {
            return response;
            //如果o已经是CommonResponse，则不需要处理
        } else if (o instanceof CommonResponse) {
            response = (CommonResponse<Object>) o;
        } else {
            //否则，把相应对象作为CommonResponse的data部分set进去
            response.setData(o);
        }
        return response;
    }
}
