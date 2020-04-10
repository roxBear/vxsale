package com.rox.vxsale.service.imp;

import com.rox.vxsale.entity.User;
import com.rox.vxsale.mapper.UserMapper;
import com.rox.vxsale.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author roxBear
 * @creat 2020/4/10
 */
@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserMapper userMapper;

    public User findByOpenid(String openId) {
        return userMapper.findByOpenid(openId);
    }

    public int save(User user) {
        int count = userMapper.save(user);
        return count;
    }


}
