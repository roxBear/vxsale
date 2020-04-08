package com.rox.vxsale.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author roxBear
 * @creat 2020/4/7
 */
public class JsonUtil {
    public static String toJson(Object object){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }
}
