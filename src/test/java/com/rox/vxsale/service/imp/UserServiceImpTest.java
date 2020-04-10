package com.rox.vxsale.service.imp;

import com.rox.vxsale.entity.User;
import com.rox.vxsale.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author roxBear
 * @creat 2020/4/10
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceImpTest {

   @Autowired
   private UserService userService;

    @Test
    public void Test(){
        User user = new User();
        user.setOpenId("123456");
        user.setUserName("张三三");
        user.setUserNumber("4");
        user.setUserPhone("18627981586");
        user.setUserTable("2");
        int save = userService.save(user);

        User user1 = userService.findByOpenid("123456");
        System.out.println(user1);
    }
}