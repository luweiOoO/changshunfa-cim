package com.cim.procedure.service.impl;

import java.util.List;
import com.cim.common.utils.DateUtils;
import com.cim.procedure.domain.ProcedureItem;
import com.cim.procedure.domain.vo.ProductProcedureInfo;
import com.cim.procedure.mapper.ProcedureItemMapper;
import com.cim.procedure.service.IProcedureItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 流程信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-02-27
 */
@Service
public class ProcedureItemServiceImpl implements IProcedureItemService {

    @Autowired
    private ProcedureItemMapper procedureItemMapper;

    /**
     * 查询流程信息
     * 
     * @param id 流程信息主键
     * @return 流程信息
     */
    @Override
    public ProcedureItem selectProcedureItemById(Long id)
    {
        return procedureItemMapper.selectProcedureItemById(id);
    }

    /**
     * 查询流程信息列表
     * 
     * @param procedureItem 流程信息
     * @return 流程信息
     */
    @Override
    public List<ProcedureItem> selectProcedureItemList(ProcedureItem procedureItem)
    {
        return procedureItemMapper.selectProcedureItemList(procedureItem);
    }

    /**
     * 新增流程信息
     * 
     * @param procedureItem 流程信息
     * @return 结果
     */
    @Override
    public int insertProcedureItem(ProcedureItem procedureItem)
    {
        procedureItem.setCreateTime(DateUtils.getNowDate());
        return procedureItemMapper.insertProcedureItem(procedureItem);
    }

    /**
     * 修改流程信息
     * 
     * @param procedureItem 流程信息
     * @return 结果
     */
    @Override
    public int updateProcedureItem(ProcedureItem procedureItem)
    {
        procedureItem.setUpdateTime(DateUtils.getNowDate());
        return procedureItemMapper.updateProcedureItem(procedureItem);
    }

    /**
     * 批量删除流程信息
     * 
     * @param ids 需要删除的流程信息主键
     * @return 结果
     */
    @Override
    public int deleteProcedureItemByIds(Long[] ids)
    {
        return procedureItemMapper.deleteProcedureItemByIds(ids);
    }

    /**
     * 删除流程信息信息
     * 
     * @param id 流程信息主键
     * @return 结果
     */
    @Override
    public int deleteProcedureItemById(Long id)
    {
        return procedureItemMapper.deleteProcedureItemById(id);
    }


    /**
     * 根据产品编码查询产品流程列表(排序)
     * @param productCodes
     * @return
     */
    @Override
    public List<ProductProcedureInfo> getProduceItemListByProductCode(List<String> productCodes) {
        return procedureItemMapper.getProduceItemListByProductCode(productCodes);
    }
}
