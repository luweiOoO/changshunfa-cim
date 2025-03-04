package com.cim.order.service.impl;


import com.cim.order.domain.OrderItem;
import com.cim.order.domain.OrderProcedureRecord;
import com.cim.order.domain.OrderProductDetail;
import com.cim.order.domain.vo.OrderDetailPageRes;
import com.cim.order.mapper.OrderProductDetailMapper;
import com.cim.order.service.IOrderItemService;
import com.cim.order.service.IOrderProcedureRecordService;
import com.cim.order.service.IOrderProductDetailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 订单产品详情表 服务实现类
 * </p>
 *
 * @author luwei
 * @since 2020-10-06
 */
@Service
public class OrderProductDetailServiceImpl implements IOrderProductDetailService {

    @Autowired
    private IOrderItemService orderItemService;
    @Resource
    private IOrderProcedureRecordService recordService;
    @Autowired
    private OrderProductDetailMapper orderProductDetailMapper;

    @Override
    public int insertOrderProductDetail(OrderProductDetail orderProductDetail) {
        return orderProductDetailMapper.insertOrderProductDetail(orderProductDetail);
    }

    @Override
    public int deleteById(Long id) {
        return orderProductDetailMapper.deleteById(id);
    }

    @Override
    public int deleteByIdBatch(List<Long> ids) {
        return orderProductDetailMapper.deleteByIdBatch(ids);
    }

    @Override
    public int updateOrderProductDetail(OrderProductDetail orderProductDetail) {
        return orderProductDetailMapper.updateOrderProductDetail(orderProductDetail);
    }

    @Override
    public OrderProductDetail selectById(Long id) {
        return orderProductDetailMapper.selectById(id);
    }

    /**
     * 根据工厂订单编码查询订单产品信息
     * @param factoryOrderNo
     * @return
     */
    @Override
    public List<OrderProductDetail> getProductListByOrderNo(String factoryOrderNo){
        return orderProductDetailMapper.getProductListByOrderNo(factoryOrderNo);
    }


    @Override
    public List<OrderProductDetail> selectOrderProductDetailList(OrderProductDetail orderProductDetail) {
        return orderProductDetailMapper.selectOrderProductDetailList(orderProductDetail);
    }

    @Override
    public int insertOrderProductDetailBatch(List<OrderProductDetail> orderProductDetailList) {
        return orderProductDetailMapper.insertOrderProductDetailBatch(orderProductDetailList);
    }

    @Override
    public int updateOrderProductDetailBatch(List<OrderProductDetail> orderProductDetailList) {
        return orderProductDetailMapper.updateOrderProductDetailBatch(orderProductDetailList);
    }

    /**
     * 根据订单idList删除所有产品信息
     * @param orderIdList
     */
    @Override
    public void delOrderProductDetailByOrderIdList(List<Long> orderIdList){
        orderProductDetailMapper.delOrderProductDetailByOrderIdList(orderIdList);
    }

}
