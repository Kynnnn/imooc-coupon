package com.kynnnn.coupon.exception;

import com.kynnnn.coupon.vo.CommonResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 *
 * @author Zhudiwei
 * @version 1.0
 * @date 2020/4/16 10:56
 */
@RestControllerAdvice
public class GlobalExceptionAdvice {

    /**
     * 对 CouponException 进行统一处理
     * @ExceptionHandler 可对指定异常进行拦截
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(value = CouponException.class)
    public CommonResponse<String> handlerCouponException(
            HttpServletRequest request,
            CouponException ex) {

        CommonResponse<String> response = new CommonResponse<>(-1, "business error");
        response.setData(ex.getMessage());
        return response;

    }
}
