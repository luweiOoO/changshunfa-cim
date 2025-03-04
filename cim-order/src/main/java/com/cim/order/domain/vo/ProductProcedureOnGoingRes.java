package com.cim.order.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 获取产品当前进行中的流程参数接受类
 */
@Data
public class ProductProcedureOnGoingRes implements Serializable {

    /**
     * 产品流程记录ID
     */
    private Long id;

    /**
     * 产品ID
     */
    private Long productId;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 订单编号
     */
    private String factoryOrderNo;

    /**
     * 下单时间
     */
    @JsonFormat(shape= JsonFormat.Shape.STRING,pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date orderTime;

    /**
     * 交货时间
     */
    @JsonFormat(shape= JsonFormat.Shape.STRING,pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date deliveryTime;

    /**
     * 流程名
     */
    private String procedureName;

    /**
     * 流程ID
     */
    private Long procedureId;

    /**
     * 流程状态
     */
    private Integer status;

    /**
     * 流程开始审核人
     */
    private String startBy;

    /**
     * 流程开始时间
     */
    @JsonFormat(shape= JsonFormat.Shape.STRING,pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date startTime;

    /**
     * 流程预计完成时间
     */
    @JsonFormat(shape= JsonFormat.Shape.STRING,pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date estimateTime;

    /**
     * OSS存储流程图片地址(多个图片逗号分隔)
     */
    private String procedureImageUrl;

    /**
     * OSS存储流程图片地址列表(由String按逗号分隔转换)
     */
    private List<String> imageUrlList;
}
