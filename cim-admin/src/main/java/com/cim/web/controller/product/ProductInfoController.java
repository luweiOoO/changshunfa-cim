package com.cim.web.controller.product;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.cim.product.domain.ProductInfo;
import com.cim.product.service.IProductInfoService;
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
 * 产品基础信息Controller
 * 
 * @author ruoyi
 * @date 2025-02-27
 */
@RestController
@RequestMapping("/product/info")
public class ProductInfoController extends BaseController
{
    @Autowired
    private IProductInfoService productInfoService;

    /**
     * 查询产品基础信息列表
     */
//    @PreAuthorize("@ss.hasPermi('system:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProductInfo productInfo)
    {
        startPage();
        List<ProductInfo> list = productInfoService.selectProductInfoList(productInfo);
        return getDataTable(list);
    }

    /**
     * 导出产品基础信息列表
     */
//    @PreAuthorize("@ss.hasPermi('system:info:export')")
    @Log(title = "产品基础信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProductInfo productInfo)
    {
        List<ProductInfo> list = productInfoService.selectProductInfoList(productInfo);
        ExcelUtil<ProductInfo> util = new ExcelUtil<ProductInfo>(ProductInfo.class);
        util.exportExcel(response, list, "产品基础信息数据");
    }

    /**
     * 获取产品基础信息详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:info:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(productInfoService.selectProductInfoById(id));
    }

    /**
     * 新增产品基础信息
     */
//    @PreAuthorize("@ss.hasPermi('system:info:add')")
    @Log(title = "产品基础信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProductInfo productInfo)
    {
        return toAjax(productInfoService.insertProductInfo(productInfo));
    }

    /**
     * 修改产品基础信息
     */
//    @PreAuthorize("@ss.hasPermi('system:info:edit')")
    @Log(title = "产品基础信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProductInfo productInfo)
    {
        return toAjax(productInfoService.updateProductInfo(productInfo));
    }

    /**
     * 删除产品基础信息
     */
//    @PreAuthorize("@ss.hasPermi('system:info:remove')")
    @Log(title = "产品基础信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(productInfoService.deleteProductInfoByIds(ids));
    }
}
