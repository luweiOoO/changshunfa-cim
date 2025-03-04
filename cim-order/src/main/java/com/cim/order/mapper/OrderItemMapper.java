package com.cim.order.mapper;


import com.cim.order.domain.OrderItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 订单信息表 Mapper 接口
 * </p>
 *
 * @author luwei
 * @since 2020-10-06
 */
public interface OrderItemMapper{

    /**
     * 查询订单信息
     * @param orderItem
     * @return
     */
    public List<OrderItem> selectOrderItemList(OrderItem orderItem);

    /**
     * 根据订单id查询订单信息
     * @param orderId
     * @return
     */
    public OrderItem selectOrderItemById(@Param("orderId") Long orderId);


    /**
     * 新增订单信息
     *
     * @param orderItem 订单信息
     * @return 结果
     */
    public int insertOrderItem(OrderItem orderItem);


    // 批量新增订单信息
    int insertOrderItemBatch(@Param("orderItemList") List<OrderItem> orderItemList);

    // 更新订单信息
    int updateOrderItem(OrderItem orderItem);

    // 批量更新订单信息
    int updateOrderItemBatch(@Param("orderItemList") List<OrderItem> orderItemList);

    // 删除订单信息
    int deleteById(Long id);

    // 删除订单信息
    int deleteByIdList(@Param("idList") List<Long> idList);

    // 校验 factory_no 是否重复
    int countByFactoryOrderNo(@Param("factoryOrderNo") String factoryOrderNo, @Param("id") Long id);




}
