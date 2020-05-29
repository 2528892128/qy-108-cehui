package com.aaa.xj.controller;

import com.aaa.xj.base.BaseService;
import com.aaa.xj.base.CommonController;
import com.aaa.xj.model.User;
import com.aaa.xj.service.UserService;
import com.github.pagehelper.PageInfo;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UserController extends CommonController<User> {

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
    @PostMapping("/selectAllUser")
    public PageInfo selectAllUser(@RequestBody User user, @RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize){
       return userService.selectAllUser(user,pageNo,pageSize);
    }

    /**
     * @Summary:
     * @Author:  xj
     * @description
     *      新增用户
     * @Data: 2020/5/28
     * @param [user]
     * @Return:java.lang.Boolean
     */
    @PostMapping("/addUser")
    public Boolean addUser(@RequestBody User user){
       return userService.addUser(user);
    }


    /**
     * @Summary:
     * @Author:  xj
     * @description
     *      通过主键删除用户
     * @Data: 2020/5/27
     * @param [user]
     * @Return:java.lang.Integer
     */
    @PostMapping("/deleteUser")
    public Integer deleteUser(@RequestBody User user){
        try {
            Integer delete = userService.delete(user);
            return delete;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * @Summary:
     * @Author:  xj
     * @description
     *      根据id批量删除用户
     * @Data: 2020/5/26
     * @param [ids]
     * @Return:java.lang.Integer
     */
    @PostMapping("/delectMoreUser")
    public Integer deleteMoreUser(@RequestBody List<Object> ids){
        return userService.deleteMoreUser(ids);
    }
    /**
     * @Summary:
     * @Author:  xj
     * @description
     *      根据id查询用户信息
     * @Data: 2020/5/26
     * @param [id]
     * @Return:com.aaa.xj.model.User
     */
    @GetMapping("/selectUserById")
    public User selectUserById(@RequestParam("id") Long id){
        return userService.selectUserById(id);
    }

    /**
     * @Summary:
     * @Author:  xj
     * @description
     *      根据id修改用户信息
     * @Data: 2020/5/26
     * @param [user]
     * @Return:java.lang.Integer
     */
    @PostMapping("/updateUserById")
    public Integer updateUserById(@RequestBody User user){
        return userService.updateUser(user);
    }

    /**
     * @Summary:
     * @Author:  xj
     * @description
     *      根据性别查询用户信息
     * @Data: 2020/5/26
     * @param [ssex, pageNo, pageSize]
     * @Return:com.github.pagehelper.PageInfo
     */
    @GetMapping("/selectUserBySsex")
    public PageInfo selectUserBySsex(@RequestParam("ssex") String ssex,@RequestParam("pageNo") Integer pageNo,@RequestParam("pageSize") Integer pageSize){
        return userService.selectUserBySsex(ssex,pageNo,pageSize);
    }

    /**
     * @Summary:
     * @Author:  xj
     * @description
     *      根据用户状态查询用户信息
     * @Data: 2020/5/28
     * @param [status, pageNo, pageSize]
     * @Return:com.github.pagehelper.PageInfo
     */
    @GetMapping("/selectUserBySta")
    public PageInfo selectUserBySta(@RequestParam("status") String status,@RequestParam("pageNo") Integer pageNo,@RequestParam("pageSize") Integer pageSize){
        return userService.selectUserBySta(status,pageNo,pageSize);
    }
    /**
     * @Summary:
     * @Author:  xj
     * @description
     *      重置密码
     * @Data: 2020/5/26
     * @param [user]
     * @Return:java.lang.Integer
     */
    @PostMapping("/resetUserPwd")
    public Integer ResetUserPwd(@RequestBody User user){
        return userService.ResetUserPwd(user);
    }

    @Override
    public BaseService<User> getBaseService() {
        return userService;
    }
}
