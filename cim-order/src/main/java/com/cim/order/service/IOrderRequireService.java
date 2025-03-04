package com.cim.order.service;


import com.cim.order.domain.OrderRequire;
import com.cim.order.domain.vo.OrderItemRequireAddReq;

import java.util.List;

/**
 * <p>
 * 订单要求表 服务类
 * </p>
 *
 * @author luwei
 * @since 2020-10-15
 */
public interface IOrderRequireService {


    /**
     * 根据订单ID查询订单要求列表
     * @param orderId
     * @return
     */
    List<OrderItemRequireAddReq> getRequireByOrderId(Long orderId);

    /**
     *
     * @param orderRequire
     * @return查询订单要求列表
     */
    List<OrderRequire> selectOrderRequireList(OrderRequire orderRequire);

    /**
     * 新增订单要求
     * @param orderRequire
     * @return
     */
    int insertOrderRequire(OrderRequire orderRequire);

    /**
     * 批量新增订单要求
     * @param list
     * @return
     */
    int insertOrderRequireBatch(List<OrderRequire> list);

    /**
     * 更新订单要求
     * @param orderRequire
     * @return
     */
    int updateOrderRequire(OrderRequire orderRequire);

    /**
     * 批量更新订单要求
     * @param orderRequireList
     * @return
     */
    int updateOrderRequireBatch(List<OrderRequire> orderRequireList);

    /**
     * 删除订单要求
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * 批量删除订单要求
     * @param idList
     * @return
     */
    int deleteByIdList(List<Long> idList);

    /**
     * 根据订单idList删除所有产品要求信息
     * @param orderIdList
     */
    void delOrderRequireByOrderIdList(List<Long> orderIdList);

}
