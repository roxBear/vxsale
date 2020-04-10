package com.rox.vxsale.service.imp;

import com.rox.vxsale.dto.OrderDTO;
import com.rox.vxsale.entity.OrderDetail;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author roxBear
 * @creat 2020/4/5
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class OrderServiceImpTest {

    @Autowired
    private OrderServiceImp orderService;
    private final String user_openid = "1101110";


    @Test
    public void create() {

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setUserName("任我行");
        orderDTO.setUserPhone("13007157509");
        orderDTO.setUserAddress("一期A栋");
        orderDTO.setUserOpenid("1201120");

        /*购物车*/
        List<OrderDetail> orderDetails = new ArrayList<>();
        OrderDetail od1 = new OrderDetail();
        od1.setProductId("040302");
        od1.setProductQuantity(1);

        OrderDetail od2 = new OrderDetail();
        od2.setProductId("040304");
        od2.setProductQuantity(4);

        /*OrderDetail od3 = new OrderDetail();
        od3.setProductId("040305");
        od3.setProductQuantity(8);*/

        orderDetails.add(od1);
        orderDetails.add(od2);
        //orderDetails.add(od3);

        orderDTO.setOrderDetailList(orderDetails);
        OrderDTO dto = orderService.create(orderDTO);
        log.info("【创建订单】result={}", dto);
    }

    @Test
    public void findByOrderId() {
        OrderDTO byOrderId = orderService.findByOrderId("202004052690");
        System.out.println(byOrderId);
    }

    @Test
    public void findByOpenId() {
        List<OrderDTO> orderDTOList = orderService.findByOpenId("1101110");
        for (OrderDTO orderDTO : orderDTOList) {
            System.out.println(orderDTO);
        }
    }

    @Test
    public void findAll() {
        List<OrderDTO> orderDTOList = orderService.findAll();
        for (OrderDTO orderDTO : orderDTOList) {
            System.out.println(orderDTO);
        }
    }

    @Test
    public void cancel() {
        OrderDTO orderDTO = orderService.findByOrderId("202004057117");
        OrderDTO orderDTO_CANCEL = orderService.cancelOrder(orderDTO);
        System.out.println(orderDTO_CANCEL);
    }

    @Test
    public void finish() {
        OrderDTO orderDTO = orderService.findByOrderId("202004057117");
        OrderDTO orderDTO_FINISH = orderService.finishOrder(orderDTO);
        System.out.println(orderDTO_FINISH);
    }

    @Test
    public void pay() {
        OrderDTO orderDTO = orderService.findByOrderId("202004057117");
        OrderDTO orderDTO_PAY = orderService.payOrder(orderDTO);
        System.out.println(orderDTO_PAY);
    }
}