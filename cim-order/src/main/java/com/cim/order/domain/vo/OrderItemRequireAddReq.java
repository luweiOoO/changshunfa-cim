package com.cim.order.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 新增订单信息-订单要求参数接收类
 */
@Data
public class OrderItemRequireAddReq implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 订单ID
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
     * 要求参考图片OSS存储地址列表
     */
    private List<String> ossImageUrlList;

}
