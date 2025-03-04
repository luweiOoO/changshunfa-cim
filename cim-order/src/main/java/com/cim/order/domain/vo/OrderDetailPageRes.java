package com.cim.order.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单产品详情分页结果参数类
 */
@Data
public class OrderDetailPageRes implements Serializable {

    private Long id;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 订单编号
     */
    private String factoryOrderNo;

    /**
     * 产品编码
     */
    private String productCode;

    /**
     * 品名
     */
    private String productName;

    /**
     * 发质
     */
    private String hairQuality;

    /**
     * 曲直
     */
    private String hairStraight;

    /**
     * 幅度
     */
    private String hairRanges;

    /**
     * 长度
     */
    private String hairLength;


    /**
     * 重量
     */
    private String hairWeight;

    /**
     * 颜色
     */
    private String hairColour;

    /**
     * 数量
     */
    private Integer hairNumber;

    /**
     * 单价
     */
    private BigDecimal unitPrice;

    /**
     * 总价
     */
    private BigDecimal totalPrice;

    /**
     * 流程ID
     */
    private Long procedureId;

    /**
     * 流程名
     */
    private String procedureName;

    /**
     * 流程编号
     */
    private Integer procedureOrder;

    /**
     * 流程状态
     */
    private Integer procedureStatus;

}
