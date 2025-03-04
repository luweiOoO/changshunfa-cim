package com.cim.order.domain.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 分页查询订单产品详情请求参数类
 */
@Data
public class OrderDetailPageReq implements Serializable {

    /**
     * 订单编号
     */
    private String factoryOrderNo;

    /**
     * 流程ID
     */
    private Long procedureId;

}
