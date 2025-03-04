package com.cim.order.service;


import com.cim.order.domain.OrderProductDetail;
import com.cim.order.domain.vo.OrderDetailPageReq;
import com.cim.order.domain.vo.OrderDetailPageRes;

import java.util.List;

/**
 * <p>
 * 订单产品详情表 服务类
 * </p>
 *
 * @author luwei
 * @since 2020-10-06
 */
public interface IOrderProductDetailService {

    // 插入一条记录
    int insertOrderProductDetail(OrderProductDetail orderProductDetail);

    // 根据 ID 删除一条记录
    int deleteById(Long id);

    // 根据 ID 删除一条记录
    int deleteByIdBatch(List<Long> ids);

    // 更新一条记录
    int updateOrderProductDetail(OrderProductDetail orderProductDetail);

    // 根据 ID 查询一条记录
    OrderProductDetail selectById(Long id);


    /**
     * 根据工厂订单编码查询订单产品信息
     * @param factoryOrderNo
     * @return
     */
    List<OrderProductDetail> getProductListByOrderNo(String factoryOrderNo);

    /**
     * 查询产品详情列表
     * @param orderProductDetail
     * @return
     */
    List<OrderProductDetail> selectOrderProductDetailList(OrderProductDetail orderProductDetail);

    /**
     * 批量新增产品详情
     * @param orderProductDetailList
     * @return
     */
    int insertOrderProductDetailBatch(List<OrderProductDetail> orderProductDetailList);

    /**
     * 批量更新产品详情
     * @param orderProductDetailList
     * @return
     */
    int updateOrderProductDetailBatch(List<OrderProductDetail> orderProductDetailList);

    /**
     * 根据订单idList删除所有产品信息
     * @param orderIdList
     */
    void delOrderProductDetailByOrderIdList(List<Long> orderIdList);

}
