package com.aaa.xj.mapper;

import com.aaa.xj.model.ManProject;
import com.aaa.xj.model.User;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface ManProjectMapper extends Mapper<ManProject> {

    List<ManProject> selectAllPros(ManProject manProject);

    List<ManProject> selectByTypes(ManProject manProject);

    //对项目类型进行统计

    List<Map> selectProjectType();
}