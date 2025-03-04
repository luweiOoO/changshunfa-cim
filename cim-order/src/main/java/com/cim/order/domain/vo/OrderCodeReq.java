package com.cim.order.domain.vo;

import java.io.Serializable;

/**
 * 订单编码请求参数类
 */
public class OrderCodeReq implements Serializable {

    /**
     * 订单编码
     */
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "OrderCodeReq{" +
                "code='" + code + '\'' +
                '}';
    }
}
