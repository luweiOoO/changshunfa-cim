package com.cim.procedure.domain;

import com.cim.common.annotation.Excel;
import com.cim.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 流程信息对象 procedure_item
 * 
 * @author ruoyi
 * @date 2025-02-27
 */
@Data
public class ProcedureItem extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 流程编码 */
    @Excel(name = "流程编码")
    private String procedureCode;

    /** 流程名 */
    @Excel(name = "流程名")
    private String procedureName;

}
