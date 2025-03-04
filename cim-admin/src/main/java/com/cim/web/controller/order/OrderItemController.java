package com.cim.web.controller.order;


import com.aliyun.oss.OSS;
import com.cim.common.core.controller.BaseController;
import com.cim.common.core.domain.AjaxResult;
import com.cim.common.core.page.TableDataInfo;
import com.cim.order.domain.OrderItem;
import com.cim.order.domain.req.OrderItemAddReq;
import com.cim.order.service.IOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 订单信息表 前端控制器
 * </p>
 *
 * @author luwei
 * @since 2020-10-06
 */
@RestController
@RequestMapping("/orderItem")
public class OrderItemController extends BaseController {


    @Autowired
    private IOrderItemService orderItemService;

    /**
     * 分页查询订单信息
     * @param orderItem
     * @return
     */
    @GetMapping("/list")
    public TableDataInfo list(OrderItem orderItem){
        startPage();
        List<OrderItem> result = orderItemService.selectOrderItemList(orderItem);
        return getDataTable(result);
    }

    /**
     * 新增订单信息接口
     * @param request
     * @return
     */
    @RequestMapping("/add")
    public AjaxResult add(@RequestBody OrderItemAddReq request) throws Exception {
        logger.info("新增订单信息请求参数：{}",request);
        return toAjax( orderItemService.addOrder(request));
    }

    /**
     * 编辑订单信息
     * @param req
     * @return
     */
    @RequestMapping("/edit")
    public AjaxResult edit(@RequestBody OrderItemAddReq req){
        logger.info("编辑订单信息请求参数：{}",req);
        return toAjax( orderItemService.editOrder(req));
    }

    /**
     * 删除订单信息
     * @param ids
     * @return
     */
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable Long[] ids){
        logger.info("删除订单信息请求参数：{}",ids);
        return toAjax(orderItemService.deleteOrder(Arrays.asList(ids)));

    }

    /**
     * 根据订单ID查询订单产品详情
     * @param id
     * @return
     */
    @GetMapping("/getById/{id}")
    public AjaxResult getOrderById(@PathVariable("id") Long id){
        logger.info("根据订单ID查询订单产品详情请求参数：{}",id);
        OrderItemAddReq result = orderItemService.getOrderById(id);
        logger.info("根据订单ID查询订单产品详情请求结果：{}",result);
        return AjaxResult.success(result);
    }
}

