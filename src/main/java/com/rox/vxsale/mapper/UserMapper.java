package com.rox.vxsale.mapper;

import com.rox.vxsale.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author roxBear
 * @creat 2020/4/10
 */
@Mapper
public interface UserMapper {

    @Select("select * from `user` where openid = #{openId}")
    User findByOpenid(String openId);

    @Insert("insert into `user`(user_name,user_phone,openid,user_table,user_number)" +
            "values(#{userName},#{userPhone},#{openId},#{userTable},#{userNumber})")
    int save(User user);
}
