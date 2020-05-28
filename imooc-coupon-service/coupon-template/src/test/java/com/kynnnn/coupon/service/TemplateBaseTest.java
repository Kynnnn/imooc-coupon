package com.kynnnn.coupon.service;

import com.alibaba.fastjson.JSON;
import com.google.gson.annotations.JsonAdapter;
import com.kynnnn.coupon.exception.CouponException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

/**
 * 优惠券模板基础服务的测试
 *
 * @author Zhudiwei
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TemplateBaseTest {

    @Autowired
    private ITemplateBaseService service;

    @Test
    public void testBuildTemplateInfo() throws CouponException {
        System.out.println(JSON.toJSONString(service.buildTemplateInfo(10)));
        System.out.println(JSON.toJSONString(service.buildTemplateInfo(1)));
    }

    @Test
    public void testFindAllUsableTemplate() throws CouponException {
        System.out.println(JSON.toJSONString(service.findAllUsableTemplate()));
    }

    @Test
    public void testFindIds2TemplateSDK() throws CouponException {
        System.out.println(JSON.toJSONString(service.findIds2TemplateSDK(Arrays.asList(1,2,10))));
    }


}
