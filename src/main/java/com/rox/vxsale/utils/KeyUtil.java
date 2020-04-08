package com.rox.vxsale.utils;

import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 生成各类订单主键
 * @author roxBear
 * @creat 2020/4/4
 */
public class KeyUtil {
    /**
     * 生成随机数20200404XXXX
     * @return
     */
    public static String getUniqueKey(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Random random = new Random();
        int count = random.nextInt(9000) + 1000;
        String uniqueKey = sdf.format(new Date()) + count;
        return uniqueKey;
    }

    public static String getProductId(){
        SimpleDateFormat sdf = new SimpleDateFormat("MMdd");
        Random random = new Random();
        int count = random.nextInt(9) + 1;
        String uniqueKey = sdf.format(new Date()) + 0+count;
        return uniqueKey;
    }
}
