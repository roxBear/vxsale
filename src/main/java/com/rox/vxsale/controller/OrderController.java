package com.rox.vxsale.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rox.vxsale.dto.OrderDTO;
import com.rox.vxsale.dto.OrderForm;
import com.rox.vxsale.exception.SaleErrorCode;
import com.rox.vxsale.exception.SaleException;
import com.rox.vxsale.service.OrderService;
import com.rox.vxsale.utils.FormConvert;
import com.rox.vxsale.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.transform.Result;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author roxBear
 * @creat 2020/4/6
 */
@RestController
@Slf4j
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    //创建订单
    @PostMapping("/create")
    public ResultVo<Map<String, String>> create(@Valid OrderForm orderForm,
                                                BindingResult bindingResult) {/*BindingResult与Valid结合验证表单信息*/

        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确, orderForm={}", orderForm);
            throw new SaleException(SaleErrorCode.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDTO orderDTO = FormConvert.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】购物车不能为空");
            throw new SaleException(SaleErrorCode.CART_EMPTY);
        }

        OrderDTO create = orderService.create(orderDTO);

        Map<String, String> map = new HashMap<>();
        /*订单id和生成订单DTO返回*/
        map.put("orderId", create.getOrderId());

        return ResultVo.successOf(map);
    }


    //订单列表
    @GetMapping("/listAll")
    public ResultVo<List<OrderDTO>> list(@RequestParam(value = "openid",required = false) String openid,
                                         @RequestParam(value = "page" , defaultValue = "1") Integer page ,
                                         @RequestParam(value = "size" , defaultValue = "8") Integer size){
        if (StringUtils.isEmpty(openid)) {
            log.error("【查询订单列表】openid为空");
            throw new SaleException(SaleErrorCode.USER_NOT_FOUND);
        }
        String orderBy = "create_time desc";
        PageHelper.startPage(page,size,orderBy);
        List<OrderDTO> orderDTOPage = orderService.findByOpenId(openid);
        PageInfo<OrderDTO> pageInfo = new PageInfo<OrderDTO>(orderDTOPage);
        return ResultVo.successOf(pageInfo);
    }

    //订单详情
    @GetMapping("/detail")
    public ResultVo detail(@RequestParam("orderid") String orderId,
                           @RequestParam(value = "openid",required = false) String openId){
          //TODO
        OrderDTO orderDTO = orderService.findByOrderId(orderId);
        return ResultVo.successOf(orderDTO);
    }


    //订单取消
    @GetMapping("/cancel")
    public ResultVo cancel(@RequestParam("orderId") String orderId,
                           @RequestParam(value = "openid",required = false) String openId){
        OrderDTO orderDTO = orderService.findByOrderId(orderId);
        orderService.cancelOrder(orderDTO);
        return ResultVo.successOf();
    }

}
