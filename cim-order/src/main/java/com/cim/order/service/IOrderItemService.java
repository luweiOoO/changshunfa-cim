package com.cim.order.service;


import com.cim.order.domain.OrderItem;
import com.cim.order.domain.req.OrderItemAddReq;

import java.util.List;

/**
 * <p>
 * 订单信息表 服务类
 * </p>
 *
 * @author luwei
 * @since 2020-10-06
 */
public interface IOrderItemService {


    /**
     *查询订单信息列表
     */
    public List<OrderItem> selectOrderItemList(OrderItem orderItem);

    /**
     * 新增订单信息
     * @param orderItemAddReq
     */
    public int addOrder(OrderItemAddReq orderItemAddReq) throws Exception;

    /**
     * 编辑订单信息
     * @param orderItemAddReq
     */
    public int editOrder(OrderItemAddReq orderItemAddReq);

    /**
     * 批量删除订单信息
     * @param orderIdList
     */
    int deleteOrder(List<Long> orderIdList);

    /**
     * 根据订单编码查询订单信息
     * @param orderNo
     * @return
     */
    OrderItem getItemByOrderNo(String orderNo);

    /**
     * 根据订单ID查询订单产品详情
     * @param id
     * @return
     */
    OrderItemAddReq getOrderById(Long id);

    // 批量新增订单信息
    int insertOrderItemBatch(List<OrderItem> orderItemList);

    // 更新订单信息
    int updateOrderItem(OrderItem orderItem);

    // 批量更新订单信息
    int updateOrderItemBatch(List<OrderItem> orderItemList);

    // 删除订单信息
    int deleteById(Long id);
}
