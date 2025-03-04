package com.cim.product.service;

import com.cim.procedure.domain.ProcedureItem;
import com.cim.product.domain.ProductInfo;
import com.cim.product.domain.vo.AvailableAndAssignedProcedureVo;

import java.util.List;

/**
 * 产品基础信息Service接口
 * 
 * @author ruoyi
 * @date 2025-02-27
 */
public interface IProductInfoService {

    /**
     * 查询产品基础信息
     * 
     * @param id 产品基础信息主键
     * @return 产品基础信息
     */
    public ProductInfo selectProductInfoById(Long id);

    /**
     * 查询产品基础信息列表
     * 
     * @param productInfo 产品基础信息
     * @return 产品基础信息集合
     */
    public List<ProductInfo> selectProductInfoList(ProductInfo productInfo);

    /**
     * 新增产品基础信息
     * 
     * @param productInfo 产品基础信息
     * @return 结果
     */
    public int insertProductInfo(ProductInfo productInfo);

    /**
     * 修改产品基础信息
     * 
     * @param productInfo 产品基础信息
     * @return 结果
     */
    public int updateProductInfo(ProductInfo productInfo);

    /**
     * 批量删除产品基础信息
     * 
     * @param ids 需要删除的产品基础信息主键集合
     * @return 结果
     */
    public int deleteProductInfoByIds(Long[] ids);

    /**
     * 删除产品基础信息信息
     * 
     * @param id 产品基础信息主键
     * @return 结果
     */
    public int deleteProductInfoById(Long id);


    public AvailableAndAssignedProcedureVo getAvailableAndAssignedProcedureListByProductCode(String productCode);


}
