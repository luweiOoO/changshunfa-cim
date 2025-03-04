package com.cim.product.domain;

import com.cim.common.annotation.Excel;
import com.cim.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 产品流程关联对象 product_procedure_relate
 * 
 * @author ruoyi
 * @date 2025-02-27
 */
@Data
public class ProductProcedureRelate extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 产品编码 */
    @Excel(name = "产品编码")
    private String productCode;

    /** 流程编码 */
    @Excel(name = "流程编码")
    private String procedureCode;

    /** 流程排序 */
    @Excel(name = "流程排序")
    private Integer orderNum;

}
