package com.rox.vxsale.controller;

import com.lly835.bestpay.model.PayResponse;
import com.rox.vxsale.dto.OrderDTO;
import com.rox.vxsale.enums.OrderStatus;
import com.rox.vxsale.exception.SaleErrorCode;
import com.rox.vxsale.exception.SaleException;
import com.rox.vxsale.service.OrderService;
import com.rox.vxsale.service.PayService;
import com.rox.vxsale.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author roxBear
 * @creat 2020/4/7
 */
@Controller
@Slf4j
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private PayService payService;

    @GetMapping("goPay")
    public ResultVo<Boolean> goPay(@RequestParam("orderId") String orderId){

        OrderDTO orderDTO = orderService.findByOrderId(orderId);
        if (orderDTO == null) {
            throw new SaleException(SaleErrorCode.ORDER_NOT_EXIST);
        }

        //发起支付
        OrderDTO payOrderDTO = payService.goPay(orderDTO);
        if(!orderDTO.getOrderStatus().equals(OrderStatus.PAYED.getCode())){
            log.error("【支付订单】订单状态不正确,  orderStatus={}", payOrderDTO.getOrderStatus());
            throw new SaleException(SaleErrorCode.ORDER_STATUS_ERROR);
        }
        return ResultVo.successOf(true);
    }

    /**
     * 微信异步通知
     * @param notifyData
     */
    @PostMapping("notify")
    public ModelAndView notify(@RequestBody String notifyData) {
        payService.notify(notifyData);
        //返回给微信处理结果
        return new ModelAndView("pay/success");
    }
}
