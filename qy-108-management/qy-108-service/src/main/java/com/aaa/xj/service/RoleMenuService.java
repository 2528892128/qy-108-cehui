package com.aaa.xj.service;

import com.aaa.xj.base.BaseService;
import com.aaa.xj.mapper.RoleMenuMapper;
import com.aaa.xj.model.Role;
import com.aaa.xj.model.RoleMenu;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RoleMenuService extends BaseService<RoleMenu> {
    @Autowired
    private RoleMenuMapper roleMenuMapper;

    /**
     * @Author:  xj
     * @description
     *      根据角色id查询角色的权限
     * @Data: 2020/6/1
     * @param [roleId]
     * @Return:java.util.List<com.aaa.xj.model.RoleMenu>
     */
    public List<RoleMenu> selectRoleMenuById(Long roleId){
        //判断前段是否传值成功
        if (null !=roleId){
            //给roleMenus赋值
            List<RoleMenu> roleMenus = null;
            try {
                //查询拥有的权限
                roleMenus = roleMenuMapper.selectRoleMenuById(roleId);
                System.out.println(roleMenus);
                //判断查询结果是否为空
                if(!"".equals(roleMenus) && null !=roleMenus){
                    return roleMenus;
                }
                return null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    /**
     * @Author:  xj
     * @description
     *      添加角色对应的权限
     * @Data: 2020/6/2
     * @param [roleMenu]
     * @Return:java.lang.Integer
     */
    public Integer insertRoleMenu(RoleMenu roleMenu){
        //判断前段是否传值成功
        if (!"".equals(roleMenu) && null !=roleMenu){
            //执行新增
            try {
                int insert = roleMenuMapper.insert(roleMenu);
                if (insert >0){
                    return insert;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * @Author:  xj
     * @description
     *      修改角色权限时删除原先权限
     * @Data: 2020/6/2
     * @param [roleId]
     * @Return:java.lang.Integer
     */
    public Integer deleteMenuByRoleId(Object roleId){
        //判断前端是否传值成功
        if (!"".equals(roleId) && null !=roleId){
            try {
                //执行删除方法
                Integer integer = roleMenuMapper.deleteByRoleId(roleId);
                if (integer>0){
                    return integer;
                }
                return null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    /**
     * @Author:  xj
     * @description
     *          根据角色id批量删除角色的权限
     * @Data: 2020/6/2
     * @param [roleId]
     * @Return:java.lang.Boolean
     */
    public Boolean deleteMenuByRoleId(List<Object> roleIds){
        //判断前段是否传值成功
        if (!"".equals(roleIds) && null !=roleIds){
            try {
                //调用父类重载的批量删除方法
                Integer integer = super.batchDelete1(roleIds);
                if (integer>0){
                    return true;
                }
                return false;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
