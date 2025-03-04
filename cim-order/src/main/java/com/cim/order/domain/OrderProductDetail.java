package com.cim.order.domain;

import com.cim.common.core.domain.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 订单产品详情表
 * </p>
 *
 * @author luwei
 * @since 2020-10-06
 */
@Data
public class OrderProductDetail extends BaseEntity {

    private static final long serialVersionUID = 1L;


    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 产品编码
     */
    private String productCode;

    /**
     * 产品名称
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


}
