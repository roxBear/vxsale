package com.rox.vxsale.mapper;

import com.rox.vxsale.entity.OrderDetail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author roxBear
 * @creat 2020/4/4
 */
@Mapper
public interface OrderDetailMapper {

    @Insert("insert into order_detail (detail_id,order_id,product_id,product_name,product_price,product_quantity,product_icon)" +
            "values(#{detailId},#{orderId},#{productId},#{productName},#{productPrice},#{productQuantity},#{productIcon})")
    int create(OrderDetail orderDetail);

    @Select("select * from order_detail where order_id = #{orderId}")
    List<OrderDetail> findByOrderId(String orderId);

}
