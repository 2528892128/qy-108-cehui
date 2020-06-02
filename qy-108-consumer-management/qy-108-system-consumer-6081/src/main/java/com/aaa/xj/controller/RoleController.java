package com.aaa.xj.controller;

import com.aaa.xj.base.BaseController;
import com.aaa.xj.base.ResultData;
import com.aaa.xj.model.Role;
import com.aaa.xj.model.RoleMenu;
import com.aaa.xj.service.IQYService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Api(value = "角色管理" ,tags = "角色管理接口")
public class RoleController extends BaseController {

    @Autowired
    private IQYService iqyService;

    /**
     * @Author:  xj
     * @description
     * 查询所有角色信息
     * @Data: 2020/6/1
     * @param [pageNo, pageSize]
     * @Return:com.github.pagehelper.PageInfo
     */
    @GetMapping("selectAllRole")
    public ResultData selectAllRole(Integer pageNo,Integer pageSize){
        PageInfo pageInfo = iqyService.selectAllRole(pageNo, pageSize);
        //判断是否查询成功
        if (!"".equals(pageInfo) && null !=pageInfo){
            return super.getSuccess(pageInfo);
        }
        return super.getFalse();
    }


    /**
     * @Author:  xj
     * @description
     *      根据条件查询
     * @Data: 2020/6/1
     * @param [map, pageNo, pageSize]
     * @Return:com.github.pagehelper.PageInfo
     */
    @PostMapping("selectRoleByField")
    public ResultData selectRoleByField(@RequestBody Map map, Integer pageNo,Integer pageSize){
        PageInfo pageInfo = iqyService.selectRoleByField(map, pageNo, pageSize);
        //判断是否查询成功
        if (!"".equals(pageInfo) && null !=pageInfo){
            return super.getSuccess(pageInfo);
        }
        return super.getFalse();
    }

    /**
     * @Author:  xj
     * @description
     *      根据主键查询角色信息
     * @Data: 2020/6/1
     * @param [roleId]
     * @Return:com.aaa.xj.model.Role
     */
    @GetMapping("selectRoleByPrimaryKey")
    public ResultData selectRoleByPrimaryKey(Long roleId){
        Role role = iqyService.selectRoleByPrimaryKey(roleId);
        if (!"".equals(role) && null !=role){
            return super.getSuccess(role);
        }
        return super.getFalse();
    }

    /**
     * @Author:  xj
     * @description
     *      根据角色id查询该角色的权限
     * @Data: 2020/6/1
     * @param [roleId]
     * @Return:com.aaa.xj.base.ResultData
     */
    @GetMapping("selectRoleMenuById")
    public ResultData selectRoleMenuById(Long roleId){
        List<RoleMenu> roleMenus = iqyService.selectRoleMenuById(roleId);
        if (!"".equals(roleMenus) && null !=roleMenus){
            return super.getSuccess(roleMenus);
        }
        return super.getFalse();
    }

    /**
     * @Author:  xj
     * @description
     *      新增角色和对应权限
     * @Data: 2020/6/2
     * @param [map]
     * @Return:com.aaa.xj.base.ResultData
     */
    @PutMapping("/insertRole")
    public ResultData insertRole(@RequestBody Map map){
        Boolean aBoolean = iqyService.insertRole(map);
        if (aBoolean){
            return super.addSuccess();
        }
        return super.addFalse();
    }

    /**
     * @Author:  xj
     * @description
     *      修改角色信息和拥有的权限
     * @Data: 2020/6/2
     * @param [map]
     * @Return:com.aaa.xj.base.ResultData
     */
    @PostMapping("updateRoleByPrimaryKey")
    public ResultData updateRoleByPrimaryKey(@RequestBody Map map){
        Boolean aBoolean = iqyService.updateRoleByPrimaryKey(map);
        if (aBoolean){
            return super.updateSuccess();
        }
        return super.updateFalse();
    }

    /**
     * @Author:  xj
     * @description
     *      根据id批量删除角色和权限
     * @Data: 2020/6/2
     * @param [roleIds]
     * @Return:com.aaa.xj.base.ResultData
     */
    @DeleteMapping("/deleteRoleAndMenu")
    public ResultData deleteRoleAndMenuByRoleId(@RequestBody List<Object> roleIds){
        Boolean aBoolean = iqyService.deleteRoleAndMenuByRoleId(roleIds);
        if (aBoolean){
            return super.deleteSuccess();
        }
        return super.deleteFalse();
    }
}
