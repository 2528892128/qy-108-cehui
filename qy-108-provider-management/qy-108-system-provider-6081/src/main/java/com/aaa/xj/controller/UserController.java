package com.aaa.xj.controller;

import com.aaa.xj.model.User;
import com.aaa.xj.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @Summary:
     * @Author:  xj
     * @description
     *      查询所有信息
     * @Data: 2020/5/26
     * @param null
     * @Return:
     */
//    @GetMapping("/selectAll")
//    public List<User> selectAllUser(){
//       return userService.selectAllUser();
//    }


    @GetMapping("/selectAllUser")
    public PageInfo<User> queryListByPage(User user, Integer pageNo, Integer pageSize){
         return  userService.queryListByPage(user,5,10);
    }

}
