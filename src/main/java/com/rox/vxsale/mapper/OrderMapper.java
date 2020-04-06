package com.rox.vxsale.mapper;

import com.rox.vxsale.dto.OrderDTO;
import com.rox.vxsale.entity.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author roxBear
 * @creat 2020/4/4
 */
@Mapper
public interface OrderMapper {

    @Insert("insert into `order`(order_id,user_name,user_phone,user_address,user_openid,order_amount,order_status,pay_status)" +
            "values (#{orderId},#{userName},#{userPhone},#{userAddress},#{userOpenid},#{orderAmount},#{orderStatus},#{payStatus})")
    int create(Order order);

    @Select("select * from `order` where user_openid = #{openId}")
    List<Order> findByOpenid(String openId);

    @Select("select * from `order` where order_id = #{orderId}")
    Order findByOrderId(String orderId);

    @Select("select * from `order`")
    List<Order> findAll();

    @Update("update `order` set order_status = #{orderStatus},pay_status = #{payStatus} where order_id = #{orderId}")
    int update(Order order);
}
