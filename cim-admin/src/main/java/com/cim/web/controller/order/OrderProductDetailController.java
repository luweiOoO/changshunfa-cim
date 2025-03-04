package com.cim.web.controller.order;


import com.cim.common.core.controller.BaseController;
import com.cim.common.core.domain.AjaxResult;
import com.cim.common.core.page.TableDataInfo;
import com.cim.order.domain.OrderProductDetail;
import com.cim.order.domain.vo.OrderDetailPageReq;
import com.cim.order.domain.vo.OrderDetailPageRes;
import com.cim.order.service.IOrderProductDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.cim.common.utils.PageUtils.startPage;

/**
 * <p>
 * 订单产品详情表 前端控制器
 * </p>
 *
 * @author luwei
 * @since 2020-10-06
 */
@RestController
@RequestMapping("/orderProductDetail")
public class OrderProductDetailController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(OrderProductDetailController.class);

    @Autowired
    private IOrderProductDetailService productDetailService;

    /**
     * 分页查询订单产品详情
     * @param request
     * @return
     */
//    @GetMapping("/list")
//    public TableDataInfo pageDetail(OrderDetailPageReq request){
//        startPage();
//        logger.info("分页查询订单产品详情请求参数：{}",request);
//        IPage<OrderDetailPageRes> resIPage = productDetailService.detailPage(request);
//        return StackHttpResponse.success(resIPage);
//    }

    /**
     * 根据订单编码查询该订单的产品列表
     * @param req
     * @return
     */
    @RequestMapping("/detail")
    public AjaxResult getDetail(@RequestBody OrderDetailPageReq req){
        logger.info("根据订单编码查询产品列表请求参数：{}",req);
        List<OrderProductDetail> detailList = productDetailService.getProductListByOrderNo(req.getFactoryOrderNo());
        logger.info("根据订单编码查询产品列表请求结果：{}",detailList);
        return AjaxResult.success(detailList);
    }

    /**
     * 根据产品ID查询指定产品详情
     * @param idReq
     * @return
     */
//    @RequestMapping("getById")
//    public StackHttpResponse getProductById(@RequestBody IdReq idReq){
//        logger.info("根据产品ID查询指定产品详情请求参数:{}",idReq);
//        OrderDetailPageRes OrderDetailPageRes = productDetailService.getProductById(idReq.getId());
//        logger.info("根据产品ID查询指定产品详情请求结果:{}",OrderDetailPageRes);
//        return StackHttpResponse.success(OrderDetailPageRes);
//    }

}

