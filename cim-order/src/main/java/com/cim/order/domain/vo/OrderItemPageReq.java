package com.cim.order.domain.vo;

import java.io.Serializable;

/**
 * 分页查询订单信息请求参数类
 */
public class OrderItemPageReq implements Serializable {

    /**
     * 工厂单号
     */
    private String factoryOrderNo;

    /**
     * 下单起始日期
     */
    private String orderStartTime;

    /**
     * 下单结束日期
     */
    private String orderEndTime;

    /**
     * 交货起始日期
     */
    private String deliverStartTime;

    /**
     * 交货结束日期
     */
    private String deliverEndTime;

    public String getFactoryOrderNo() {
        return factoryOrderNo;
    }

    public void setFactoryOrderNo(String factoryOrderNo) {
        this.factoryOrderNo = factoryOrderNo;
    }

    public String getOrderStartTime() {
        return orderStartTime;
    }

    public void setOrderStartTime(String orderStartTime) {
        this.orderStartTime = orderStartTime;
    }

    public String getOrderEndTime() {
        return orderEndTime;
    }

    public void setOrderEndTime(String orderEndTime) {
        this.orderEndTime = orderEndTime;
    }

    public String getDeliverStartTime() {
        return deliverStartTime;
    }

    public void setDeliverStartTime(String deliverStartTime) {
        this.deliverStartTime = deliverStartTime;
    }

    public String getDeliverEndTime() {
        return deliverEndTime;
    }

    public void setDeliverEndTime(String deliverEndTime) {
        this.deliverEndTime = deliverEndTime;
    }

    @Override
    public String toString() {
        return "OrderItemPageReq{" +
                ", factoryOrderNo='" + factoryOrderNo + '\'' +
                ", orderStartTime=" + orderStartTime +
                ", orderEndTime=" + orderEndTime +
                ", deliverStartTime=" + deliverStartTime +
                ", deliverEndTime=" + deliverEndTime +
                '}';
    }
}
