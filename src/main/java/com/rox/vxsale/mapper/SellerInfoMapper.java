package com.rox.vxsale.mapper;

import com.rox.vxsale.entity.SellerInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author roxBear
 * @creat 2020/4/9
 */
@Mapper
public interface SellerInfoMapper {

    @Select("select * from seller_info where phone = #{phone}")
    SellerInfo findOne(String phone);
}
