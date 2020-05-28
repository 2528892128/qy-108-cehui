package com.aaa.xj.controller;

import com.aaa.xj.base.ResultData;
import com.aaa.xj.model.User;
import com.aaa.xj.service.LoginLogsService;
import com.aaa.xj.service.LoginService;
import com.aaa.xj.redis.RedisService;
import com.aaa.xj.vo.TokenVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.UnknownHostException;


@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private LoginLogsService loginLogsService;


    /**
     * @author
     * @description
     *      执行登录操作
     * @param [user]
     * @date 2020/5/15
     * @return com.aaa.lee.base.ResultData
     * @throws
    **/

    @PostMapping("/doLogin")
    public TokenVo doLogin(@RequestBody User user){
        try {
            TokenVo tokenVo = loginService.doLogin(user, redisService);
            if (tokenVo.getIfSuccess()){
                return tokenVo;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
//
//    @PostMapping("/isLogin")
//    public ResultData isLogin(){
//        return loginService.isLogin(redisService);
//    }

}