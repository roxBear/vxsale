package com.rox.vxsale.service;

import com.rox.vxsale.dto.OrderDTO;

import java.util.List;

/**
 * @author roxBear
 * @creat 2020/4/4
 */
public interface OrderService {

    /*创建订单*/
    OrderDTO create(OrderDTO orderDTO);

    /*查询单个订单*/
    OrderDTO findByOrderId(String orderId);

    /*查询订单列表-用户*/
    List<OrderDTO> findByOpenId(String openId);

    /*查询所有订单-商户*/
    List<OrderDTO> findAll();

    /*取消订单*/
    OrderDTO cancelOrder(OrderDTO orderDTO);

    /*支付订单*/
    OrderDTO payOrder(OrderDTO orderDTO);

    /*完成订单*/
    OrderDTO finishOrder(OrderDTO orderDTO);

}
