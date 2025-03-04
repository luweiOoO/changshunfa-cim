package com.cim.product.mapper;

import com.cim.product.domain.ProductInfo;

import java.util.List;

/**
 * 产品基础信息Mapper接口
 * 
 * @author ruoyi
 * @date 2025-02-27
 */
public interface ProductInfoMapper {

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
     * 删除产品基础信息
     * 
     * @param id 产品基础信息主键
     * @return 结果
     */
    public int deleteProductInfoById(Long id);

    /**
     * 批量删除产品基础信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProductInfoByIds(Long[] ids);

}
