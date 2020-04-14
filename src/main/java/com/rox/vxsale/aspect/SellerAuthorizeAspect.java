package com.rox.vxsale.aspect;

import com.rox.vxsale.constant.CookieConstant;
import com.rox.vxsale.constant.RedisConstant;
import com.rox.vxsale.exception.SellerAuthorizeException;
import com.rox.vxsale.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author roxBear
 * @creat 2020/4/12
 */
@Aspect
@Component
@Slf4j
public class SellerAuthorizeAspect {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Pointcut("execution(public * com.rox.vxsale.controller.Seller*.*(..))")
    public void verify(){}

    @Before("verify()")
    public void doVerify(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //查询cookie
        Cookie cookie = CookieUtil.get(request , CookieConstant.TOKEN);
        if(cookie == null){
            log.warn("【登录校验】 Cookie中查询不到token");
            throw new SellerAuthorizeException();
        }

        /*//去redis查询
        String tokenValue = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX , cookie.getValue()));
        if(StringUtils.isEmpty(tokenValue)){
            log.warn("【登录校验】 Redis中查询不到token");
            String msg = "登录失败！";
            throw new SellerAuthorizeException(msg);
        }*/
    }
}
