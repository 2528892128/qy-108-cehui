package com.aaa.xj.controller;

import com.aaa.xj.base.BaseController;
import com.aaa.xj.base.ResultData;
import com.aaa.xj.model.User;
import com.aaa.xj.service.IQYService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "用户管理" ,tags = "用户管理接口")
public class UserController extends BaseController {

    @Autowired
    private IQYService iqyService;

   /**
    * @Summary:
    * @Author:  xj
    * @description
    *       用户查询
    * @Data: 2020/5/26
    * @param [user, pageNo, pageSize]
    * @Return:com.aaa.xj.base.ResultData
    */
    @PostMapping("/selectAllUser")
    public ResultData selectAllUser(User user,Integer pageNo,Integer pageSize){
        PageInfo pageInfo = iqyService.selectAllUser(user, pageNo, pageSize);
        if (!"".equals(pageInfo) && null !=pageInfo){
            return super.getSuccess(pageInfo);
        }
        return super.getFalse();
    }

    /**
     * @Summary:
     * @Author:  xj
     * @description
     *      新增用户
     * @Data: 2020/5/26
     * @param [user]
     * @Return:com.aaa.xj.base.ResultData
     */
    @PostMapping("/addUser")
    public ResultData addUser(User user){
        Boolean aBoolean = iqyService.addUser(user);
        if (aBoolean){
            return super.addSuccess();
        }
        return super.addFalse();
    }

    /**
     * @Summary:
     * @Author:  xj
     * @description
     *      根据id删除用处
     * @Data: 2020/5/26
     * @param [user]
     * @Return:com.aaa.xj.base.ResultData
     */
    @PostMapping("/deleteUser")
    public ResultData deleteUser( User user){
        Integer integer = iqyService.deleteUser(user);
        if (integer>0){
            return super.deleteSuccess();
        }
        return super.deleteFalse();
    }

    /**
     * @Summary:
     * @Author:  xj
     * @description
     *      根据id批量删除用户
     * @Data: 2020/5/28
     * @param [ids]
     * @Return:com.aaa.xj.base.ResultData
     */

    @PostMapping("/deleteMoreUser")
    public ResultData deleteMoreUser(@RequestBody List<Object> ids){
        Integer integer = iqyService.deleteMoreUser(ids);
        if (integer>0){
            return super.deleteSuccess();
        }
        return super.deleteFalse();
    }

    /**
     * @Summary:
     * @Author:  xj
     * @description
     *      根据用户id查询信息
     * @Data: 2020/5/26
     * @param [id]
     * @Return:com.aaa.xj.base.ResultData
     */
    @GetMapping("/selectUserById")
    public ResultData selectUserById(Long id){
        User user = iqyService.selectUserById(id);
        if (!"".equals(user) && null !=user){
            return super.getSuccess(user);
        }
        return super.getFalse();
    }

    /**
     * @Summary:
     * @Author:  xj
     * @description
     *      根据id修改用户信息
     * @Data: 2020/5/26
     * @param [user]
     * @Return:com.aaa.xj.base.ResultData
     */
   @PostMapping("/updateUserById")
   public ResultData updateUserById(User user){
       Integer integer = iqyService.updateUserById(user);
       if (integer>0){
           return super.updateSuccess();
       }
       return super.updateFalse();
   }

}
