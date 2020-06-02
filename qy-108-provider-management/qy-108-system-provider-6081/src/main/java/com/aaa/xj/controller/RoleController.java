package com.aaa.xj.controller;

import com.aaa.xj.base.BaseController;
import com.aaa.xj.model.ManProject;
import com.aaa.xj.model.Role;
import com.aaa.xj.model.RoleMenu;
import com.aaa.xj.service.RoleMenuService;
import com.aaa.xj.service.RoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleMenuService roleMenuService;

    /**
     * @Summary:
     * @Author:  xj
     * @description
     *      查询所有角色信息
     * @Data: 2020/6/1
     * @param [pageNo, pageSize]
     * @Return:com.github.pagehelper.PageInfo
     */
    @GetMapping("/selectAllRole")
    public PageInfo selectAllRole(@RequestParam("pageNo") Integer pageNo,@RequestParam("pageSize") Integer pageSize){
        PageInfo pageInfo = roleService.selectAllARole(pageNo, pageSize);
        if (null !=pageInfo){
            return pageInfo;
        }
        return null;
    }

    /**
     * @Author:  xj
     * @description
     *      根据条件查询
     * @Data: 2020/6/1
     * @param [map, pageNo, pageSize]
     * @Return:com.github.pagehelper.PageInfo
     */
    @PostMapping("/selectRoleByField")
    public PageInfo selectRoleByField(@RequestBody Map map,@RequestParam("pageNo") Integer pageNo,@RequestParam("pageSize") Integer pageSize){
        PageInfo pageInfo = roleService.selectRoleByField(map, pageNo, pageSize);
        if (!"".equals(pageInfo) && null !=pageInfo){
            return pageInfo;
        }
        return null;
    }


    /**
     * @Author:  xj
     * @description
     *       根据主键查询角色信息
     * @Data: 2020/6/1
     * @param [roleId]
     * @Return:com.aaa.xj.model.Role
     */
    @GetMapping("selectRoleByPrimaryKey")
    public Role selectRoleByPrimaryKey(@RequestParam("roleId") Long roleId){
        Role role = roleService.selectRoleByParimaryKey(roleId);
        if (!"".equals(role) && null !=role){
            return role;
        }
        return null;
    }

    /**
     * @Author:  xj
     * @description
     *      根据角色id查询拥有的权限
     * @Data: 2020/6/1
     * @param [roleId]
     * @Return:java.util.List<com.aaa.xj.model.RoleMenu>
     */
    @GetMapping("/selectRoleMenuById")
    public List<RoleMenu> selectRoleMenuById(@RequestParam("roleId") Long roleId){
        List<RoleMenu> roleMenus = roleMenuService.selectRoleMenuById(roleId);
        if (!"".equals(roleMenus) && null !=roleMenus){
            return roleMenus;
        }
        return null;
    }
    /**
     * @Author:  xj
     * @description
     *      新增角色和权限
     * @Data: 2020/6/2
     * @param [map]
     * @Return:java.lang.Boolean
     */
    @PutMapping("insertRole")
    public Boolean insertRole(@RequestBody Map map){
        //前台传入map 数据 需要取出后放入实体类中进行添加
        Role role = new Role().setRoleName(map.get("roleName").toString()).setRemark(map.get("remark").toString());
        //此时返回的时 添加生成的主键id
        Long roleId = roleService.insertRole(role);
        if (null !=roleId){
            Integer insert = 0;
            //获取前台传入的数据 因为map中获取的时object类型 转化为string并且去除空格
            String xx = map.get("xx").toString().replaceAll(" ","");
            //将string转化为数组 因string格式为"[1,2,3]" 去除边框[]为"1,2,3" 并且以,为分割转化为数组
            // 便于遍历进行权限表的添加数据
            String[] arr = xx.substring(1,xx.length()-1).split(",");
            //遍历arr中的权限id 进行权限的添加
            for (int i = 0; i < arr.length ; i++) {
                //将数组遍历 取出的权限id转化为Long类型
                Long menuId = Long.valueOf(arr[i]);
                //添加到角色权限中间表中
                RoleMenu roleMenu = new RoleMenu().setRoleId(roleId).setMenuId(menuId);
                Integer integer = roleMenuService.insertRoleMenu(roleMenu);
                insert += integer;
            }
            if (insert > 0){
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * @Author:  xj
     * @description
     *      修改角色信息和权限
     * @Data: 2020/6/2
     * @param [map]
     * @Return:java.lang.Boolean
     */
    @PostMapping("updateRoleByPrimaryKey")
    public Boolean updateRoleByPrimaryKey(@RequestBody Map map){
        Role role = new Role();
        role.setRoleName(map.get("roleName").toString()).setRemark(map.get("remark").toString()).setRoleId(Long.valueOf(map.get("roleId").toString()));
        //修改角色信息
        Integer updateRoleByPrimaryKey = roleService.updateRoleByPrimaryKey(role);
        if (updateRoleByPrimaryKey > 0){
            //删除角色对应权限
            Integer deleteMenuByRoleId = roleMenuService.deleteMenuByRoleId(map.get("roleId"));
            if (deleteMenuByRoleId > 0){
                Integer insert = 0;
                //获取前台传入的数据 因为map中获取的时object类型 转化为string并且去除空格
                String xx = map.get("xx").toString().replaceAll(" ","");
                //将string转化为数组 因string格式为"[1,2,3]" 去除边框[]为"1,2,3" 并且以,为分割转化为数组
                // 便于遍历进行权限表的添加数据
                String[] arr = xx.substring(1,xx.length()-1).split(",");
                //遍历arr中的权限id 进行权限的添加
                for (int i = 0; i < arr.length ; i++) {
                    //将数组遍历 取出的权限id转化为Long类型
                    Long menuId = Long.valueOf(arr[i]);
                    //添加到角色权限中间表中
                    RoleMenu roleMenu = new RoleMenu().setRoleId(Long.valueOf(map.get("roleId").toString())).setMenuId(menuId);
                    Integer integer = roleMenuService.insertRoleMenu(roleMenu);
                    insert += integer;
                }
                if (insert > 0){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @Author:  xj
     * @description
     *      根据id批量删除角色和权限
     * @Data: 2020/6/2
     * @param [roleIds]
     * @Return:java.lang.Boolean
     */
    @DeleteMapping("/deleteRoleAndMenu")
    public Boolean deleteRoleAndMenuByRoleId(@RequestBody List<Object> roleIds){
        Boolean aBoolean = roleService.deleteRoleByRoleId(roleIds);
        if (aBoolean){
            Boolean aBoolean1 = roleMenuService.deleteMenuByRoleId(roleIds);
            if (aBoolean1){
                return true;
            }
            return false;
        }
        return false;
    }
}
