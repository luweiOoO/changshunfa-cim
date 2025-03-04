package com.cim.product.mapper;

import com.cim.product.domain.ProductProcedureRelate;

import java.util.List;

/**
 * 产品流程关联Mapper接口
 * 
 * @author ruoyi
 * @date 2025-02-27
 */
public interface ProductProcedureRelateMapper {

    /**
     * 查询产品流程关联
     * 
     * @param id 产品流程关联主键
     * @return 产品流程关联
     */
    public ProductProcedureRelate selectProductProcedureRelateById(Long id);

    /**
     * 查询产品流程关联列表
     * 
     * @param productProcedureRelate 产品流程关联
     * @return 产品流程关联集合
     */
    public List<ProductProcedureRelate> selectProductProcedureRelateList(ProductProcedureRelate productProcedureRelate);

    /**
     * 新增产品流程关联
     * 
     * @param productProcedureRelate 产品流程关联
     * @return 结果
     */
    public int insertProductProcedureRelate(ProductProcedureRelate productProcedureRelate);

    /**
     * 修改产品流程关联
     * 
     * @param productProcedureRelate 产品流程关联
     * @return 结果
     */
    public int updateProductProcedureRelate(ProductProcedureRelate productProcedureRelate);

    /**
     * 删除产品流程关联
     * 
     * @param id 产品流程关联主键
     * @return 结果
     */
    public int deleteProductProcedureRelateById(Long id);

    /**
     * 批量删除产品流程关联
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProductProcedureRelateByIds(Long[] ids);
}
