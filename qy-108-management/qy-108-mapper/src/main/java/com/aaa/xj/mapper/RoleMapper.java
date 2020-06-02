package com.aaa.xj.mapper;

import com.aaa.xj.model.Role;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface RoleMapper extends Mapper<Role> {
    //根据字段查询角色信息

    List<Role> selectRoleByField(Map map);

    //新增角色
    
    Integer insertRoleResultId(Role role);

    //修改角色信息

    Integer updateRoleByPrimaryKey(Role role);
}