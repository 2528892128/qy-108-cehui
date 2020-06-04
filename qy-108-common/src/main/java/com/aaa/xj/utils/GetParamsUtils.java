package com.aaa.xj.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/6/2 15:35
 * @Description
 *      获取以GET方式请求的参数
 **/
public class GetParamsUtils {

    private GetParamsUtils() {

    }

    /**
     * @author Seven Lee
     * @description
     *      获取参数的方法
     * @param [request]
     * @date 2020/6/2
     * @return java.lang.String
     * @throws
    **/
    public static String getParams(HttpServletRequest request) {
        // GET传参的形式:localhost:8081/login?username=zhangsan&password=123456
        // 按照正规要求应该考虑安全，但是像咱们这个项目是一个后台系统
        StringBuilder params = new StringBuilder("?");
        // 所以对安全的考虑没有特别大的要求
        Enumeration<String> names = request.getParameterNames();
        // 判断确定用户使用的就是get请求方式
        if(request.getMethod().toUpperCase().equals("GET")) {
            // 说明使用的就是GET方式
            while(names.hasMoreElements()) {
                // 其实这里获取的是参数的key值
                String name = names.nextElement();
                params.append(name);
                params.append("=");
                params.append(request.getParameter(name));
                params.append("&");
            }
        }
        if(params.length() > 1) {
            params.delete(params.length() - 1, params.length());
        }
        return params.toString();
    }

}
