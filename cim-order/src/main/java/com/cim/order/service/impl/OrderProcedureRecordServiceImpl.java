package com.cim.order.service.impl;

import com.cim.common.constant.ProcedureConstants;
import com.cim.order.domain.OrderProcedureRecord;
import com.cim.order.domain.vo.ProductProcedureOnGoingRes;
import com.cim.order.domain.vo.ProductProcedureStartReq;
import com.cim.order.mapper.OrderProcedureRecordMapper;
import com.cim.order.service.IOrderProcedureRecordService;
import com.cim.procedure.domain.vo.ProcedureOrderInfo;
import com.cim.procedure.domain.vo.ProductProcedureInfo;
import com.cim.procedure.service.IProcedureItemService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 订单流程记录表 服务实现类
 * </p>
 *
 * @author luwei
 * @since 2020-10-06
 */
@Service
@Slf4j
public class OrderProcedureRecordServiceImpl implements IOrderProcedureRecordService {


    @Resource
    private IProcedureItemService procedureItemService;
    @Autowired
    private OrderProcedureRecordMapper orderProcedureRecordMapper;


    /**
     * 批量删除订单流程信息
     * @param orderIdList
     */
    @Override
    public void delRecordByOrderIdList(List<Long> orderIdList){
        orderProcedureRecordMapper.delRecordByOrderIdList(orderIdList);
    }


    @Override
    public int initProductProcedureRecordByProductCodeAndDetailIdMap(Map<Long,String> productIdAndCodeMap) {

        //1、初始化每个产品的流程信息
        List<OrderProcedureRecord> orderProcedureRecordList = new ArrayList<>();
        // 提取所有 productCode 并去重
        List<String> productCodeList = productIdAndCodeMap.values()
                .stream() // 转换为 Stream
                .distinct() // 去重
                .collect(Collectors.toList()); // 转换为 List
        //2、根据产品编码查询出所有产品对应的流程信息
        List<ProductProcedureInfo> productProcedureInfoList = procedureItemService.getProduceItemListByProductCode(productCodeList);
        //3、将产品及流程信息转换为map格
        Map<String, List<ProcedureOrderInfo>> productProcedureMap = productProcedureInfoList.stream()
                .collect(Collectors.toMap(
                        ProductProcedureInfo::getProductCode, // Key: productCode
                        ProductProcedureInfo::getProcedureList, // Value: List<ProcedureOrderInfo>
                        (existing, replacement) -> existing // 去重策略：如果key重复，保留现有的value
                ));
        //3、初始化所有产品的流程
        // 使用 entrySet().stream() 遍历 Map
        productIdAndCodeMap.entrySet().stream().forEach(entry -> {
            Long productId = entry.getKey();
            String productCode = entry.getValue();
            if(!productProcedureMap.containsKey(productCode)){
                throw new RuntimeException("未找到产品编码为：" + productCode + "的流程信息");
            }
            // 这里可以添加其他处理逻辑
            List<ProcedureOrderInfo> procedureList = productProcedureMap.get(productCode);
            ProcedureOrderInfo firstProcedure = procedureList.get(0);
            OrderProcedureRecord record = new OrderProcedureRecord();
            record.setProcedureCode(firstProcedure.getProcedureCode());
            record.setProcedureName(firstProcedure.getProcedureName());
            record.setOrderNum(firstProcedure.getOrderNum());
            record.setProcedureStatus(ProcedureConstants.ProcedureStatusEnum.UNSTART.getStatusCode());// 1未开始 2进行中 3已完成
            record.setIsRework(ProcedureConstants.ReworkEnum.NO.getStatusCode());
            record.setOrderDetailId(productId);
            record.setStartTime(new Date());//初始化产品流程开始时间
            orderProcedureRecordList.add(record);
        });
        return orderProcedureRecordMapper.insertOrderProcedureRecordBatch(orderProcedureRecordList);
    }

