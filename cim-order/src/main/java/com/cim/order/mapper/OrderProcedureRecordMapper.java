package com.cim.order.mapper;


import com.cim.order.domain.OrderProcedureRecord;
import com.cim.order.domain.vo.ProductProcedureOnGoingRes;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 订单流程记录表 Mapper 接口
 * </p>
 *
 * @author luwei
 * @since 2020-10-06
 */
public interface OrderProcedureRecordMapper{

    ProductProcedureOnGoingRes newestProcedureByProductId(@Param("productId") Long productId);

    // 新增产品流程记录
    int insertOrderProcedureRecord(OrderProcedureRecord record);

    // 批量新增产品流程记录
    int insertOrderProcedureRecordBatch(List<OrderProcedureRecord> list);

    // 删除指定产品流程记录
    int deleteById(Long id);

    // 根据产品idList批量删除产品流程记录
    int deleteByOrderDetailIdBatch(@Param("orderDetailIdList") List<Long> orderDetailIdList);

    // 删除指定产品流程记录
    int deleteByOrderDetailIdList(@Param("orderDetailIdList") List<Long> orderDetailIdList);

    // 更新产品流程记录
    int updateOrderProcedureRecord(OrderProcedureRecord record);

    // 批量更新产品流程记录
    int updateOrderProcedureRecordBatch(List<OrderProcedureRecord> list);

    // 查询指定产品流程记录
    OrderProcedureRecord selectById(Long id);

    // 查询产品流程记录列表
    List<OrderProcedureRecord> selectOrderProcedureRecordList(OrderProcedureRecord orderProcedureRecord);

    // 批量删除订单流程信息
    void delRecordByOrderIdList(@Param("orderIdList") List<Long> orderIdList);

}
