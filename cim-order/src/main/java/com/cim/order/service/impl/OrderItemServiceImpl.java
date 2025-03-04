package com.cim.order.service.impl;

import com.cim.common.constant.ProcedureConstants;
import com.cim.common.utils.OSSUtil;
import com.cim.common.utils.StringUtils;
import com.cim.order.domain.OrderItem;
import com.cim.order.domain.OrderProcedureRecord;
import com.cim.order.domain.OrderProductDetail;
import com.cim.order.domain.OrderRequire;
import com.cim.order.domain.req.OrderItemAddReq;
import com.cim.order.domain.vo.OrderItemPageReq;
import com.cim.order.domain.vo.OrderItemRequireAddReq;
import com.cim.order.mapper.OrderItemMapper;
import com.cim.order.service.IOrderItemService;
import com.cim.order.service.IOrderProcedureRecordService;
import com.cim.order.service.IOrderProductDetailService;
import com.cim.order.service.IOrderRequireService;
import com.cim.procedure.domain.ProcedureItem;
import com.cim.procedure.domain.vo.ProcedureOrderInfo;
import com.cim.procedure.domain.vo.ProductProcedureInfo;
import com.cim.procedure.service.IProcedureItemService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.SetUtils;
import org.apache.commons.collections4.list.SetUniqueList;
import org.aspectj.weaver.ast.Or;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 订单信息表 服务实现类
 * </p>
 *
 * @author luwei
 * @since 2020-10-06
 */
@Service
@Slf4j
public class OrderItemServiceImpl  implements IOrderItemService {

    private static Logger logger = LoggerFactory.getLogger(OrderItemServiceImpl.class);

    @Autowired
    private IOrderProductDetailService productDetailService;
    @Autowired
    private IOrderProcedureRecordService procedureRecordService;
    @Autowired
    private IProcedureItemService procedureItemService;
    @Resource
    private IOrderRequireService orderRequireService;
    @Autowired
    private OSSUtil ossUtil;
    @Autowired
    private OrderItemMapper orderItemMapper;


    /**
     *查询订单信息列表
     * @param orderItem
     * @return
     */
    @Override
    public List<OrderItem> selectOrderItemList(OrderItem orderItem) {
        return orderItemMapper.selectOrderItemList(orderItem);
    }

    //1.订单基本信息新增
    //2.订单详情新增
    //3.初始化订单各个产品状态
    @Override
    @Transactional
    public int addOrder(OrderItemAddReq orderItemAddReq) throws Exception {
        //参数校验
        this.checkAddOrEditOrderParam(orderItemAddReq);

        OrderItem check = getItemByOrderNo(orderItemAddReq.getFactoryOrderNo());
        if(check != null){
            throw new Exception("订单编码重复,请校验");
//            return StackHttpResponse.error("400","订单编码重复,请校验");
        }
        //新增订单基本信息
        OrderItem orderItem = new OrderItem();
        BeanUtils.copyProperties(orderItemAddReq,orderItem);
        orderItemMapper.insertOrderItem(orderItem);
        //汇总所有产品编码
        List<String> productCodeList = orderItemAddReq.getDetailList()
                .stream().map(OrderProductDetail::getProductCode)
                .filter(Objects::nonNull) // 过滤掉 null 值
                .distinct() // 去重
                .collect(Collectors.toList());
        //初始化订单的产品信息
        List<OrderProductDetail> detailList = orderItemAddReq.getDetailList();
        if(detailList != null && detailList.size() > 0){//批量新增订单详情
            detailList.forEach(detail -> {
                detail.setOrderId(orderItem.getId());
                //计算产品总价
                BigDecimal amount = detail.getUnitPrice().multiply(new BigDecimal(detail.getHairNumber()));
                detail.setTotalPrice(amount);
                detailList.add(detail);
            });
        }else {
            throw new Exception("订单产品详情不能为空");
        }
        //批量新增产品明细信息
        productDetailService.insertOrderProductDetailBatch(detailList);
        // 将 List<OrderProductDetail> 转换为 Map<Long, String>，处理重复 key
        Map<Long, String> productDetailIdAndCodeMap = detailList.stream()
                .collect(Collectors.toMap(
                        OrderProductDetail::getId, // Key: OrderProductDetail 的 id
                        OrderProductDetail::getProductCode, // Value: OrderProductDetail 的 productCode
                        (oldValue, newValue) -> oldValue // 如果 key 重复，保留旧值
                ));
        //批量新增产品流程记录信息
        procedureRecordService.initProductProcedureRecordByProductCodeAndDetailIdMap(productDetailIdAndCodeMap);
        //初始化订单生产要求信息
        List<OrderItemRequireAddReq> addRequireList = orderItemAddReq.getRequireList();
        logger.info("新增订单生产要求信息入参为：{}",addRequireList);
        List<OrderRequire> requireList = new LinkedList<>();
        addRequireList.forEach(addRequire -> {
            logger.info("循环遍历订单生产要求参数接受类：{}",addRequire);
            OrderRequire require = new OrderRequire();
            require.setOrderId(orderItem.getId());
            require.setRequireContent(addRequire.getRequireContent());
            require.setRequireName(addRequire.getRequireName());

            if(addRequire.getOssImageUrlList() != null && addRequire.getOssImageUrlList().size() > 0){
                require.setRequireImageUrl(String.join(",",addRequire.getOssImageUrlList()));
            }
            logger.info("根据要求接受类构造生产要求类：{}",require);
            requireList.add(require);
        });
        orderRequireService.insertOrderRequireBatch(requireList);
        return 1;
    }

