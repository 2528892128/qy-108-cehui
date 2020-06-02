package com.aaa.xj.dynamic.interceptor;

import com.aaa.xj.dynamic.annotation.TDS;
import com.aaa.xj.dynamic.datasource.DynamicDataSourceContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/5/30 16:59
 * @Description
 **/
@Component
public class TDSInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod)handler;
        TDS annotation = handlerMethod.getMethod().getAnnotation(TDS.class);
        if(null == annotation) {
            annotation = handlerMethod.getMethod().getDeclaringClass().getAnnotation(TDS.class);
        }
        if(null != annotation && !"".equals(annotation.value())) {
            DynamicDataSourceContextHolder.setDatasourceType(annotation.value());
        }
        return true;
    }
}
