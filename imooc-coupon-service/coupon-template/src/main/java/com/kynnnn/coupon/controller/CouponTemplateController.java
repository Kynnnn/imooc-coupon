package com.kynnnn.coupon.controller;

import com.alibaba.fastjson.JSON;
import com.kynnnn.coupon.entity.CouponTemplate;
import com.kynnnn.coupon.exception.CouponException;
import com.kynnnn.coupon.service.IBuildTemplateService;
import com.kynnnn.coupon.service.ITemplateBaseService;
import com.kynnnn.coupon.vo.CouponTemplateSDK;
import com.kynnnn.coupon.vo.TemplateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 优惠券模板相关的功能控制器
 *
 * @author Zhudiwei
 */
@Slf4j
@RestController
public class CouponTemplateController {

    /**
     * 优惠券模板服务
     */
    private final IBuildTemplateService templateService;

    /**
     * 优惠券模板基础服务
     */
    private final ITemplateBaseService baseService;

    public CouponTemplateController(IBuildTemplateService templateService, ITemplateBaseService baseService) {
        this.templateService = templateService;
        this.baseService = baseService;
    }

    /**
     * 构建优惠券模板
     * 127.0.0.1:7001/coupon-template/build
     * 127.0.0.1:9000/imooc/coupon-template/build
     * @param request {@link TemplateRequest}
     * @return
     * @throws CouponException
     */
    @PostMapping("/build")
    public CouponTemplate buildTemplate(@RequestBody TemplateRequest request) throws CouponException {
        log.info("Build Template: {}", JSON.toJSONString(request));
        return templateService.buildTemplate(request);
    }

    /**
     * 构造优惠券模板详情
     * 127.0.0.1:7001/coupon-template/template/info?id=1
     *
     * @param id
     * @return
     */
    @GetMapping("/template/info")
    public CouponTemplate buildTemplateInfo(@RequestParam("id") Integer id) throws CouponException {
        log.info("Build Template Info For: {}", id);
        return baseService.buildTemplateInfo(id);
    }

    /**
     * 查找所有可用的优惠券模板
     * 127.0.0.1:7001/coupon-template/template/sdk/all
     *
     * @return
     */
    @GetMapping("/template/sdk/all")
    public List<CouponTemplateSDK> findAllUsableTemplate() {
        log.info("Find All Usable Template");
        return baseService.findAllUsableTemplate();
    }

    /**
     * 获取模板 ids 到 CouponTemplateSDK 的映射
     * 127.0.0.1:7001/coupon-template/template/sdk/infos
     *
     * @return
     */
    @GetMapping("/template/sdk/infos")
    public Map<Integer, CouponTemplateSDK> findIds2TemplateSDK(@RequestParam("ids") Collection<Integer> ids) {
        log.info("FindIds2TemplateSDK : {}", JSON.toJSONString(ids));
        return baseService.findIds2TemplateSDK(ids);
    }

}
