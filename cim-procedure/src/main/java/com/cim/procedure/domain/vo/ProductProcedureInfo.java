package com.cim.procedure.domain.vo;

import lombok.Data;

import java.util.List;

/**
 * 产品流程信息
 */
@Data
public class ProductProcedureInfo {

    /**
     * 产品编码
     */
    private String productCode;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 流程列表(已排序)
     */
    List<ProcedureOrderInfo> procedureList;
}
