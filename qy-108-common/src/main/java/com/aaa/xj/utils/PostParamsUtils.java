package com.aaa.xj.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.context.RequestContext;
import io.micrometer.core.instrument.util.IOUtils;

import javax.servlet.ServletInputStream;
import java.io.IOException;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/6/2 16:12
 * @Description
 *      获取POST请求方式的参数
 **/
public class PostParamsUtils {

    private  PostParamsUtils() {

    }

    /**
     * @author Seven Lee
     * @description
     *      获取参数的方法
     * @param [rcx]
     * @date 2020/6/2
     * @return com.alibaba.fastjson.JSONObject
     * @throws
    **/
    public static JSONObject postParams(RequestContext rcx) {
        String body = null;
        if(!rcx.isChunkedRequestBody()) {
            ServletInputStream inp;
            try {
                inp = rcx.getRequest().getInputStream();
                if(null != inp) {
                    body = IOUtils.toString(inp);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return JSON.parseObject(body);
    }

}
