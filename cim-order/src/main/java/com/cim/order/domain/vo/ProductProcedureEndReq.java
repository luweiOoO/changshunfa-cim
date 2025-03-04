package com.cim.order.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 结束产品流程请求参数
 */
@Data
public class ProductProcedureEndReq implements Serializable {

    /**
     * 产品流程记录ID
     */
    private Long id;

    /**
     * 损失重量
     */
    private BigDecimal lossWeight;

    /**
     * OSS存储图片地址列表
     */
    private List<String> imageUrlList;

    /**
     * 用户手机号
     */
    private String phone;

    /**
     * 该手机号绑定的AppId
     */
    private String appId;
}
