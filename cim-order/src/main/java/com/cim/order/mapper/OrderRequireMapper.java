package com.cim.order.mapper;

import com.cim.order.domain.OrderRequire;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 订单要求表 Mapper 接口
 * </p>
 *
 * @author luwei
 * @since 2020-10-15
 */
public interface OrderRequireMapper {

    // 条件查询列表
    List<OrderRequire> selectOrderRequireList(OrderRequire orderRequire);

    // 新增订单要求
    int insertOrderRequire(OrderRequire orderRequire);

    // 批量新增订单要求
    int insertOrderRequireBatch(List<OrderRequire> orderRequireList);

    // 更新订单要求
    int updateOrderRequire(OrderRequire orderRequire);

    // 批量更新订单要求
    int updateOrderRequireBatch(List<OrderRequire> orderRequireList);

    // 删除订单要求
    int deleteById(Long id);

    /**
     * 根据订单idList删除所有产品要求信息
     * @param orderIdList
     */
    void delOrderRequireByOrderIdList(@Param("orderIdList") List<Long> orderIdList);

    /**
     * 批量删除订单要求
     * @param idList
     * @return
     */
    int deleteByIdList(@Param("idList") List<Long> idList);


}
