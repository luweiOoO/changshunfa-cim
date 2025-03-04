package com.cim.product.service.impl;

import java.util.List;
import com.cim.common.utils.DateUtils;
import com.cim.procedure.domain.ProcedureItem;
import com.cim.procedure.service.IProcedureItemService;
import com.cim.product.domain.ProductInfo;
import com.cim.product.domain.vo.AvailableAndAssignedProcedureVo;
import com.cim.product.mapper.ProductInfoMapper;
import com.cim.product.service.IProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 产品基础信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-02-27
 */
@Service
public class ProductInfoServiceImpl implements IProductInfoService {

    @Autowired
    private ProductInfoMapper productInfoMapper;
    @Autowired
    private IProcedureItemService procedureItemService;

    /**
     * 查询产品基础信息
     * 
     * @param id 产品基础信息主键
     * @return 产品基础信息
     */
    @Override
    public ProductInfo selectProductInfoById(Long id)
    {
        return productInfoMapper.selectProductInfoById(id);
    }

    /**
     * 查询产品基础信息列表
     * 
     * @param productInfo 产品基础信息
     * @return 产品基础信息
     */
    @Override
    public List<ProductInfo> selectProductInfoList(ProductInfo productInfo)
    {
        return productInfoMapper.selectProductInfoList(productInfo);
    }

    /**
     * 新增产品基础信息
     * 
     * @param productInfo 产品基础信息
     * @return 结果
     */
    @Override
    public int insertProductInfo(ProductInfo productInfo)
    {
        productInfo.setCreateTime(DateUtils.getNowDate());
        return productInfoMapper.insertProductInfo(productInfo);
    }

    /**
     * 修改产品基础信息
     * 
     * @param productInfo 产品基础信息
     * @return 结果
     */
    @Override
    public int updateProductInfo(ProductInfo productInfo)
    {
        productInfo.setUpdateTime(DateUtils.getNowDate());
        return productInfoMapper.updateProductInfo(productInfo);
    }

    /**
     * 批量删除产品基础信息
     * 
     * @param ids 需要删除的产品基础信息主键
     * @return 结果
     */
    @Override
    public int deleteProductInfoByIds(Long[] ids)
    {
        return productInfoMapper.deleteProductInfoByIds(ids);
    }

    /**
     * 删除产品基础信息信息
     * 
     * @param id 产品基础信息主键
     * @return 结果
     */
    @Override
    public int deleteProductInfoById(Long id)
    {
        return productInfoMapper.deleteProductInfoById(id);
    }

    @Override
    public AvailableAndAssignedProcedureVo getAvailableAndAssignedProcedureListByProductCode(String productCode) {
        //查询出所有工序
        List<ProcedureItem> availableProcedureList = procedureItemService.selectProcedureItemList(new PrsocedureItem());

       return null;
    }


}
