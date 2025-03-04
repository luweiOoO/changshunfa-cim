package com.cim.order.domain.req;

import com.cim.order.domain.OrderProductDetail;
import com.cim.order.domain.vo.OrderItemRequireAddReq;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 新增订单信息请求参数类
 */
@Data
public class OrderItemAddReq implements Serializable {

    /**
     * 订单ID
     */
    private Long id;

    /**
     * 工厂单号
     */
    private String factoryOrderNo;

    /**
     * 下单日期
     */
    private Date orderTime;

    /**
     * 计划完成日期
     */
    private Date planFinishTime;

    /**
     * 实际完成时间
     */
    private Date realFinishTime;

    /**
     * 业务员
     */
    private String salePerson;

    /**
     * 订单进度
     */
    private BigDecimal orderProgress;

    /**
     * 订单状态 0未开始 1进行中 2已完成
     */
    private String orderStatus;

    /**
     * 订单详情列表
     */
    private List<OrderProductDetail> detailList;

    /**
     * 订单生产要求列表
     */
    private List<OrderItemRequireAddReq> requireList;

}
