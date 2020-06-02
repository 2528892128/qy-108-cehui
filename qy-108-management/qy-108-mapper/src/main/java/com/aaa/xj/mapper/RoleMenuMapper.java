package com.aaa.xj.mapper;

import com.aaa.xj.model.RoleMenu;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface RoleMenuMapper extends Mapper<RoleMenu> {

    List<RoleMenu> selectRoleMenuById(Long roleId);

    Integer deleteByRoleId(Object roleId);
}