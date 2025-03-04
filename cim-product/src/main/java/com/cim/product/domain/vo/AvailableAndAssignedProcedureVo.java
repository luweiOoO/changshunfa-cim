package com.cim.product.domain.vo;

import com.cim.procedure.domain.ProcedureItem;
import lombok.Data;

import java.util.List;

@Data
public class AvailableAndAssignedProcedureVo {

    /**
     * 可分配工序列表
     */
    List<ProcedureItem> availableProcedureList;

    /**
     * 已分配工序列表
     */
    List<ProcedureItem> assignedProcedureList;
}
