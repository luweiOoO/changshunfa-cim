package com.cim.order.domain.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 产品开始流程请求参数
 */
@Data
public class ProductProcedureStartReq implements Serializable {

    /**
     * 产品流程记录ID
     */
    private Long id;

    /**
     *产品流程预计完成日期
     */
    private String estimateTime;

    /**
     * 操作人手机号
     */
    private String phone;

    /**
     * 该手机号绑定的appId
     */
    private String appId;

}
