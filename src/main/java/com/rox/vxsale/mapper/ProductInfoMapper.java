package com.rox.vxsale.mapper;

import com.rox.vxsale.entity.ProductInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author roxBear
 * @creat 2020/4/3
 */
@Mapper
public interface ProductInfoMapper {

    @Select("select * from product_info where product_status = #{productStatus}")
    List<ProductInfo> findByStatus(Integer productStatus);

    @Select("select * from product_info where product_id = #{productId}")
    ProductInfo findById(String productId);

    @Select("select * from product_info where category_num = #{categoryNum}")
    List<ProductInfo> findByCategoryNum(Integer categoryNum);

    @Insert("insert into product_info (product_id,product_name,product_price,product_stock,product_description,product_icon,product_status,category_num,create_time,update_time)" +
            "values(#{productId},#{productName},#{productPrice},#{productStock},#{productDescription},#{productIcon},#{productStatus},#{categoryNum},#{createTime},#{updateTime})")
    int saveOne(ProductInfo productInfo);

    @Select("select * from product_info")
    List<ProductInfo> findAll();

    @Update("update product_info set  product_stock = #{productStock} where product_id = #{productId}")
    void updateStock(ProductInfo productInfo);

    @Update("update product_info set  product_status = #{productStatus} where product_id = #{productId}")
    int updateStatus(ProductInfo productInfo);

    @Delete("delete from product_info where product_id = #{productId}")
    int delete(String productId);
}
