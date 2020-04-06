package com.rox.vxsale.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rox.vxsale.dto.OrderDTO;
import com.rox.vxsale.dto.OrderForm;
import com.rox.vxsale.entity.OrderDetail;
import com.rox.vxsale.exception.SaleErrorCode;
import com.rox.vxsale.exception.SaleException;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author roxBear
 * @creat 2020/4/6
 */
@Slf4j
public class FormConvert {

    public static OrderDTO convert(OrderForm orderForm){
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setUserName(orderForm.getName());
        orderDTO.setUserPhone(orderForm.getPhone());
        orderDTO.setUserOpenid(orderForm.getOpenid());
        orderDTO.setUserAddress(orderForm.getAddress());

        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>() {
                    }.getType());
        } catch (Exception e) {
            log.error("【对象转换】错误, string={}", orderForm.getItems());
            throw new SaleException(SaleErrorCode.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
