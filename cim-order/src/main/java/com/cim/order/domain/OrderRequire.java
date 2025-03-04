package com.cim.order.domain;

import com.cim.common.core.domain.BaseEntity;
import lombok.Data;


/**
 * <p>
 * 订单要求表
 * </p>
 *
 * @author luwei
 * @since 2020-10-15
 */
@Data
public class OrderRequire extends BaseEntity {

    private static final long serialVersionUID = 1L;


    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 要求名称
     */
    private String requireName;

    /**
     * 要求内容
     */
    private String requireContent;

    /**
     * 要求参考图片
     */
    private String requireImageUrl;

}
