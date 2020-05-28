package com.aaa.xj.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/5/27 15:38
 * @Description
 **/
public class IPUtils {

    private IPUtils() {

    }

    private static final String UNKNOWN = "unknown";

    /**
     * @author Seven Lee
     * @description
     *      获取到用户的ip地址
     * @param [request]
     * @date 2020/5/27
     * @return java.lang.String
     * @throws
    **/
    public static String getIpAddr(HttpServletRequest request) {

        // 其实用户的ip地址你们都可以获取到:x-forwarded-for就是ip地址
        String ip = request.getHeader("x-forwarded-for");
        // 需要进行严谨判断(如果用户使用的有代理服务器(本地代理服务器，网络代理服务器都需要判断))
        if(ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();// 0:0:0:1.. 127.0.0.1
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }

}
