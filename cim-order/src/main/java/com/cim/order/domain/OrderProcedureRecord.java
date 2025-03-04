package com.cim.order.domain;

import com.cim.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 订单流程记录表
 * </p>
 *
 * @author luwei
 * @since 2020-10-06
 */
@Data
public class OrderProcedureRecord extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 订单产品ID
     */
    private Long orderDetailId;

    /**
     * 流程编码
     */
    private String procedureCode;

    /**
     * 流程名称
     */
    private String procedureName;

    /**
     * 流程序号
     */
    private Integer orderNum;

    /**
     * 流程状态 1未开始 2进行中 3已完成
     */
    private String procedureStatus;

    /**
     * 流程开始审核人
     */
    private String startBy;

    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date startTime;

    /**
     * 预计完成时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date estimateTime;

    /**
     * 流程结束审核人
     */
    private String finishBy;

    /**
     * 实际完成时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date finishTime;

    /**
     * 流程损失材料重量
     */
    private BigDecimal lossWeight;

    /**
     * 是否返工 0不返工 1返工
     */
    private String isRework;

    /**
     * 返工原因
     */
    private String reworkReason;


    /**
     * 流程图片地址
     */
    private String procedureImageUrl;
}
