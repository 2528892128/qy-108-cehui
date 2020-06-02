package com.aaa.xj.mapper;

import com.aaa.xj.model.MappingProject;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author ligen
 * @description
 * @Data Create in 2020/5/22 9:52
 */
public interface MappingProjectMapper extends Mapper<MappingProject> {

    /**
     * @author ligen
     * @description 项目汇交
     *  查询所有的 项目汇交信息-项目成果汇交状态：通过 已提交
     * @date 2020/5/22
     * @param []
     * @return java.util.List<com.aaa.xj.model.MappingProject>
     */
    List<MappingProject> selectAllProjectResult();

    /**
     * @author ligen
     * @description 项目汇交-根据项目类型查询
     *  条件查询 根据项目类型 projectType，
     *  查询所有的 项目汇交信息
     *      项目类型分为：基础测绘，专业测绘
     * @date 2020/5/22
     * @param [projectType]
     * @return java.util.List<com.aaa.xj.model.MappingProject>
     */
    List<MappingProject> selectAllProjectResultByType(String projectType);

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

    /**
     * @author ligen
     * @description 系统主页-测绘项目
     *  查询项目基本信息-主键查询
     * @date 2020/5/31
     * @param [id]
     * @return com.aaa.xj.model.MappingProject
     */
    MappingProject selectProjectInfoById(Long id);

    /**
     * @author ligen
     * @description 系统主页-测绘项目-查看详情
     *  查询测绘项目详情-根据主键id查询
     * @date 2020/5/31
     * @param [id]
     * @return com.aaa.xj.model.MappingProject
     */
    MappingProject selectProjectDetailById(Long id);

    /**
     * @author ligen
     * @description 系统主页-测绘单位-查看详情-项目信息
     *  查询，
     *      根据单位id 查询该单位下的项目信息
     * @date 2020/5/31
     * @param [id]
     * @return java.util.List<com.aaa.xj.model.MappingProject>
     */
    List<MappingProject> selectProjectForUnitByUserId(Long id);

    /**
     * @author ligen
     * @description 项目审核-项目信息
     *  查询所有的项目信息-项目审核结果为通过 已提交
     * @date 2020/6/1
     * @param []
     * @return java.util.List<com.aaa.xj.model.MappingProject>
     */
    List<MappingProject> selectAllProjectAudit();

    /**
     * @author ligen
     * @description 项目审核-项目信息
     *  查询所有的项目信息-项目审核结果为通过 已提交
     *      条件查询-模糊查询，
     *      条件：项目名称 projectName
     * @date 2020/6/1
     * @param [projectName]
     * @return java.util.List<com.aaa.xj.model.MappingProject>
     */
    List<MappingProject> fuzzyProjectAuditByType(String projectName);

    /**
     * @author ligen
     * @description 项目审核-项目信息
     *  查询项目详细信息-主键查询
     * @date 2020/6/1
     * @param [id]
     * @return com.aaa.xj.model.MappingProject
     */
    MappingProject selectProjectAuditById(Long id);

}