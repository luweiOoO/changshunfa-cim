package com.cim.order.service;


import com.cim.order.domain.OrderProcedureRecord;
import com.cim.order.domain.vo.ProductProcedureEndReq;
import com.cim.order.domain.vo.ProductProcedureOnGoingRes;
import com.cim.order.domain.vo.ProductProcedureStartReq;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单流程记录表 服务类
 * </p>
 *
 * @author luwei
 * @since 2020-10-06
 */
public interface IOrderProcedureRecordService {

    /**
     * 批量删除订单流程信息
     * @param orderIdList
     */
    void delRecordByOrderIdList(List<Long> orderIdList);


    /**
     * 根据产品编码和id初始化产品得第一步流程
     * @param productIdAndCodeMap
     * @return
     */
    int initProductProcedureRecordByProductCodeAndDetailIdMap(Map<Long,String> productIdAndCodeMap);

    /**
     * 根据产品ID获取该产品最新流程信息
     * @param productId
     * @return
     */
    ProductProcedureOnGoingRes getNewestProcedureByProductId(Long productId);

//    /**
//     * 开始产品流程
//     * @return 返回更新后的流程信息
//     */
//    void startProductProcedure(ProductProcedureStartReq req);
//
//    /**
//     * 完成产品流程
//     * @param req
//     * @return 返回更新后的流程信息
//     */
//    void endProductProcedure(ProductProcedureEndReq req);


    // 新增产品流程记录
    int insertOrderProcedureRecord(OrderProcedureRecord record);

    // 批量新增产品流程记录
    int insertOrderProcedureRecordBatch(List<OrderProcedureRecord> list);

    // 删除指定产品流程记录
    int deleteById(Long id);

    // 根据产品idList批量删除产品流程记录
    int deleteByOrderDetailIdBatch(List<Long> orderDetailIdList);

    // 更新产品流程记录
    int updateOrderProcedureRecord(OrderProcedureRecord record);

    // 批量更新产品流程记录
    int updateOrderProcedureRecordBatch(List<OrderProcedureRecord> list);

    // 查询指定产品流程记录
    OrderProcedureRecord selectById(Long id);

    // 查询产品流程记录列表
    List<OrderProcedureRecord> selectOrderProcedureRecordList(OrderProcedureRecord orderProcedureRecord);
}
