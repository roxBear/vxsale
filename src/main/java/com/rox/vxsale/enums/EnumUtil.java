package com.rox.vxsale.enums;

/**
 * @author roxBear
 * @creat 2020/4/7
 */
public class EnumUtil {

    public static <T extends EnumCode> T getByCode(Integer code, Class<T> enumClass) {
        for (T each: enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}
