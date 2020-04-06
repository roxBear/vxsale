package com.rox.vxsale.mapper;

import com.rox.vxsale.entity.Order;
import com.rox.vxsale.enums.OrderStatus;
import com.rox.vxsale.enums.PayStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

/**
 * @author roxBear
 * @creat 2020/4/4
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderMapperTest {

    @Autowired
    private OrderMapper orderMapper;
    @Test
    public void createTest(){

        Order order = new Order();
        order.setOrderId(UUID.randomUUID().toString());
        order.setUserName("rox");
        order.setUserPhone("13099991245");
        order.setUserAddress("二期D栋");
        order.setUserOpenid("110110");
        order.setOrderAmount(new BigDecimal(3.2));
        order.setOrderStatus(OrderStatus.NEW.getCode());
        order.setPayStatus(PayStatus.WAIT.getCode());
        order.setCreateTime(new Timestamp(System.currentTimeMillis()));
        order.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        int count = orderMapper.create(order);
        System.out.println(count);
    }

    @Test
    public void findByOpenid(){
        List<Order> orderList = orderMapper.findByOpenid("110110");
        for (Order order : orderList) {
            System.out.println(order);
        }
    }
}