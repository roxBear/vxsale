package com.rox.vxsale.mapper;

import com.rox.vxsale.entity.SellerInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author roxBear
 * @creat 2020/4/9
 */
@Mapper
public interface SellerInfoMapper {

    @Select("select * from seller_info where phone = #{phone}")
    SellerInfo findOne(String phone);

    @Select("select * from seller_info where seller_id = #{sellerId}")
    SellerInfo findById(Integer sellerId);

    @Select("select * from seller_info")
    List<SellerInfo> findAll();

    @Insert("insert into seller_info (username,password,phone) values(#{userName},#{passWord},#{phone})")
    int create(SellerInfo sellerInfo);

    @Update("update seller_info set username=#{userName},password=#{passWord} where phone=#{phone}")
    int update(SellerInfo sellerInfo);

}