    /**
     * 校验新增或修改订单信息的通用参数校验方法
     * @param orderItemAddReq
     */
    public void checkAddOrEditOrderParam(OrderItemAddReq orderItemAddReq) throws Exception {
        if(orderItemAddReq == null){
            throw new Exception("参数不能为空");
        }
        if(StringUtils.isEmpty(orderItemAddReq.getFactoryOrderNo())){
            throw new Exception("订单编码不能为空");
        }
        if(orderItemAddReq.getOrderTime() == null){
            throw new Exception("下单时间不能为空");
        }
        if(orderItemAddReq.getPlanFinishTime() == null){
            throw new Exception("预计完成日期不能为空");
        }
        if(StringUtils.isEmpty(orderItemAddReq.getOrderStatus())){

        }
        if(orderItemAddReq.getOrderProgress() == null){
            orderItemAddReq.setOrderProgress(BigDecimal.ZERO);
        }
        if(StringUtils.isEmpty(orderItemAddReq.getDetailList())){
            throw new Exception("产品信息不能为空");
        }
    }

    @Override
    @Transactional
    public int editOrder(OrderItemAddReq orderItemAddReq) {
        //修改前订单信息
        int exitFactorOrderNoNum = orderItemMapper.countByFactoryOrderNo(orderItemAddReq.getFactoryOrderNo(),orderItemAddReq.getId());
        if (exitFactorOrderNoNum > 0) {
            throw new IllegalArgumentException("订单编码 " + orderItemAddReq.getFactoryOrderNo() + " 已存在，请更换！");
        }
        OrderItem newOrderItem = new OrderItem();
        BeanUtils.copyProperties(orderItemAddReq,newOrderItem);
        //更新订单基础信息
        newOrderItem.setUpdateInfo();
        orderItemMapper.updateOrderItem(newOrderItem);

        OrderProductDetail productDetailParam = new OrderProductDetail();
        productDetailParam.setOrderId(orderItemAddReq.getId());
        List<OrderProductDetail> oldProductDetailList = productDetailService.selectOrderProductDetailList(productDetailParam);
        List<Long> oldProductIdList = oldProductDetailList.stream().map(OrderProductDetail::getId).collect(Collectors.toList());
        //需删除得产品ID列表(oldProductDetailList中存在但newProductIdList不存在的数据)
        // 将 newProductIdList 转换为 Set
        Set<Long> newProductIdSet = orderItemAddReq.getDetailList().stream().map(OrderProductDetail::getId).collect(Collectors.toSet());

        // 使用 Stream 筛选
        List<Long> delProductIdList = oldProductIdList.stream()
                .filter(id -> !newProductIdSet.contains(id))
                .collect(Collectors.toList());

        if(delProductIdList.size() > 0){
            //批量删除订单的产品信息
            productDetailService.deleteByIdBatch(delProductIdList);
            //批量删除产品的流程信息
            procedureRecordService.deleteByOrderDetailIdBatch(delProductIdList);
        }
        //筛选出产品ID为null得产品，此产品为新增的产品
        List<OrderProductDetail> addProductDetailList = orderItemAddReq.getDetailList().stream().filter(product -> product.getId() == null).collect(Collectors.toList());
        if (addProductDetailList.size() > 0){
            addProductDetailList.forEach(addProductDetail ->{
                addProductDetail.setOrderId(orderItemAddReq.getId());
                //计算产品总价
                BigDecimal amount = addProductDetail.getUnitPrice().multiply(new BigDecimal(addProductDetail.getHairNumber()));
                addProductDetail.setTotalPrice(amount);
            });
            productDetailService.insertOrderProductDetailBatch(addProductDetailList);
            // 将 List<OrderProductDetail> 转换为 Map<Long, String>，处理重复 key
            Map<Long, String> productDetailIdAndCodeMap = addProductDetailList.stream()
                    .collect(Collectors.toMap(
                            OrderProductDetail::getId, // Key: OrderProductDetail 的 id
                            OrderProductDetail::getProductCode, // Value: OrderProductDetail 的 productCode
                            (oldValue, newValue) -> oldValue // 如果 key 重复，保留旧值
                    ));
            //批量新增产品流程记录信息
            procedureRecordService.initProductProcedureRecordByProductCodeAndDetailIdMap(productDetailIdAndCodeMap);
        }
        //入参产品列表中ID不为null得需更新
        List<OrderProductDetail> updateProductDetailList = orderItemAddReq.getDetailList().stream().filter(product -> product.getId() != null).collect(Collectors.toList());
        if(updateProductDetailList.size() > 0){
            updateProductDetailList.forEach(detail -> {
                //计算产品总价
                BigDecimal amount = detail.getUnitPrice().multiply(new BigDecimal(detail.getHairNumber()));
                detail.setTotalPrice(amount);
                detail.setUpdateInfo();
            });
            productDetailService.updateOrderProductDetailBatch(updateProductDetailList);
        }

        //批量更新订单要求信息
        List<OrderItemRequireAddReq> requireAddReqList = orderItemAddReq.getRequireList();
        //查询出原有的订单要求列表
        List<OrderItemRequireAddReq> oldRequireList = orderRequireService.getRequireByOrderId(orderItemAddReq.getId());
        List<Long> oldRequireIdList = oldRequireList.stream().map(OrderItemRequireAddReq::getId).collect(Collectors.toList());
        Set<Long> editRequireIdList = new HashSet<>();
        List<OrderRequire> addOrderRequireList = new ArrayList<>();
        List<OrderRequire> updateOrderRequireList = new ArrayList<>();
        requireAddReqList.forEach(addReq ->{
            OrderRequire orderRequire = new OrderRequire();
            BeanUtils.copyProperties(addReq,orderRequire);
            //将OSS存储地址列表转换为逗号分隔的字符串
            if(addReq.getOssImageUrlList() != null && addReq.getOssImageUrlList().size() > 0){
                String ossImageUrl = String.join(",",addReq.getOssImageUrlList());
                orderRequire.setRequireImageUrl(ossImageUrl);
            }
            orderRequire.setOrderId(orderItemAddReq.getId());
            //若订单要求ID存在则更新，否则新增
            if(orderRequire.getId() != null){
                updateOrderRequireList.add(orderRequire);
                editRequireIdList.add(orderRequire.getId());
            }else{
                addOrderRequireList.add(orderRequire);
            }

        });
        //批量新增和更新要求
        orderRequireService.insertOrderRequireBatch(addOrderRequireList);
        orderRequireService.updateOrderRequireBatch(updateOrderRequireList);
        //若编辑的要求列表中ID不包含在旧要求List中，则需删除旧要求
        // 使用 Stream 筛选
        List<Long> delOrderRequireList = oldRequireIdList.stream()
                .filter(id -> !editRequireIdList.contains(id))
                .collect(Collectors.toList());
        if(delOrderRequireList.size() > 0){
            orderRequireService.deleteByIdList(delOrderRequireList);
            //TODO 批量删除在OSS中存储的这些要求的图片
        }
        return 1;
    }


