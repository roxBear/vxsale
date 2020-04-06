package com.rox.vxsale.mapper;

import com.rox.vxsale.entity.OrderDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author roxBear
 * @creat 2020/4/4
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderDetailMapperTest {

    @Autowired
    private OrderDetailMapper detailMapper;

    @Test
    public void create(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("1234567810");
        orderDetail.setOrderId("11111112");
        orderDetail.setProductIcon("http://xxxx.jpg");
        orderDetail.setProductId("11111112");
        orderDetail.setProductName("皮蛋粥");
        orderDetail.setProductPrice(new BigDecimal(2.2));
        orderDetail.setProductQuantity(3);
        int count = detailMapper.create(orderDetail);
        System.out.println(count);
    }

    @Test
    public void findByOrderId(){
        List<OrderDetail> orderDetailList = detailMapper.findByOrderId("202004052690");
        for (OrderDetail orderDetail : orderDetailList) {
            System.out.println(orderDetail);
        }
    }
}