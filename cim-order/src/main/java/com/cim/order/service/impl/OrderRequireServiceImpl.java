package com.cim.order.service.impl;

import com.cim.common.utils.OSSUtil;
import com.cim.order.domain.OrderRequire;
import com.cim.order.domain.vo.OrderItemRequireAddReq;
import com.cim.order.mapper.OrderRequireMapper;
import com.cim.order.service.IOrderRequireService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 订单要求表 服务实现类
 * </p>
 *
 * @author luwei
 * @since 2020-10-15
 */
@Service
public class OrderRequireServiceImpl implements IOrderRequireService {

    private static final Logger logger = LoggerFactory.getLogger(OrderRequireServiceImpl.class);

    @Resource
    private OSSUtil ossUtil;
    @Autowired
    private OrderRequireMapper orderRequireMapper;

    @Override
    public List<OrderItemRequireAddReq> getRequireByOrderId(Long orderId) {
        //查询出指定订单的订单要求
        OrderRequire orderRequireParam = new OrderRequire();
        orderRequireParam.setOrderId(orderId);
        List<OrderRequire> requireList = orderRequireMapper.selectOrderRequireList(orderRequireParam);
        // 将 List<OrderRequire> 转换为 List<OrderItemRequireAddReq>
        List<OrderItemRequireAddReq> resultList = requireList.stream()
                .map(require -> {
                    OrderItemRequireAddReq result = new OrderItemRequireAddReq();
                    // 将逗号分隔的 url 字符串拆分为 List<String>
                    List<String> imageUrlList = Arrays.asList(require.getRequireImageUrl().split(","));
                    BeanUtils.copyProperties(require,result);
                    result.setOssImageUrlList(imageUrlList);
                    // 创建 B 对象
                    return result;
                })
                .collect(Collectors.toList());
        return resultList;
    }


    @Override
    public List<OrderRequire> selectOrderRequireList(OrderRequire orderRequire) {
        return orderRequireMapper.selectOrderRequireList(orderRequire);
    }

    @Override
    public int insertOrderRequire(OrderRequire orderRequire) {
        return orderRequireMapper.insertOrderRequire(orderRequire);
    }

    @Override
    public int insertOrderRequireBatch(List<OrderRequire> orderRequireList) {
        return orderRequireMapper.insertOrderRequireBatch(orderRequireList);
    }

    @Override
    public int updateOrderRequire(OrderRequire orderRequire) {
        return orderRequireMapper.updateOrderRequire(orderRequire);
    }

    @Override
    public int updateOrderRequireBatch(List<OrderRequire> orderRequireList) {
        return orderRequireMapper.updateOrderRequireBatch(orderRequireList);
    }

    @Override
    public int deleteById(Long id) {
        return orderRequireMapper.deleteById(id);
    }

    /**
     * 批量删除订单要求
     * @param idList
     * @return
     */
    @Override
    public int deleteByIdList(List<Long> idList){
        return orderRequireMapper.deleteByIdList(idList);
    }

    /**
     * 根据订单idList删除所有产品要求信息
     * @param orderIdList
     */
    @Override
    public void delOrderRequireByOrderIdList(List<Long> orderIdList){
        orderRequireMapper.delOrderRequireByOrderIdList(orderIdList);
    }
}
