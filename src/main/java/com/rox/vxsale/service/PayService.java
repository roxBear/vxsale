package com.rox.vxsale.service;

import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;
import com.rox.vxsale.dto.OrderDTO;

/**
 * @author roxBear
 * @creat 2020/4/7
 */
public interface PayService {

    OrderDTO goPay(OrderDTO orderDTO);

    PayResponse notify(String notifyData);

    RefundResponse refund(OrderDTO orderDTO);
}
