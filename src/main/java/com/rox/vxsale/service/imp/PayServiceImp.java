package com.rox.vxsale.service.imp;

import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;
import com.rox.vxsale.dto.OrderDTO;
import com.rox.vxsale.entity.Order;
import com.rox.vxsale.enums.OrderStatus;
import com.rox.vxsale.enums.PayStatus;
import com.rox.vxsale.exception.SaleErrorCode;
import com.rox.vxsale.exception.SaleException;
import com.rox.vxsale.mapper.OrderMapper;
import com.rox.vxsale.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author roxBear
 * @creat 2020/4/7
 */
@Service
@Slf4j
public class PayServiceImp implements PayService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public OrderDTO goPay(OrderDTO orderDTO) {
        //判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatus.NEW.getCode())){
            log.error("【支付订单】订单状态不正确, orderId={}, orderStatus={}", orderDTO.getOrderId(),
                    orderDTO.getOrderStatus());
            throw new SaleException(SaleErrorCode.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        Order order = new Order();
        /*支付,省了得了*/
        //TODO
        orderDTO.setOrderStatus(OrderStatus.PAYED.getCode());
        orderDTO.setPayStatus(PayStatus.SUCCESS.getCode());
        BeanUtils.copyProperties(orderDTO,order);
        //更新订单数据库信息
        int count = orderMapper.update(order);
        if(count != 1){
            log.error("【支付订单】更新失败, order={}", order);
            throw new SaleException(SaleErrorCode.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }

    @Override
    public PayResponse notify(String notifyData) {
        return null;
    }

    @Override
    public RefundResponse refund(OrderDTO orderDTO) {
        return null;
    }
}
