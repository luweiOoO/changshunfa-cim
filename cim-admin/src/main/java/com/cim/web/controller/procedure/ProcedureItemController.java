package com.cim.web.controller.procedure;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.cim.procedure.domain.ProcedureItem;
import com.cim.procedure.service.IProcedureItemService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cim.common.annotation.Log;
import com.cim.common.core.controller.BaseController;
import com.cim.common.core.domain.AjaxResult;
import com.cim.common.enums.BusinessType;
import com.cim.common.utils.poi.ExcelUtil;
import com.cim.common.core.page.TableDataInfo;

/**
 * 流程信息Controller
 * 
 * @author ruoyi
 * @date 2025-02-27
 */
@RestController
@RequestMapping("/procedure/item")
public class ProcedureItemController extends BaseController
{
    @Autowired
    private IProcedureItemService procedureItemService;

    /**
     * 查询流程信息列表
     */
//    @PreAuthorize("@ss.hasPermi('system:item:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProcedureItem procedureItem)
    {
        startPage();
        List<ProcedureItem> list = procedureItemService.selectProcedureItemList(procedureItem);
        return getDataTable(list);
    }

    /**
     * 导出流程信息列表
     */
//    @PreAuthorize("@ss.hasPermi('system:item:export')")
    @Log(title = "流程信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProcedureItem procedureItem)
    {
        List<ProcedureItem> list = procedureItemService.selectProcedureItemList(procedureItem);
        ExcelUtil<ProcedureItem> util = new ExcelUtil<ProcedureItem>(ProcedureItem.class);
        util.exportExcel(response, list, "流程信息数据");
    }

    /**
     * 获取流程信息详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:item:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(procedureItemService.selectProcedureItemById(id));
    }

    /**
     * 新增流程信息
     */
//    @PreAuthorize("@ss.hasPermi('system:item:add')")
    @Log(title = "流程信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProcedureItem procedureItem)
    {
        return toAjax(procedureItemService.insertProcedureItem(procedureItem));
    }

    /**
     * 修改流程信息
     */
//    @PreAuthorize("@ss.hasPermi('system:item:edit')")
    @Log(title = "流程信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProcedureItem procedureItem)
    {
        return toAjax(procedureItemService.updateProcedureItem(procedureItem));
    }

    /**
     * 删除流程信息
     */
//    @PreAuthorize("@ss.hasPermi('system:item:remove')")
    @Log(title = "流程信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(procedureItemService.deleteProcedureItemByIds(ids));
    }
}
