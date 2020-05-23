package com.kynnnn.coupon.service.impl;

import com.kynnnn.coupon.dao.CouponTemplateDao;
import com.kynnnn.coupon.entity.CouponTemplate;
import com.kynnnn.coupon.exception.CouponException;
import com.kynnnn.coupon.service.IAsyncService;
import com.kynnnn.coupon.service.IBuildTemplateService;
import com.kynnnn.coupon.vo.TemplateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * 构建优惠券模板实现
 *
 * @author Zhudiwei
 */
@Service
@Slf4j
public class BuildTemplateServiceImpl implements IBuildTemplateService {

    /**
     * 异步服务
     */
    private final IAsyncService asyncService;

    /**
     * CouponTemplate Dao
     */
    private final CouponTemplateDao templateDao;

    public BuildTemplateServiceImpl(IAsyncService asyncService,
                                    CouponTemplateDao templateDao) {
        this.asyncService = asyncService;
        this.templateDao = templateDao;
    }


    @Override
    public CouponTemplate buildTemplate(TemplateRequest request) throws CouponException {

        //参数合法性校验
        if (!request.validate()) {
            throw new CouponException("BuildTemplate Param Is Not Valid!");
        }
        //判断同名模板是否存在
        if (null != templateDao.findByName(request.getName())) {
            throw new CouponException("Exists Same Name Template!");
        }

        CouponTemplate couponTemplate = requestToTemplate(request);
        couponTemplate = templateDao.save(couponTemplate);

        //根据优惠券模板异步生成优惠券码
        asyncService.asyncConstructCouponByTemplate(couponTemplate);

        return couponTemplate;
    }

    /**
     * 将 TemplateRequest 转化为优惠券实体 CouponTemplate
     *
     * @param request
     * @return
     */
    private CouponTemplate requestToTemplate(TemplateRequest request) {
        return new CouponTemplate(
                request.getName(),
                request.getLogo(),
                request.getDesc(),
                request.getCategory(),
                request.getProductLine(),
                request.getCount(),
                request.getUserId(),
                request.getTarget(),
                request.getRule()
        );
    }


}
