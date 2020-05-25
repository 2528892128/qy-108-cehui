package com.aaa.xj.mapper;

import com.aaa.xj.model.ManProject;
import com.aaa.xj.model.User;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface ManProjectMapper extends Mapper<ManProject> {

    List<ManProject> selectAllPros(ManProject manProject);

    List<ManProject> selectByTypes(String projectType,Long id);
}