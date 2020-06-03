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
     *  查询所有未提交的汇交成果
     *      汇交成果状态 results_status=3
     * @date 2020/5/22
     * @param []
     * @return java.util.List<com.aaa.xj.model.MappingProject>
     */
    List<MappingProject> selectAllProjectResult();

    /**
     * @author ligen
     * @description 项目汇交-根据项目类型查询
     *  条件查询 根据项目类型 projectType，查询所有未提交的汇交成果
     *      项目类型分为：基础测绘，专业测绘
     * @date 2020/5/22
     * @param [projectType]
     * @return java.util.List<com.aaa.xj.model.MappingProject>
     */
    List<MappingProject> selectAllProjectResultByType(String projectType);

    /**
     * @author ligen
     * @description 项目汇交-操作
     *  修改汇交成果状态 results_status=2
     *  场景：点击按钮提交汇交成果项目
     * @date 2020/6/2
     * @param [id]
     * @return int
     */
    int updateProjectResultStatusById(Long id);

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
     * @description 查询测绘项目详情-根据主键id查询
     * 系统主页-测绘项目-查看详情
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
     *  查询所有审核结果为通过的项目信息，audit_status=0
     * @date 2020/6/1
     * @param []
     * @return java.util.List<com.aaa.xj.model.MappingProject>
     */
    List<MappingProject> selectAllProjectAudit();

    /**
     * @author ligen
     * @description 项目审核-项目信息
     *  查询所有审核结果为通过的项目信息
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

    /**
     * @author ligen
     * @description 项目审核-汇交成果信息
     *  查询所有的汇交成果为通过的项目信息 results_status=0
     * @date 2020/6/2
     * @param []
     * @return java.util.List<com.aaa.xj.model.MappingProject>
     */
    List<MappingProject> selectAllProjectResultAudit();

    /**
     * @author ligen
     * @description 项目审核-汇交成果信息
     *  查询所有的汇交成果为通过的项目信息
     *      条件查询-模糊查询，
     *      条件：项目名称 projectName
     * @date 2020/6/2
     * @param [projectName]
     * @return java.util.List<com.aaa.xj.model.MappingProject>
     */
    List<MappingProject> fuzzyProjectResultAuditByType(String projectName);

    /**
     * @author ligen
     * @description 项目审核-汇交成果信息-查看
     *  查看汇交成果项目详情
     * @date 2020/6/2
     * @param [id]
     * @return com.aaa.xj.model.MappingProject
     */
    MappingProject selectProjectResultAuditById(Long id);

    /**
     * @author ligen
     * @description 项目审核-项目审核
     *  查询所有待审核的项目信息
     *      项目审核结果为已提交 audit_status=2
     * @date 2020/6/2
     * @param []
     * @return java.util.List<com.aaa.xj.model.MappingProject>
     */
    List<MappingProject> selectAllProjectToAudit();

    /**
     * @author ligen
     * @description 项目审核-项目审核-条件查询
     *  条件查询-模糊查询，项目名称
     *  查询所有待审核的项目信息
     *      项目审核结果为已提交 audit_status=2
     * @date 2020/6/2
     * @param [projectName]
     * @return java.util.List<com.aaa.xj.model.MappingProject>
     */
    List<MappingProject> fuzzyProjectToAuditByPName(String projectName);

    /**
     * @author ligen
     * @description 项目审核-项目审核-审核
     *      t_mapping_project:
     *          id 操作项目编号id；
     *          audit_status 项目审核结果，0 通过；1 不通过；
     * @date 2020/6/3
     * @param [map]
     * @return int
     */
    int updateProjectAuditStatus(@Param("id") Long id, @Param("auditStatus") Integer auditStatus);

    /**
     * @author ligen
     * @description 项目审核-汇交成果审核
     *  查询所有待审核的汇交成果项目信息
     *      汇交成果状态为已提交 results_status=2
     * @date 2020/6/3
     * @param []
     * @return java.util.List<com.aaa.xj.model.MappingProject>
     */
    List<MappingProject> selectAllProjectResultToAudit();

    /**
     * @author ligen
     * @description 项目审核-汇交成果审核-条件模糊查询
     *  条件查询-模糊查询，项目名称 projectName
     *  查询所有待审核的汇交成果项目信息
     *      汇交成果状态为已提交 results_status=2
     * @date 2020/6/3
     * @param [projectName]
     * @return java.util.List<com.aaa.xj.model.MappingProject>
     */
    List<MappingProject> fuzzyProjectResultToAuditByPName(String projectName);

    /**
     * @author ligen
     * @description 项目审核-汇交成果审核-审核
     *      t_mapping_project：
     *          id 操作项目编号id；
     *          results_status 项目审核结果，0 通过；1 不通过；
     * @date 2020/6/3
     * @param [id, resultsStatus]
     * @return int
     */
    int updateProjectResultStatus(@Param("id") Long id, @Param("resultsStatus") Integer resultsStatus);


}