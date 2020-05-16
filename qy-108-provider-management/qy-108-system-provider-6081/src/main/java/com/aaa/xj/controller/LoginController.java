package com.aaa.xj.controller;

import com.aaa.xj.model.User;
import com.aaa.xj.service.LoginService;
import com.aaa.xj.redis.RedisService;
import com.aaa.xj.vo.TokenVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private RedisService redisService;

    /**
     * @author Seven Lee
     * @description
     *      执行登录操作
     * @param [user]
     * @date 2020/5/15
     * @return com.aaa.lee.base.ResultData
     * @throws
    **/
    @PostMapping("/doLogin")
    public TokenVo doLogin(@RequestBody User user) {
        return loginService.doLogin(user, redisService);
    }
}
