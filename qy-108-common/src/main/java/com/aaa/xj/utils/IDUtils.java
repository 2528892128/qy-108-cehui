package com.aaa.xj.utils;

import java.util.UUID;


public class IDUtils {

    private IDUtils() {

    }

    /**
     * @author Seven Lee
     * @description
     *      获取uuid
     * @param [id]
     * @date 2020/5/15
     * @return java.lang.String
     * @throws
    **/
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
