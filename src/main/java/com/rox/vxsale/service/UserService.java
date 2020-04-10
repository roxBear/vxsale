package com.rox.vxsale.service;

import com.rox.vxsale.entity.User;

/**
 * @author roxBear
 * @creat 2020/4/6
 */
public interface UserService {

    public User findByOpenid(String openId);

    public int save(User user);
}
