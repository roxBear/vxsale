package com.rox.vxsale.controller;

import com.rox.vxsale.constant.CookieConstant;
import com.rox.vxsale.constant.RedisConstant;
import com.rox.vxsale.dto.SellerForm;
import com.rox.vxsale.entity.SellerInfo;
import com.rox.vxsale.exception.SaleErrorCode;
import com.rox.vxsale.exception.SaleException;
import com.rox.vxsale.service.SellerService;
import com.rox.vxsale.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
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
    private SellerService sellerService;

    @GetMapping("/loginAdmin")
    public ModelAndView loginAdmin(@RequestParam("phone") String phone ,
                                   @RequestParam("password") String password ,
                                   HttpServletResponse response,
                                   Map<String, Object> map){
        SellerInfo sellerInfo = sellerService.findOne(phone);
        if (sellerInfo == null) {
            map.put("msg", SaleErrorCode.LOGIN_FAIL.getMessage());
            map.put("url", "/sale/seller/order/list");
            return new ModelAndView("common/error",map);
        }

        //2. 设置token至redis
        String token = UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE;

        /*redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token), openid, expire, TimeUnit.SECONDS);*/

        //3. 设置token至cookie
        CookieUtil.set(response, CookieConstant.TOKEN, token, expire);

        return new ModelAndView("redirect:localhost:8888/seller/order/list");
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,
                               HttpServletResponse response,
                               Map<String, Object> map) {
        //1. 从cookie里查询
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie != null) {
            //2. 清除redis
            /*redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));*/
            //3. 清除cookie
            CookieUtil.set(response, CookieConstant.TOKEN, null, 0);
        }

        map.put("msg", SaleErrorCode.LOGOUT_SUCCESS.getMessage());
        map.put("url", "/sale/seller/order/list");
        return new ModelAndView("common/success", map);
    }

    @GetMapping("/list")
    public ModelAndView list(Map<String , Object> map){
        List<SellerInfo> sellerInfoList = sellerService.findAll();
        map.put("sellerInfoList" , sellerInfoList);
        return new ModelAndView("admin/list" , map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "sellerId" , required = false) Integer sellerId ,
                              Map<String , Object> map){
        if(!StringUtils.isEmpty(sellerId)){
            SellerInfo sellerInfo = sellerService.findById(sellerId);
            map.put("sellerInfo" , sellerInfo);
        }
        return new ModelAndView("admin/index" , map);
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid SellerForm form ,
                             BindingResult bindingResult ,
                             Map<String , Object> map){
        if(bindingResult.hasErrors()){
            map.put("msg" , bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sale/admin/index");
            return new ModelAndView("common/error" , map);
        }
        try{
            SellerInfo sellerInfo = sellerService.findOne(form.getPhone());
            if(sellerInfo != null){
                BeanUtils.copyProperties(form , sellerInfo);
                sellerService.update(sellerInfo);
            }else{
                SellerInfo sellerInfoNew = new SellerInfo();
                BeanUtils.copyProperties(form,sellerInfoNew);
                sellerService.create(sellerInfoNew);
            }
        }catch (SaleException e){
            map.put("msg", e.getMessage());
            map.put("url", "/sale/admin/index");
            return new ModelAndView("common/error", map);
        }
        map.put("msg" , "操作成功!");
        map.put("url" , "/sale/admin/list");
        return new ModelAndView("common/success" , map);
    }
}
