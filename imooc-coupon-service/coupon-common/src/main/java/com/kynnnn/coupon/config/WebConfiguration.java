package com.kynnnn.coupon.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 定制 HTTP 消息转换器
 *
 * @author Zhudiwei
 * @version 1.0
 * @date 2020/4/16 1:36
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    /**
     *
     * @param converters 对应当前系统中所有的消息转换器
     */
    @Override
    public void configureMessageConverters(
            List<HttpMessageConverter<?>> converters) {
        //清空
        converters.clear();
        converters.add(new MappingJackson2HttpMessageConverter());
    }
}
