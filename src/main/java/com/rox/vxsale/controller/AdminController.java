package com.rox.vxsale.controller;

import com.rox.vxsale.entity.SellerInfo;
import com.rox.vxsale.exception.SaleErrorCode;
import com.rox.vxsale.exception.SaleException;
import com.rox.vxsale.mapper.SellerInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author roxBear
 * @creat 2020/4/10
 */
@Controller
@RequestMapping("/admin")
@Slf4j
public class AdminController {

    @Autowired
    private SellerInfoMapper sellerInfoMapper;

    @GetMapping("/login")
    public String loginAdmin(@RequestParam("phone") String phone ,
                             @RequestParam("password") String password ,
                             HttpServletResponse response){
        SellerInfo sellerInfo = sellerInfoMapper.findOne(phone);
        log.info("【商家登录】 商家信息={}" , sellerInfo);
        if(sellerInfo != null && sellerInfo.getPassword().equals(password)){
            String token = UUID.randomUUID().toString();
            log.info("【商家登录】 登录成功的token = {}" , token);
            //Integer expire = RedisConstant.EXPIRE;
            //设置token至cookie
            //CookieUtil.set(response , CookieConstant.TOKEN , token , expire);
            return "登录成功";
        }else{
            throw new SaleException(SaleErrorCode.LOGIN_FAIL);
        }
    }
}
