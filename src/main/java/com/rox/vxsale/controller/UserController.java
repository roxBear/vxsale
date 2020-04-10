package com.rox.vxsale.controller;

import com.rox.vxsale.dto.UserForm;
import com.rox.vxsale.entity.User;
import com.rox.vxsale.exception.SaleErrorCode;
import com.rox.vxsale.exception.SaleException;
import com.rox.vxsale.service.UserService;
import com.rox.vxsale.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * @author roxBear
 * @creat 2020/4/10
 */
@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 客户信息
     * @param form
     * @param bindingResult
     * @return
     */
    @PostMapping("/save")
    public ResultVo create(@Valid UserForm form ,
                           BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("参数不正确, userForm={}", form);
            throw new SaleException(SaleErrorCode.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        User userOld = userService.findByOpenid(form.getOpenId());
        User user = new User();
        if(userOld != null){
            user.setUserId(userOld.getUserId());
        }
        user.setUserName(form.getUserName());
        user.setOpenId(form.getOpenId());
        user.setUserPhone(form.getPhone());
        user.setUserTable(form.getUserTable());
        user.setUserNumber(form.getUserNumber());
        userService.save(user);

        return ResultVo.successOf(user);
    }

    @GetMapping("/getUserInfo")
    public ResultVo getUserInfo(@RequestParam("openid") String openid){
        User user = userService.findByOpenid(openid);
        return ResultVo.successOf(user);
    }
}
