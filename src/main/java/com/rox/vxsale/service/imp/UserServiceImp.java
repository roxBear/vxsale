package com.rox.vxsale.service.imp;

import com.rox.vxsale.dto.OrderDTO;
import com.rox.vxsale.service.OrderService;
import com.rox.vxsale.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author roxBear
 * @creat 2020/4/6
 */
@Service
public class UserServiceImp implements UserService {

    @Autowired
    private OrderService orderService;
}
