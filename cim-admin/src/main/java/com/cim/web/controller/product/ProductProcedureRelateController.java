package com.cim.web.controller.product;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.cim.product.domain.ProductProcedureRelate;
import com.cim.product.service.IProductProcedureRelateService;
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
 * 产品流程关联Controller
 * 
 * @author ruoyi
 * @date 2025-02-27
 */
@RestController
@RequestMapping("/product/procedureRelate")
public class ProductProcedureRelateController extends BaseController
{
    @Autowired
    private IProductProcedureRelateService productProcedureRelateService;

    /**
     * 查询产品流程关联列表
     */
//    @PreAuthorize("@ss.hasPermi('productProcedure:relate:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProductProcedureRelate productProcedureRelate)
    {
        startPage();
        List<ProductProcedureRelate> list = productProcedureRelateService.selectProductProcedureRelateList(productProcedureRelate);
        return getDataTable(list);
    }

    /**
     * 导出产品流程关联列表
     */
//    @PreAuthorize("@ss.hasPermi('productProcedure:relate:export')")
    @Log(title = "产品流程关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProductProcedureRelate productProcedureRelate)
    {
        List<ProductProcedureRelate> list = productProcedureRelateService.selectProductProcedureRelateList(productProcedureRelate);
        ExcelUtil<ProductProcedureRelate> util = new ExcelUtil<ProductProcedureRelate>(ProductProcedureRelate.class);
        util.exportExcel(response, list, "产品流程关联数据");
    }

    /**
     * 获取产品流程关联详细信息
     */
//    @PreAuthorize("@ss.hasPermi('productProcedure:relate:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(productProcedureRelateService.selectProductProcedureRelateById(id));
    }

    /**
     * 新增产品流程关联
     */
//    @PreAuthorize("@ss.hasPermi('productProcedure:relate:add')")
    @Log(title = "产品流程关联", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProductProcedureRelate productProcedureRelate)
    {
        return toAjax(productProcedureRelateService.insertProductProcedureRelate(productProcedureRelate));
    }

    /**
     * 修改产品流程关联
     */
//    @PreAuthorize("@ss.hasPermi('productProcedure:relate:edit')")
    @Log(title = "产品流程关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProductProcedureRelate productProcedureRelate)
    {
        return toAjax(productProcedureRelateService.updateProductProcedureRelate(productProcedureRelate));
    }

    /**
     * 删除产品流程关联
     */
//    @PreAuthorize("@ss.hasPermi('productProcedure:relate:remove')")
    @Log(title = "产品流程关联", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(productProcedureRelateService.deleteProductProcedureRelateByIds(ids));
    }
}
