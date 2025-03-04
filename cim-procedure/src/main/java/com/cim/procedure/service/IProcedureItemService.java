package com.cim.procedure.service;

import com.cim.procedure.domain.ProcedureItem;
import com.cim.procedure.domain.vo.ProductProcedureInfo;

import java.util.List;

/**
 * 流程信息Service接口
 * 
 * @author ruoyi
 * @date 2025-02-27
 */
public interface IProcedureItemService {

    /**
     * 查询流程信息
     * 
     * @param id 流程信息主键
     * @return 流程信息
     */
    public ProcedureItem selectProcedureItemById(Long id);

    /**
     * 查询流程信息列表
     * 
     * @param procedureItem 流程信息
     * @return 流程信息集合
     */
    public List<ProcedureItem> selectProcedureItemList(ProcedureItem procedureItem);

    /**
     * 新增流程信息
     * 
     * @param procedureItem 流程信息
     * @return 结果
     */
    public int insertProcedureItem(ProcedureItem procedureItem);

    /**
     * 修改流程信息
     * 
     * @param procedureItem 流程信息
     * @return 结果
     */
    public int updateProcedureItem(ProcedureItem procedureItem);

    /**
     * 批量删除流程信息
     * 
     * @param ids 需要删除的流程信息主键集合
     * @return 结果
     */
    public int deleteProcedureItemByIds(Long[] ids);

    /**
     * 删除流程信息信息
     * 
     * @param id 流程信息主键
     * @return 结果
     */
    public int deleteProcedureItemById(Long id);

    /**
     * 根据产品编码查询产品流程列表(排序)
     * @param productCodes
     * @return
     */
    public List<ProductProcedureInfo> getProduceItemListByProductCode(List<String> productCodes);
}
