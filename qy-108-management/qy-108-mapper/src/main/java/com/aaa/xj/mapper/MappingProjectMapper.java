package com.aaa.xj.mapper;

import com.aaa.xj.model.MappingProject;
import org.apache.ibatis.annotations.Param;
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
    List<MappingProject> selectAllProject();

    /**
     * @author ligen
     * @description
     *  查询测绘项目的详情信息
     * @date 2020/5/22
     * @param []
     * @return java.util.List<com.aaa.xj.model.MappingProject>
     */
    MappingProject selectAllProjectDetailById(Long id);

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
    List<MappingProject> selectAllProjectByType(String projectType);

    /**
     * @author ligen
     * @description
     *  模糊查询-动态sql 查询测绘项目名称
     * @date 2020/5/27
     * @param [mappingProject]
     * @return java.util.List<com.aaa.xj.model.MappingProject>
     */
    List<MappingProject> fuzzyProjectName(@Param("projectName") String projectName,
                                          @Param("projectType") String projectType,
                                          @Param("startDate") String startDate);




}