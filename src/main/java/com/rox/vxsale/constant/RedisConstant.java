package com.rox.vxsale.constant;

/**
 * redis常量
 * @author roxBear
 * @creat 2020/4/12
 */
public interface RedisConstant {

    String TOKEN_PREFIX = "token_%s";

    Integer EXPIRE = 3600*24; //24小时
}
