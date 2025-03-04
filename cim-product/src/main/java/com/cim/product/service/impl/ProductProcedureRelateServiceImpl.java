package com.cim.product.service.impl;

import java.util.List;

import com.cim.product.domain.ProductProcedureRelate;
import com.cim.product.mapper.ProductProcedureRelateMapper;
import com.cim.product.service.IProductProcedureRelateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 产品流程关联Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-02-27
 */
@Service
public class ProductProcedureRelateServiceImpl implements IProductProcedureRelateService {

    @Autowired
    private ProductProcedureRelateMapper productProcedureRelateMapper;

    /**
     * 查询产品流程关联
     * 
     * @param id 产品流程关联主键
     * @return 产品流程关联
     */
    @Override
    public ProductProcedureRelate selectProductProcedureRelateById(Long id)
    {
        return productProcedureRelateMapper.selectProductProcedureRelateById(id);
    }

    /**
     * 查询产品流程关联列表
     * 
     * @param productProcedureRelate 产品流程关联
     * @return 产品流程关联
     */
    @Override
    public List<ProductProcedureRelate> selectProductProcedureRelateList(ProductProcedureRelate productProcedureRelate)
    {
        return productProcedureRelateMapper.selectProductProcedureRelateList(productProcedureRelate);
    }

    /**
     * 新增产品流程关联
     * 
     * @param productProcedureRelate 产品流程关联
     * @return 结果
     */
    @Override
    public int insertProductProcedureRelate(ProductProcedureRelate productProcedureRelate)
    {
        return productProcedureRelateMapper.insertProductProcedureRelate(productProcedureRelate);
    }

    /**
     * 修改产品流程关联
     * 
     * @param productProcedureRelate 产品流程关联
     * @return 结果
     */
    @Override
    public int updateProductProcedureRelate(ProductProcedureRelate productProcedureRelate)
    {
        return productProcedureRelateMapper.updateProductProcedureRelate(productProcedureRelate);
    }

    /**
     * 批量删除产品流程关联
     * 
     * @param ids 需要删除的产品流程关联主键
     * @return 结果
     */
    @Override
    public int deleteProductProcedureRelateByIds(Long[] ids)
    {
        return productProcedureRelateMapper.deleteProductProcedureRelateByIds(ids);
    }

    /**
     * 删除产品流程关联信息
     * 
     * @param id 产品流程关联主键
     * @return 结果
     */
    @Override
    public int deleteProductProcedureRelateById(Long id)
    {
        return productProcedureRelateMapper.deleteProductProcedureRelateById(id);
    }
}
