//package com.cim.web.controller.wechat;
//
//import com.alibaba.fastjson.JSONObject;
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.changshunfa.stack.entity.OrderProcedureRecord;
//import com.changshunfa.stack.entity.OrderProductDetail;
//import com.changshunfa.stack.entity.User;
//import com.changshunfa.stack.entity.req.*;
//import com.changshunfa.stack.entity.res.OrderRequireRes;
//import com.changshunfa.stack.entity.res.ProductProcedureOnGoingRes;
//import com.changshunfa.stack.entity.res.WeChatGetAppIdRes;
//import com.changshunfa.stack.enums.ProcedureConstants;
//import com.changshunfa.stack.http.StackHttpResponse;
//import com.changshunfa.stack.mapper.OrderProcedureRecordMapper;
//import com.changshunfa.stack.service.*;
//import com.changshunfa.stack.service.impl.WeChatServiceImpl;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.Resource;
//import java.util.List;
//
///**
// * 微信小程序相关接口
// */
//@RestController
//@RequestMapping("/wechat")
//public class WeChatController {
//
//    private static final Logger logger = LoggerFactory.getLogger(WeChatController.class);
//
//    @Autowired
//    private IWeChatService weChatService;
//    @Resource
//    private IOrderProductDetailService productDetailService;
//    @Resource
//    private IOrderRequireService orderRequireService;
//    @Resource
//    private IOrderProcedureRecordService procedureRecordService;
//    @Resource
//    private IUserService userService;
//    @Autowired
//    private OrderProcedureRecordMapper orderProcedureRecordMapper;
//
//    /**
//     * 获取用户OpenId接口
//     * @param request
//     * @return
//     */
//    @RequestMapping("/getOpenId")
//    public StackHttpResponse getUserDetail(@RequestBody WeChatUserDetailReq request){
//        logger.info("获取用户OpenId请求参数：{}",request);
//        JSONObject result = WeChatServiceImpl.getSessionKeyOropenId(request.getCode());
//        String appId = (String) result.get("openid");
//        logger.info("获取用户AppId的结果：{}",result);
//        User user = userService.getUserByAppId(appId);
//        logger.info("该AppId绑定的用户信息:{}",user);
//        WeChatGetAppIdRes res = new WeChatGetAppIdRes();
//        res.setAppId(appId);
//        if(user != null && user.getProcedureId() != null){
//            res.setProcedureId(user.getProcedureId());
//            res.setPhone(user.getPhone());
//        }
//        logger.info("获取用户OpenId接口请求结果:{}",res);
//        return StackHttpResponse.success(res);
//    }
//
//    /**
//     * 根据用户appId获取该用户相关信息
//     * @param req
//     * @return
//     */
//    @RequestMapping("/getUserByAppId")
//    public StackHttpResponse getUserDetailByAppId(@RequestBody WeChatUserLoginReq req){
//        logger.info("根据appid查询用户信息请求参数:{}",req);
//        StackHttpResponse response = weChatService.getUserDetailByAppId(req);
//        logger.info("根据AppId查询用户信息请求结果:{}",response);
//        return response;
//    }
//
//    /**
//     * 首次登录小程序用户绑定appId
//     * @param req
//     * @return
//     */
//    @RequestMapping("/bindAppId")
//    public StackHttpResponse bindAppIdByUser(@RequestBody WeChatUserLoginReq req){
//        logger.info("首次登录小程序用户绑定AppId请求参数:{}",req);
//        StackHttpResponse response = weChatService.bindUserAppId(req);
//        logger.info("首次登录小程序用户绑定AppId请求结果:{}",response);
//        return response;
//    }
//
//
//    /**
//     * 小程序根据订单编号查询订单产品列表
//     * @param request
//     * @return
//     */
//    @RequestMapping("/productList")
//    public StackHttpResponse getOrderProcess(@RequestBody OrderCodeReq request){
//        logger.info("获取订单流程请求参数：{}",request);
//        List<OrderProductDetail> detailList = productDetailService.getProductListByOrderNo(request.getCode());
//        logger.info("获取订单流程请求结果：{}",detailList);
//        return StackHttpResponse.success(detailList);
//    }
//
//    /**
//     * 根据订单ID查询订单要求
//     * @param req
//     * @return
//     */
//    @RequestMapping("requireByOrdeId")
//    public StackHttpResponse getOrderRequireByOrderId(@RequestBody IdReq req){
//        logger.info("根据订单ID查询订单要求请求参数：{}",req);
//        List<OrderRequireRes> requireRes = orderRequireService.getRequireListByOrderId(req.getId());
//        logger.info("根据订单ID查询订单要求请求结果：{}",requireRes);
//        return StackHttpResponse.success(requireRes);
//    }
//
//    /**
//     * 根据产品ID查询产品最新流程状态
//     * @param req
//     * @return
//     */
//    @RequestMapping("procedureByProductId")
//    public StackHttpResponse getNewestProcedureByProductId(@RequestBody IdReq req){
//        logger.info("根据产品ID查询产品最新流程状态请求参数：{}",req);
//        ProductProcedureOnGoingRes res = procedureRecordService.getNewestProcedureByProductId(req.getId());
//        return StackHttpResponse.success(res);
//    }
//
//    /**
//     * 产品流程--开始该流程
//     * @param req
//     * @return
//     */
//    @RequestMapping("startProductProcedure")
//    public StackHttpResponse startProductProcedure(@RequestBody ProductProcedureStartReq req){
//        logger.info("开始产品流程请求参数:{}",req);
//        StackHttpResponse res = procedureRecordService.startProductProcedure(req);
//        logger.info("开始产品流程请求结果:{}",res);
//        return res;
//    }
//
//    /**
//     * 产品流程--结束该流程
//     * @param req
//     * @return
//     */
//    @RequestMapping("endProductProcedure")
//    public StackHttpResponse endProductProcedure(@RequestBody ProductProcedureEndReq req){
//        logger.info("完成产品流程请求参数:{}",req);
//        StackHttpResponse res = procedureRecordService.endProductProcedure(req);
//        logger.info("完成产品流程请求结果：{}",res);
//        return res;
//    }
//
//
//    /**
//     * 查询指定产品的流程记录
//     * @param orderId
//     * @return
//     */
//    @GetMapping("/getOrderProcedureRecordList")
//    public StackHttpResponse getOrderProcedureRecordList(Long orderId){
//        if(orderId == null){
//            return StackHttpResponse.error("a","a");
//        }
//        //分页查询流程信息
//        QueryWrapper<OrderProcedureRecord> recordQueryWrapper = new QueryWrapper<>();
//        recordQueryWrapper.eq("order_detail_id",orderId);
//        recordQueryWrapper.orderByAsc("start_time");
//        List<OrderProcedureRecord> result = orderProcedureRecordMapper.selectList(recordQueryWrapper);
//        if(result != null && result.size() > 0){
//            result.forEach(obj -> {
//                obj.setStatus(ProcedureConstants.ProcedureStatusEnum.findByStatusCode(obj.getStatus()).getStatusName());
//            });
//        }
//
//        return StackHttpResponse.success(result);
//    }
//}