    @Override
    @Transactional
    public int deleteOrder(List<Long> orderIdList) {
        //删除订单产品流程信息
        procedureRecordService.delRecordByOrderIdList(orderIdList);
        //删除订单产品信息
        productDetailService.delOrderProductDetailByOrderIdList(orderIdList);
        //删除订单的要求信息
        orderRequireService.delOrderRequireByOrderIdList(orderIdList);
        //删除订单信息
        return orderItemMapper.deleteByIdList(orderIdList);
    }

    @Override
    public OrderItem getItemByOrderNo(String orderNo) {
        OrderItem orderItemParam = new OrderItem();
        orderItemParam.setFactoryOrderNo(orderNo);
        List<OrderItem> orderItemList = orderItemMapper.selectOrderItemList(orderItemParam);
        if(orderItemList != null && orderItemList.size() > 0){
            return orderItemList.get(0);
        }
        return null;
    }


    @Override
    public int insertOrderItemBatch(List<OrderItem> orderItemList) {
        return orderItemMapper.insertOrderItemBatch(orderItemList);
    }

    @Override
    public int updateOrderItem(OrderItem orderItem) {
        return orderItemMapper.updateOrderItem(orderItem);
    }

    @Override
    public int updateOrderItemBatch(List<OrderItem> orderItemList) {
        return orderItemMapper.updateOrderItemBatch(orderItemList);
    }

    @Override
    public int deleteById(Long id) {
        return orderItemMapper.deleteById(id);
    }

    @Override
    public OrderItemAddReq getOrderById(Long orderId) {
        //查询订单信息
        OrderItem orderItem = orderItemMapper.selectOrderItemById(orderId);
        OrderItemAddReq result = new OrderItemAddReq();
        //赋值
        BeanUtils.copyProperties(orderItem,result);
        //查询订单详情列表
        OrderProductDetail productDetailParam = new OrderProductDetail();
        productDetailParam.setOrderId(orderId);
        List<OrderProductDetail> detailList = productDetailService.selectOrderProductDetailList(productDetailParam);
        result.setDetailList(detailList);
        //查询订单要求列表
        List<OrderItemRequireAddReq> requireList = orderRequireService.getRequireByOrderId(orderId);
        result.setRequireList(requireList);
        return result;
    }
}
