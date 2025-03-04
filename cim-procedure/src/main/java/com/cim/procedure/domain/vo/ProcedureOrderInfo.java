package com.cim.procedure.domain.vo;

import lombok.Data;

/**
 * 流程及排序信息
 */
@Data
public class ProcedureOrderInfo {

    /**
     * 流程编码
     */
    private String procedureCode;

    /**
     * 流程名称
     */
    private String procedureName;

    /**
     * 流程排序
     */
    private Integer orderNum;
}
