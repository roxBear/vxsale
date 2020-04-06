package com.rox.vxsale.service.imp;

import com.rox.vxsale.dto.CarDTO;
import com.rox.vxsale.dto.OrderDTO;
import com.rox.vxsale.entity.Order;
import com.rox.vxsale.entity.OrderDetail;
import com.rox.vxsale.entity.ProductInfo;
import com.rox.vxsale.enums.OrderStatus;
import com.rox.vxsale.enums.PayStatus;
import com.rox.vxsale.exception.SaleErrorCode;
import com.rox.vxsale.exception.SaleException;
import com.rox.vxsale.mapper.OrderDetailMapper;
import com.rox.vxsale.mapper.OrderMapper;
import com.rox.vxsale.service.OrderService;
import com.rox.vxsale.service.ProductInfoService;
import com.rox.vxsale.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author roxBear
 * @creat 2020/4/4
 */
@Service
@Slf4j
public class OrderServiceImp implements OrderService {

    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private OrderMapper orderMapper;


    /**
     * 创建订单
     * controller层封装前端信息成orderDTO传入
     * @param orderDTO
     * @return
     */
    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {

        String orderId = KeyUtil.getUniqueKey();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        Random random = new Random();

        /*查询订单DTO中的每一种商品和数量,即购物车中信息*/
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            ProductInfo productInfo = productInfoService.findOne(orderDetail.getProductId());
            if(productInfo == null){
                throw new SaleException(SaleErrorCode.PRODUCT_NOT_EXIST);
            }

            /*计算总价*/
            orderAmount = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);

            /*存订单详情表order_detail*/
            int flag = random.nextInt(90) + 10;
            //orderDetail.setDetailId(KeyUtil.getUniqueKey());
            orderDetail.setDetailId(orderId+"_"+flag);
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo,orderDetail);
            orderDetailMapper.create(orderDetail);

        }

        /*写入订单数据库order*/
        Order order = new Order();
        orderDTO.setOrderId(orderId);/*先设置id再copy否则会null*/
        /*使用BeanUtils copy时先拷贝id字段,再拷贝属性*/
        BeanUtils.copyProperties(orderDTO,order);
        order.setOrderAmount(orderAmount);
        order.setOrderStatus(OrderStatus.NEW.getCode());
        order.setPayStatus(PayStatus.WAIT.getCode());
        orderMapper.create(order);

        /*扣库存*/
        List<CarDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e ->
                new CarDTO(e.getProductId(), e.getProductQuantity())
        ).collect(Collectors.toList());
        productInfoService.decreaseStock(cartDTOList);

        return orderDTO;
    }


    /**
     * 单个订单查询
     * 查询到的数据统一为DTO方便在各层传输
     * @param orderId
     * @return
     */
    @Override
    public OrderDTO findByOrderId(String orderId) {
        Order order = orderMapper.findByOrderId(orderId);
        if(order == null){
            throw new SaleException(SaleErrorCode.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetailList = orderDetailMapper.findByOrderId(orderId);
        if(CollectionUtils.isEmpty(orderDetailList)){
            throw new SaleException(SaleErrorCode.ORDERDETAIL_NOT_EXIST);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(order,orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    /**
     * 查询openID下的所有订单
     * @param openId
     * @return
     */
    @Override
    public List<OrderDTO> findByOpenId(String openId) {
        List<OrderDTO> orderDTOList = new ArrayList<>();
        List<Order> orderList = orderMapper.findByOpenid(openId);
        for (Order order : orderList) {
            OrderDTO orderDTO = new OrderDTO();
            List<OrderDetail> detailList = orderDetailMapper.findByOrderId(order.getOrderId());
            orderDTO.setOrderId(order.getOrderId());
            orderDTO.setOrderDetailList(detailList);
            BeanUtils.copyProperties(order,orderDTO);
            orderDTOList.add(orderDTO);
        }
        return orderDTOList;
    }

    /**
     * 查询所有,后台管理
     * @return
     */
    @Override
    public List<OrderDTO> findAll() {

        List<OrderDTO> orderDTOList = new ArrayList<>();
        List<Order> orderList = orderMapper.findAll();
        for (Order order : orderList) {
            OrderDTO orderDTO = new OrderDTO();
            List<OrderDetail> details = orderDetailMapper.findByOrderId(order.getOrderId());
            orderDTO.setOrderId(order.getOrderId());
            orderDTO.setOrderDetailList(details);
            BeanUtils.copyProperties(order,orderDTO);
            orderDTOList.add(orderDTO);
        }
        return orderDTOList;
    }


    /**
     * 取消订单
     * @param orderDTO
     * @return
     */
    @Override
    @Transactional
    public OrderDTO cancelOrder(OrderDTO orderDTO) {
        /*判断订单状态*/
        if(!orderDTO.getOrderStatus().equals(OrderStatus.NEW.getCode())){
            log.error("【取消订单】 订单状态错误, orderId={}, orderStatus={}");
            throw new SaleException(SaleErrorCode.ORDER_STATUS_ERROR);
        }
        /*修改订单状态*/
        orderDTO.setOrderStatus(OrderStatus.CANCEL.getCode());
        Order order = new Order();
        BeanUtils.copyProperties(orderDTO,order);
        int flag = orderMapper.update(order);
        if(flag != 1){
            log.error("【取消订单】更新失败");
        }
        /*返回库存*/
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            throw new SaleException(SaleErrorCode.ORDER_DETAIL_EMPTY);
        }
        List<CarDTO> carDTOList = orderDTO.getOrderDetailList().stream()
                .map(orderDetail -> new CarDTO(orderDetail.getProductId(),orderDetail.getProductQuantity()))
                .collect(Collectors.toList());
        productInfoService.increaseStock(carDTOList);

        /*退款*/
        if(orderDTO.getPayStatus().equals(PayStatus.SUCCESS.getCode())){
            //TODO
        }

        return orderDTO;
    }

    /**
     * 订单支付
     * @param orderDTO
     * @return
     */
    @Override
    public OrderDTO payOrder(OrderDTO orderDTO) {
        if(!orderDTO.getOrderStatus().equals(OrderStatus.NEW.getCode())){
            log.error("【订单支付】订单状态不正确");
            throw new SaleException(SaleErrorCode.ORDER_STATUS_ERROR);
        }
        if(!orderDTO.getPayStatus().equals(PayStatus.WAIT.getCode())){
            log.error("【订单支付】订单支付状态不正确");
            throw new SaleException(SaleErrorCode.ORDER_STATUS_ERROR);
        }
        orderDTO.setPayStatus(PayStatus.SUCCESS.getCode());
        Order order = new Order();
        BeanUtils.copyProperties(orderDTO,order);
        int flag = orderMapper.update(order);
        if(flag != 1){
            log.error("【订单修改】修改订单状态出错");
        }
        return orderDTO;
    }


    /**
     * 订单完成
     * @param orderDTO
     * @return
     */
    @Override
    public OrderDTO finishOrder(OrderDTO orderDTO) {
        if(!orderDTO.getOrderStatus().equals(OrderStatus.NEW.getCode())){
            log.error("【订单完结】订单状态不正确");
            throw new SaleException(SaleErrorCode.ORDER_STATUS_ERROR);
        }
        /*修改订单状态*/
        orderDTO.setOrderStatus(OrderStatus.FINISHED.getCode());
        Order order = new Order();
        BeanUtils.copyProperties(orderDTO,order);
        int flag = orderMapper.update(order);
        if(flag != 1){
            log.error("【订单修改】修改订单状态出错");
        }
        return orderDTO;
    }

}
