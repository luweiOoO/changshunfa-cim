package com.cim.order.domain;

import com.cim.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 订单信息表
 * </p>
 *
 * @author luwei
 * @since 2020-10-06
 */
@Data
public class OrderItem extends BaseEntity {

    private static final long serialVersionUID = 1L;


    /**
     * 工厂单号
     */
    private String factoryOrderNo;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 下单时间
     */
    @JsonFormat(shape= JsonFormat.Shape.STRING,pattern="yyyy-MM-dd hh-mm-ss",timezone="GMT+8")
    private Date orderTime;

    /**
     * 预计完成时间
     */
    @JsonFormat(shape= JsonFormat.Shape.STRING,pattern="yyyy-MM-dd hh-mm-ss",timezone="GMT+8")
    private Date planFinishTime;

    /**
     * 实际完成时间
     */
    @JsonFormat(shape= JsonFormat.Shape.STRING,pattern="yyyy-MM-dd hh-mm-ss",timezone="GMT+8")
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

}
