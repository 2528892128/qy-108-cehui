package com.aaa.xj.mapper;

import com.aaa.xj.model.MappingProject;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author ligen
 * @description
 *  项目汇交
 * @Data Create in 2020/5/22 9:52
 */
public interface MappingProjectMapper extends Mapper<MappingProject> {

    /**
     * @author ligen
     * @description
     *  查询所有的 已提交的项目信息
     * @date 2020/5/22
     * @param []
     * @return java.util.List<com.aaa.xj.model.MappingProject>
     */
    List<MappingProject> selectAllMappingProject();

    /**
     * @author ligen
     * @description
     *  条件查询 根据项目类型 projectType，
     *  查询所有的 已提交的项目信息
     *      项目类型分为：基础测绘，专业测绘
     * @date 2020/5/22
     * @param [projectType]
     * @return java.util.List<com.aaa.xj.model.MappingProject>
     */
    List<MappingProject> selectAllByProjectType(String projectType);

}