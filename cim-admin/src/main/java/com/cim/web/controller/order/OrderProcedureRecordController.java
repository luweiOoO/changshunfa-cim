package com.cim.web.controller.order;


import com.cim.common.core.controller.BaseController;
import com.cim.common.core.domain.AjaxResult;
import com.cim.common.core.page.TableDataInfo;
import com.cim.order.domain.OrderProcedureRecord;
import com.cim.order.service.IOrderProcedureRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 订单流程记录表 前端控制器
 * </p>
 *
 * @author luwei
 * @since 2020-10-06
 */
@RestController
@RequestMapping("/orderProcedureRecord")
public class OrderProcedureRecordController extends BaseController {

    @Autowired
    private IOrderProcedureRecordService orderProcedureRecordService;

    private static final Logger logger = LoggerFactory.getLogger(OrderProcedureRecordController.class);

    /**
     * 根据产品ID查询最新流程信息
     * @param req
     * @return
     */
//    @RequestMapping("/record")
//    public AjaxResult getRecordByProduct(@RequestBody IdReq req){
//        logger.info("根据产品查询最新流程信息请求参数：{}",req);
//        OrderProcedureRecord record = orderProcedureRecordService.getNewRecordByProduct(req);
//        logger.info("根据产品查询最新流程信息请求结果：{}",record);
//        return AjaxResult.success(record);
//    }

    /**
     * 分页查询订单产品流程详情
     * @param request
     * @return
     */
//    @RequestMapping("/list")
//    public TableDataInfo productRecordPage(@RequestBody StackHttpRequest<IdReq> request){
//        logger.info("分页查询订单产品流程详情请求参数：{}",request);
//        IPage<OrderProcedureRecord> result = orderProcedureRecordService.recordPage(request);
//        return StackHttpResponse.success(result);
//    }

    /**
     * 根据产品流程ID查询流程得详细信息
     * @param idReq
     * @return
     */
//    @RequestMapping("/getProcedureDetail")
//    public StackHttpResponse getProcedureDetailById(@RequestBody IdReq idReq){
//        logger.info("查询产品流程详情请求参数：{}",idReq);
//        OrderProcedureDetailRes result = orderProcedureRecordService.getProcedureDetailById(idReq.getId());
//        logger.info("查询产品流程详情请求结果：{}",result);
//        return StackHttpResponse.success(result);
//
//    }

}

