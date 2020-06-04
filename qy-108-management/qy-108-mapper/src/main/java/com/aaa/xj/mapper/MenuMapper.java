package com.aaa.xj.mapper;

import com.aaa.xj.model.Menu;
import com.aaa.xj.vo.MenuVo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface MenuMapper extends Mapper<Menu> {

    //条件查询

    List<Menu> selectMenuByField(Map map);

    List<MenuVo> selectMenuByParentId(Object parentId);
}