    @Override
    public ProductProcedureOnGoingRes getNewestProcedureByProductId(Long productId) {
        ProductProcedureOnGoingRes res =  orderProcedureRecordMapper.newestProcedureByProductId(productId);
        List<String> imageUrlList = new LinkedList<>();
        //获取数据库中存储的流程图片String(逗号分隔)并转换为List
        if(StringUtils.isNotEmpty(res.getProcedureImageUrl())){
            imageUrlList = Arrays.asList(res.getProcedureImageUrl().split(","));
        }
        res.setImageUrlList(imageUrlList);
        return res;
    }

//    @Override
//    public void startProductProcedure(ProductProcedureStartReq req) {
//
//        User user = userService.getUserByPhone(req.getPhone());
//        //若该手机号无对应账户或账户appId和已绑定appId不同，则让重新选择角色并绑定
//        if(user == null || StringUtils.isEmpty(user.getAppId()) || !user.getAppId().equals(req.getAppId())){
//            return StackHttpResponse.error("400","请重新绑定账号");
//        }
//        OrderProcedureRecord record = baseMapper.selectById(req.getId());
//        //若要修改的流程ID与账号绑定的流程ID不同，则不能更改
//        if (StringUtils.isNotEmpty(user.getProcedureId())) {
//            List<Long> procedureIdList = Arrays.stream(user.getProcedureId().split(","))
//                    .map(Long::valueOf)
//                    .collect(Collectors.toList());
//            user.setProcedureIdList(procedureIdList);
//            if(!procedureIdList.contains(record.getProcedureId())){
//                return StackHttpResponse.error("500","暂无权限，请联系管理员");
//            }
//        }
//        record.setStatus(ProcedureConstants.ProcedureStatusEnum.ONGOING.getStatusCode());//该流程状态修改为进行中
//        record.setStartTime(new Date());//更新产品流程的开始时间
//        record.setStartBy(user.getName());
//        record.setEstimateTime(DateUtil.stringToDate(req.getEstimateTime()));//更新产品流程的预估时间
//        baseMapper.updateById(record);
//        ProductProcedureOnGoingRes res = baseMapper.newestProcedureByProductId(record.getOrderDetailId());
//        return StackHttpResponse.success(res);
//    }
//
//    @Override
//    public void endProductProcedure(ProductProcedureEndReq req) {
//        //若该手机号无对应账户或账户appId和已绑定appId不同，则让重新选择角色并绑定
//        User user = userService.getUserByPhone(req.getPhone());
//        if(user == null || StringUtils.isEmpty(user.getAppId()) || !user.getAppId().equals(req.getAppId())){
//            return StackHttpResponse.error("400","请重新绑定账号");
//        }
//        OrderProcedureRecord record = baseMapper.selectById(req.getId());
//        //若要修改的流程ID与账号绑定的流程ID不同，则不能更改
//        if (StringUtils.isNotEmpty(user.getProcedureId())) {
//            List<Long> procedureIdList = Arrays.stream(user.getProcedureId().split(","))
//                    .map(Long::valueOf)
//                    .collect(Collectors.toList());
//            user.setProcedureIdList(procedureIdList);
//            if(!procedureIdList.contains(record.getProcedureId())){
//                return StackHttpResponse.error("500","暂无权限，请联系管理员");
//            }
//        }
//        record.setStatus(ProcedureConstants.ProcedureStatusEnum.FINISH.getStatusCode());//该流程状态修改为已完成
//        record.setFinishTime(new Date());//更新流程的完成时间
//        record.setFinishBy(user.getName());
//        List<String> imageUrlList = req.getImageUrlList();
//        String urlString = StringUtils.join(imageUrlList, ",");//转换流程图片的格式
//        record.setProcedureImageUrl(urlString);
//        baseMapper.updateById(record);//更新当前流程为完成状态
//        //开始下一个流程
//        Integer preProcedureOrder = record.getProcedureOrder();
//        //获取下一个流程的详细信息
//        ProcedureItem nextProcedureItem = procedureItemService.nextProItem(preProcedureOrder);
//        OrderProcedureRecord nextRecord = new OrderProcedureRecord();
//        //若查询下一流程存在即完成的不是最后一个流程，则新增流程
//        if(nextProcedureItem != null){
//            nextRecord.setStatus(ProcedureConstants.ProcedureStatusEnum.UNSTART.getStatusCode());
//            nextRecord.setStartTime(new Date());
//            nextRecord.setProcedureOrder(nextProcedureItem.getProcedureOrder());
//            nextRecord.setProcedureId(nextProcedureItem.getId());
//            nextRecord.setOrderDetailId(record.getOrderDetailId());
//            nextRecord.setProcedureName(nextProcedureItem.getProcedureName());
//            baseMapper.insert(nextRecord);
//        }
//        //查询该产品最新的流程信息
//        ProductProcedureOnGoingRes res = baseMapper.newestProcedureByProductId(record.getOrderDetailId());
//
//        return StackHttpResponse.success(res);
//    }

    @Override
    public int insertOrderProcedureRecord(OrderProcedureRecord record) {
        return orderProcedureRecordMapper.insertOrderProcedureRecord(record);
    }

    @Override
    public int insertOrderProcedureRecordBatch(List<OrderProcedureRecord> recordList) {
        return orderProcedureRecordMapper.insertOrderProcedureRecordBatch(recordList);
    }

    @Override
    public int deleteById(Long id) {
        return orderProcedureRecordMapper.deleteById(id);
    }

    // 根据产品idList批量删除产品流程记录
    @Override
    public int deleteByOrderDetailIdBatch(List<Long> orderDetailIdList){
        return orderProcedureRecordMapper.deleteByOrderDetailIdBatch(orderDetailIdList);
    }

    @Override
    public int updateOrderProcedureRecord(OrderProcedureRecord record) {
        return orderProcedureRecordMapper.updateOrderProcedureRecord(record);
    }

    @Override
    public int updateOrderProcedureRecordBatch(List<OrderProcedureRecord> recordList) {
        return orderProcedureRecordMapper.updateOrderProcedureRecordBatch(recordList);
    }

    @Override
    public OrderProcedureRecord selectById(Long id) {
        return orderProcedureRecordMapper.selectById(id);
    }

    @Override
    public List<OrderProcedureRecord> selectOrderProcedureRecordList(OrderProcedureRecord orderProcedureRecord) {
        return orderProcedureRecordMapper.selectOrderProcedureRecordList(orderProcedureRecord);
    }


}
