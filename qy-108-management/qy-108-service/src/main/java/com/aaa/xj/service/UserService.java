package com.aaa.xj.service;

import com.aaa.xj.base.BaseService;
import com.aaa.xj.mapper.UserMapper;
import com.aaa.xj.model.User;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService extends BaseService<User> {

    @Autowired
    private UserMapper userMapper;

    /**
     * @Summary:
     * @Author:  xj
     * @description
     *      查询所有用户
     * @Data: 2020/5/23 18:21
     * @param null
     * @Return:
     */


}
