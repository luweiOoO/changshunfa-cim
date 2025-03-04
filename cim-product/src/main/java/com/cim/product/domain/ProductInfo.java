package com.cim.product.domain;

import com.cim.common.annotation.Excel;
import com.cim.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 产品基础信息对象 product_info
 * 
 * @author luwei
 * @date 2025-02-27
 */
@Data
public class ProductInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 产品编码 */
    @Excel(name = "产品编码")
    private String productCode;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String productName;

}
