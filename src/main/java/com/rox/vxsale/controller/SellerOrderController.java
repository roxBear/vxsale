package com.rox.vxsale.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rox.vxsale.dto.OrderDTO;
import com.rox.vxsale.exception.SaleErrorCode;
import com.rox.vxsale.exception.SaleException;
import com.rox.vxsale.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 买家端订单
 * @author roxBear
 * @creat 2020/4/7
 */
@Controller
@Slf4j
@RequestMapping("/seller/order")
public class SellerOrderController {

    @Autowired
    private OrderService orderService;



    /**
     * 买家订单查询-全
     * @param page
     * @param size
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page" , defaultValue = "1") Integer page ,
                             @RequestParam(value = "size" , defaultValue = "8") Integer size ,
                             Map<String , Object> map){
//        PageRequest pageRequest = new PageRequest(page - 1 , size);
//        Page<OrderDTO> orderDTOPage = orderService.findList(pageRequest);
//        Page<OrderDTO> orderDTOPage = orderService.findOrderMastersByCreateTimeDesc(pageRequest);
//        map.put("orderDTOPage" , orderDTOPage);
//        map.put("currentPage" , page);
//        map.put("size" , size);

        String orderBy = "create_time desc";
        PageHelper.startPage(page,size,orderBy);
        List<OrderDTO> orderDTOList = orderService.findAll();
        PageInfo<OrderDTO> pageInfo = new PageInfo<OrderDTO>(orderDTOList);
        map.put("orderDTOPage" , pageInfo);
        map.put("currentPage" , pageInfo.getPageNum());
        map.put("size" , size);
        return new ModelAndView("/order/list" , map);
    }


    /**
     * 取消订单
     * @param orderId 订单的ID
     * @return
     */
    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam("orderId") String orderId ,
                               Map<String , Object> map){
        try{
            OrderDTO orderDTO = orderService.findByOrderId(orderId);
            orderService.cancelOrder(orderDTO);
        }catch (SaleException e){
            log.error("【卖家端取消订单】 发生异常{}" , e);
            map.put("msg" , SaleErrorCode.ORDER_NOT_EXIST.getMessage());
            map.put("url" , "/sale/seller/order/list");
            return new ModelAndView("common/error" , map);
        }
        map.put("msg" , SaleErrorCode.ORDER_CANCEL_SUCCESS.getMessage());
        map.put("url" , "/sale/seller/order/list");
        return new ModelAndView("common/success" , map);
    }

    /**
     * 订单详情
     * @param orderId
     * @return
     */
    @GetMapping("detail")
    public ModelAndView detail(@RequestParam("orderId") String orderId ,
                               Map<String , Object> map) {
        OrderDTO orderDTO = new OrderDTO();
        try {
            orderDTO = orderService.findByOrderId(orderId);
        } catch (SaleException e) {
            log.error("【卖家端查询订单详情】 发生异常{}", e);
            map.put("msg", SaleErrorCode.ORDER_NOT_EXIST.getMessage());
            map.put("url", "/sale/seller/order/list");
            return new ModelAndView("common/error", map);
        }
        map.put("orderDTO" , orderDTO);
        return new ModelAndView("/sale/order/detail", map);
    }

    /**
     * 订单完结
     * @param orderId
     * @param map
     * @return
     */
    @GetMapping("finish")
    public ModelAndView finish(@RequestParam("orderId") String orderId ,
                               Map<String , Object> map){
        try {
            OrderDTO orderDTO = orderService.findByOrderId(orderId);
            orderService.finishOrder(orderDTO);
        }catch (SaleException e){
            log.error("【卖家端完结订单】 发生异常{}", e);
            map.put("msg" , SaleErrorCode.ORDER_NOT_EXIST.getMessage());
            map.put("url" , "/sale/seller/order/list");
            return new ModelAndView("common/error" , map);
        }
        map.put("msg" , SaleErrorCode.ORDER_FINISH_SUCCESS.getMessage());
        map.put("url" , "/sale/seller/order/list");
        return new ModelAndView("common/success" , map);
    }


    @PostMapping("search")
    public ModelAndView search(HttpServletRequest request ,
                               HttpServletResponse response ,
                               Map<String , Object> map) throws IOException {
        String orderId = request.getParameter("search");
        if (orderId == null || orderId == ""){
            response.sendRedirect(request.getContextPath() + "/seller/order/list");
            return null;
        }
        OrderDTO orderDTO = orderService.findByOrderId(orderId);
        map.put("orderDTO" , orderDTO);
        return new ModelAndView("/order/list1" , map);
    }

}
