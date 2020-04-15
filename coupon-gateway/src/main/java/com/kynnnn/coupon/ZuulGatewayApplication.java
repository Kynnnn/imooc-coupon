package com.kynnnn.coupon;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author Zhudiwei
 * @version 1.0
 * @date 2020/4/15 17:13
 * @EnableZuulProxy 标识当前的应用是 Zuul Server
 * @SpringCloudApplication 组合了 SpringBoot应用 + 服务发现 + 熔断
 */
@SpringCloudApplication
@EnableZuulProxy
public class ZuulGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuulGatewayApplication.class, args);
    }
}
