package com.aaa.xj.filter;

import com.aaa.xj.utils.GetParamsUtils;
import com.aaa.xj.utils.PostParamsUtils;
import com.aaa.xj.utils.SendParams2ControllerUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/6/2 15:06
 * @Description
 *      验证token的过滤器
 **/
@Component
public class TokenFilter extends ZuulFilter {

    /**
     * @author Seven Lee
     * @description
     *      这个是必面的
     *      过滤器的类型
     *          这个过滤器的类型一共有四种，换句话说一共有四个值:
     *              1.pre:
     *                  到达路由之前执行
     *                  客户端发送登录请求--->(zuul(pre)--->(转发的一个类))--->eureka--->consumer
     *              2.routing:
     *                  到达路由的实话执行
     *              3.post:
     *                  在路由之后执行
     *              4.error:
     *                  在路由中抛异常的时候(统一的路由异常处理)
     * @param []
     * @date 2020/6/2
     * @return java.lang.String
     * @throws
    **/
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * @author Seven Lee
     * @description
     *      路由过滤器的顺序:
     *          如果只是单个过滤器则没有用
     *          当有多个过滤器的时候就非常重要了
     *          这里返回的数值就代表了过滤器触发优先级:
     *              数字越大，优先级越低
     *          TODO 自己查看一下是否可以写负数
     * @param []
     * @date 2020/6/2
     * @return int
     * @throws 
    **/
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * @author Seven Lee
     * @description
     *      是否要启用过滤器
     *          通常用于逻辑判断，判断请求路径是否需要过滤
     *          true:过滤器启动
     *          false:过滤器停用
     * @param []
     * @date 2020/6/2
     * @return boolean
     * @throws
    **/
    @Override
    public boolean shouldFilter() {
        RequestContext rcx = RequestContext.getCurrentContext();
        HttpServletRequest request = rcx.getRequest();
        // 第一种解决方案
        // 一定要去验证最终路径中是否传递了token值，如果没有则直接return false，如果有直接return true
        // 项目难点:zuul是无论如何都获取不到这个路径中所传递的参数(无论是GET还是POST都不好使)
        // 必须要从请求头里获取信息(如果单纯验证token的话，只需要封装一个get即可，如果需要验证其他的对象信息则需要封装post请求)
        // get和post我都封装，但是get太简单，用post来获取
        String params = GetParamsUtils.getParams(request);
        if(params.contains("token") && (params.contains("http://") || params.contains("https://"))) {
            // 这么一来我就可以确定你的值里面传的有token
            return true;
        }
        // 第二种解决方案
        // 判断请求路径，如果路径中包含了/login或者像/doLogin这些路径，而且还包含了http://或者https://
        // 就证明是登录的，我就直接不拦你 TODO 作业去实现第二种思路，业务必须要求写
        // 难点二:return false说明run方法根本就没用，那就意味着根本就没有办法跳转到目标controller
        /**
         * 现在已经确定了只有两种情况:
         *  1.有token，不是登录
         *  2.没有token，绝对是登录
         *          那就意味着必须会有user对象(而且这个user对象还必须是post方式发送)
         */
        SendParams2ControllerUtils.sendPanras(PostParamsUtils.postParams(rcx), rcx, request);
        return false;
    }

    /**
     * @author Seven Lee
     * @description
     *      实现具体逻辑的
     *      也就是说有关于验证token的业务都会在这个方法中
     * @param []
     * @date 2020/6/2
     * @return java.lang.Object
     * @throws
    **/
    @Override
    public Object run() throws ZuulException {
        return null;
    }
}
