package com.aaa.xj.controller;

import com.aaa.xj.model.Audit;
import com.aaa.xj.model.MappingProject;
import com.aaa.xj.service.MappingProjectService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ligen
 * @program qy-108-cehui
 * @description
 *  测绘项目
 * @create 2020-05-22 14:01
 */
@RestController
public class MappingProjectController {
    @Autowired
    private MappingProjectService mappingProjectService;

    /**
     * @author ligen
     * @description 项目汇交
     *  查询所有未提交的汇交成果
     *      汇交成果状态 results_status=3
     * @date 2020/6/2
     * @param [pageNo, pageSize]
     * @return com.github.pagehelper.PageInfo<com.aaa.xj.model.MappingProject>
     */
    @GetMapping("/selectAllProjectResult")
    public PageInfo<MappingProject> selectAllProjectResult(@RequestParam("pageNo") Integer pageNo,
                                                           @RequestParam("pageSize") Integer pageSize) {
        // 调用 mappingProjectService 中的 selectAllProjectResult 方法，返回结果
        PageInfo<MappingProject> projectPageInfo = mappingProjectService.selectAllProjectResult(pageNo, pageSize);

        // 判断 结果不为空
        if (null != projectPageInfo){
            // 说明结果不为空，查询成果，返回结果
            return projectPageInfo;
        }else {
            // 查询失败，返回null
            return null;
        }
    }

    /**
     * @author ligen
     * @description 项目汇交-根据项目类型查询
     *  条件查询 根据项目类型 projectType，查询所有的 项目汇交信息，进行分页
     *      项目类型分为：基础测绘，专业测绘
     * @date 2020/5/31
     * @param [projectType, pageNo, pageSize]
     * @return com.github.pagehelper.PageInfo<com.aaa.xj.model.MappingProject>
     */
    @GetMapping("/selectAllProjectResultByType")
    public PageInfo<MappingProject> selectAllProjectResultByType(@RequestParam("projectType") String projectType,
                                                                 @RequestParam("pageNo") Integer pageNo,
                                                                 @RequestParam("pageSize") Integer pageSize) {
        // 调用 mappingProjectService 中的 selectAllProjectResultByType 方法，得到查询结果
        PageInfo<MappingProject> projectPageInfo = mappingProjectService.selectAllProjectResultByType(projectType, pageNo, pageSize);

        // 判断 结果是否为空
        if (null != projectPageInfo){
            // 说明结果不为空，查询成功返回结果
            return projectPageInfo;
        }else {
            // 查询失败，返回null
            return null;
        }
    }

    /**
     * @author ligen
     * @description 项目汇交-操作
     *  修改汇交成果状态 results_status=2
     *  场景：点击按钮提交汇交成果项目
     * @date 2020/6/2
     * @param [id]
     * @return java.lang.Boolean
     */
    @PostMapping("/updateProjectResultStatusById")
    public Boolean updateProjectResultStatusById(@RequestParam("id") Long id) {
        // 调用 mappingProjectService 中的 updateProjectResultStatusById 方法，返回受影响行数
        Boolean aBoolean = mappingProjectService.updateProjectResultStatusById(id);
        return aBoolean;
    }

    /**
     * @author ligen
     * @description
     *  模糊查询-动态sql 查询测绘项目名称
     * @date 2020/5/27
     * @param [mappingProject]
     * @return java.util.List<com.aaa.xj.model.MappingProject>
     */
    @GetMapping("/fuzzyProjectName")
    public List<MappingProject> fuzzyProjectName(@RequestParam("projectName") String projectName,
                                                 @RequestParam("projectType") String projectType,
                                                 @RequestParam("startDate") String startDate) {
        // 调用 mappingProjectService 中的 fuzzyProjectName 方法，得到结果
        List<MappingProject> mappingProjects = mappingProjectService.fuzzyProjectName(projectName, projectType, startDate);

        // 判断 结果是否为空
        if (mappingProjects != null) {
            // 说明结果不为空，返回结果数据
            return mappingProjects;
        }else {
            // 返回null
            return null;
        }
    }

    /**
     * @author ligen
     * @description 系统主页-测绘项目
     *  查询项目基本信息
     * @date 2020/5/31
     * @param [id]
     * @return com.aaa.xj.model.MappingProject
     */
    @GetMapping("/selectProjectInfoById")
    public MappingProject selectProjectInfoById(@RequestParam("id") Long id) {
        // 调用 mappingProjectService 中的 selectProjectInfoById 方法，得到查询结果
        MappingProject mappingProject = mappingProjectService.selectProjectInfoById(id);

        // 判断 结果是否为空
        if (mappingProject != null) {
            // 说明结果不为空，查询成功，返回结果数据
            return mappingProject;
        }else {
            // 查询失败，返回null
            return null;
        }
    }

    /**
     * @author ligen
     * @description 系统主页-测绘项目-查看详情
     *  查询测绘项目详情-根据主键id查询
     * @date 2020/5/31
     * @param [id]
     * @return com.aaa.xj.model.MappingProject
     */
    @GetMapping("/selectProjectDetailById")
    public MappingProject selectProjectDetailById(@RequestParam("id") Long id) {
        // 调用 mappingProjectService 中的 selectProjectDetailById 方法，得到查询结果
        MappingProject mappingProject = mappingProjectService.selectProjectDetailById(id);

        // 判断 结果是否为空
        if (mappingProject != null) {
            // 说明结果不为空，查询成功，返回结果数据
            return mappingProject;
        }else {
            // 查询失败，返回null
            return null;
        }
    }

    /**
     * @author ligen
     * @description 系统主页-测绘单位-查看详情-项目信息
     *  查询，
     *      根据单位id 查询该单位下的所有项目信息，进行分页
     * @date 2020/5/31
     * @param [id, pageNo, pageSize]
     * @return com.github.pagehelper.PageInfo<com.aaa.xj.model.MappingProject>
     */
    @GetMapping("/queryProjectForUnitByUserId")
    public PageInfo<MappingProject> queryProjectForUnitByUserId(@RequestParam("id") Long id,
                                                                @RequestParam("pageNo") Integer pageNo,
                                                                @RequestParam("pageSize") Integer pageSize) {
        // 调用 mappingProjectService 中的 selectProjectForUnitByUserId 方法，得到查询结果
        PageInfo<MappingProject> projectPageInfo = mappingProjectService.selectProjectForUnitByUserId(id, pageNo, pageSize);

        // 判断 结果是否为空
        if (projectPageInfo != null) {
            // 说明结果不为空，查询成功，返回结果数据
            return projectPageInfo;
        }else {
            // 查询失败，返回null
            return null;
        }
    }

    /**
     * @author ligen
     * @description 项目审核-项目信息
     *  查询所有的汇交成果为通过的项目信息 results_status=0
     * @date 2020/6/1
     * @param [pageNo, pageSize]
     * @return com.github.pagehelper.PageInfo<com.aaa.xj.model.MappingProject>
     */
    @GetMapping("/selectAllProjectAudit")
    public PageInfo<MappingProject> selectAllProjectAudit(@RequestParam("pageNo") Integer pageNo,
                                                          @RequestParam("pageSize") Integer pageSize) {
        // 调用 mappingProjectService 中的 selectAllProjectAudit 方法，得到查询结果
        PageInfo<MappingProject> projectPageInfo = mappingProjectService.selectAllProjectAudit(pageNo, pageSize);

        // 判断 结果是否为空
        if (null != projectPageInfo) {
            // 说明结果不为空，查询成功，返回结果数据
            return projectPageInfo;
        }else {
            // 查询失败，返回null
            return null;
        }
    }

    /**
     * @author ligen
     * @description 项目审核-汇交成果信息
     *  查询所有的汇交成果为通过项目信息
     *      条件查询-模糊查询，
     *      条件：项目名称 projectName
     * @date 2020/6/1
     * @param [projectName, pageNo, pageSize]
     * @return com.github.pagehelper.PageInfo<com.aaa.xj.model.MappingProject>
     */
    @GetMapping("/fuzzyProjectAuditByType")
    public PageInfo<MappingProject> fuzzyProjectAuditByType(@RequestParam("projectName") String projectName,
                                                            @RequestParam("pageNo") Integer pageNo,
                                                            @RequestParam("pageSize") Integer pageSize) {
        // 调用 mappingProjectService 中的 fuzzyProjectAuditByType 方法，得到查询结果
        PageInfo<MappingProject> fuzzyProjectAuditByType = mappingProjectService.fuzzyProjectAuditByType(projectName, pageNo, pageSize);

        // 判断 结果是否为空
        if (null != fuzzyProjectAuditByType) {
            // 说明结果不为空，查询成功，返回结果数据
            return fuzzyProjectAuditByType;
        }else {
            // 查询失败，返回null
            return null;
        }
    }

    /**
     * @author ligen
     * @description 项目审核-项目信息
     *  查询项目详细信息-主键查询
     * @date 2020/6/1
     * @param [id]
     * @return com.aaa.xj.model.MappingProject
     */
    @GetMapping("/selectProjectAuditById")
    public MappingProject selectProjectAuditById(@RequestParam("id") Long id) {
        // 调用 mappingProjectService 中的 selectProjectAuditById 方法，得到查询结果
        MappingProject mappingProject = mappingProjectService.selectProjectAuditById(id);

        // 判断 结果是否为空
        if (null != mappingProject) {
            // 说明结果不为空，查询成功，返回结果数据
            return mappingProject;
        }else {
            // 查询失败，返回null
            return null;
        }
    }

    /**
     * @author ligen
     * @description 项目审核-汇交成果信息
     *  查询所有的汇交成果为通过的项目信息 results_status=0
     * @date 2020/6/2
     * @param [pageNo, pageSize]
     * @return com.github.pagehelper.PageInfo<com.aaa.xj.model.MappingProject>
     */
    @GetMapping("/selectAllProjectResultAudit")
    public PageInfo<MappingProject> selectAllProjectResultAudit(@RequestParam("pageNo") Integer pageNo,
                                                                @RequestParam("pageSize") Integer pageSize) {
        // 调用 mappingProjectService 中的 selectAllProjectResultAudit 方法，得到查询结果
        PageInfo<MappingProject> projectPageInfo = mappingProjectService.selectAllProjectResultAudit(pageNo, pageSize);

        // 判断 结果是否为空
        if (null != projectPageInfo) {
            // 说明结果不为空，查询成功，返回结果数据
            return projectPageInfo;
        }else {
            // 查询失败，返回null
            return null;
        }
    }

    /**
     * @author ligen
     * @description 项目审核-汇交成果信息
     *  查询所有的汇交成果为通过的项目信息
     *      条件查询-模糊查询，
     *      条件：项目名称 projectName
     * @date 2020/6/2
     * @param [projectName, pageNo, pageSize]
     * @return com.github.pagehelper.PageInfo<com.aaa.xj.model.MappingProject>
     */
    @GetMapping("/fuzzyProjectResultAuditByType")
    public PageInfo<MappingProject> fuzzyProjectResultAuditByType(@RequestParam("projectName") String projectName,
                                                                  @RequestParam("pageNo") Integer pageNo,
                                                                  @RequestParam("pageSize") Integer pageSize) {
        // 调用 mappingProjectService 中的 fuzzyProjectResultAuditByType 方法，得到查询结果
        PageInfo<MappingProject> fuzzyProjectResultAuditByType = mappingProjectService.fuzzyProjectResultAuditByType(projectName, pageNo, pageSize);

        // 判断 结果是否为空
        if (null != fuzzyProjectResultAuditByType) {
            // 说明结果不为空，查询成功，返回结果数据
            return fuzzyProjectResultAuditByType;
        }else {
            // 查询失败，返回null
            return null;
        }
    }

    /**
     * @author ligen
     * @description 项目审核-汇交成果信息-查看
     *  查看汇交成果项目详情
     * @date 2020/6/2
     * @param [id]
     * @return com.aaa.xj.model.MappingProject
     */
    @GetMapping("/selectProjectResultAuditById")
    public MappingProject selectProjectResultAuditById(@RequestParam("id") Long id) {
        // 调用 mappingProjectService 中的 selectProjectResultAuditById 方法，得到查询结果
        MappingProject mappingProject = mappingProjectService.selectProjectResultAuditById(id);

        // 判断 结果是否为空
        if (null != mappingProject) {
            // 说明结果不为空，查询成功，返回结果数据
            return mappingProject;
        }else {
            // 查询失败，返回null
            return null;
        }
    }

    /**
     * @author ligen
     * @description 项目审核-项目审核
     *  查询所有待审核的项目信息
     *      项目审核结果为已提交 audit_status=2
     * @date 2020/6/2
     * @param [pageNo, pageSize]
     * @return com.github.pagehelper.PageInfo<com.aaa.xj.model.MappingProject>
     */
    @GetMapping("/selectAllProjectToAudit")
    public PageInfo<MappingProject> selectAllProjectToAudit(@RequestParam("pageNo") Integer pageNo,
                                                            @RequestParam("pageSize") Integer pageSize) {
        // 调用 mappingProjectService 中的 selectAllProjectToAudit 方法，得到查询结果
        PageInfo<MappingProject> projectPageInfo = mappingProjectService.selectAllProjectToAudit(pageNo, pageSize);

        // 判断 结果是否为空
        if (null != projectPageInfo) {
            // 说明结果不为空，查询成功，返回结果数据
            return projectPageInfo;
        }else {
            // 查询失败，返回null
            return null;
        }
    }

    /**
     * @author ligen
     * @description 项目审核-项目审核-条件查询
     *  条件查询-模糊查询，项目名称
     *  查询所有待审核的项目信息
     *      项目审核结果为已提交 audit_status=2
     * @date 2020/6/2
     * @param [projectName, pageNo, pageSize]
     * @return com.github.pagehelper.PageInfo<com.aaa.xj.model.MappingProject>
     */
    @GetMapping("/fuzzyProjectToAuditByPName")
    public PageInfo<MappingProject> fuzzyProjectToAuditByPName(@RequestParam("projectName") String projectName,
                                                               @RequestParam("pageNo") Integer pageNo,
                                                               @RequestParam("pageSize") Integer pageSize) {
        // 调用 mappingProjectService 中的 fuzzyProjectToAuditByPName 方法，得到查询结果
        PageInfo<MappingProject> projectPageInfo = mappingProjectService.fuzzyProjectToAuditByPName(projectName, pageNo, pageSize);

        // 判断 结果是否为空
        if (null != projectPageInfo) {
            // 说明结果不为空，查询成功，返回结果数据
            return projectPageInfo;
        }else {
            // 查询失败，返回null
            return null;
        }
    }

    /**
     * @author ligen
     * @description 项目审核-项目审核-审核
     *  更改项目审核结果 audit_status
     *      0 通过；1 不通过。
     *  并 添加审核日志 memo审核意见，status==audit_status审核状态
     * @date 2020/6/3
     * @param [audit, id, auditStatus]
     * @return java.lang.Boolean
     */
    @PostMapping("/updateProjectAuditStatus")
    public Boolean updateProjectAuditStatus(@RequestBody Audit audit,
                                            @RequestParam("id") Long id,
                                            @RequestParam("auditStatus") Integer auditStatus) {
        // 调用 mappingProjectService 中的 updateProjectAuditStatus 方法，得到结果
        Boolean aBoolean = mappingProjectService.updateProjectAuditStatus(audit, id, auditStatus);

        // 判断 结果是否为true
        if (aBoolean == true) {
            // 操作成功
            return true;
        }else {
            // 操作失败
            return false;
        }
    }

    /**
     * @author ligen
     * @description 项目审核-汇交成果审核
     *  查询所有待审核的汇交成果项目信息
     *      汇交成果状态为已提交 results_status=2
     * @date 2020/6/2
     * @param [pageNo, pageSize]
     * @return com.github.pagehelper.PageInfo<com.aaa.xj.model.MappingProject>
     */
    @GetMapping("/selectAllProjectResultToAudit")
    public PageInfo<MappingProject> selectAllProjectResultToAudit(@RequestParam("pageNo") Integer pageNo,
                                                                  @RequestParam("pageSize") Integer pageSize) {
        // 调用 mappingProjectService 中的 selectAllProjectResultToAudit 方法，得到查询结果
        PageInfo<MappingProject> projectPageInfo = mappingProjectService.selectAllProjectResultToAudit(pageNo, pageSize);

        // 判断 结果是否为空
        if (null != projectPageInfo) {
            // 说明结果不为空，查询成功，返回结果数据
            return projectPageInfo;
        }else {
            // 查询失败，返回null
            return null;
        }
    }

    /**
     * @author ligen
     * @description 项目审核-汇交成果审核-条件模糊查询
     *  条件查询-模糊查询，项目名称 projectName
     *  查询所有待审核的汇交成果项目信息
     *      汇交成果状态为已提交 results_status=2
     * @date 2020/6/2
     * @param [projectName, pageNo, pageSize]
     * @return com.github.pagehelper.PageInfo<com.aaa.xj.model.MappingProject>
     */
    @GetMapping("/fuzzyProjectResultToAuditByPName")
    public PageInfo<MappingProject> fuzzyProjectResultToAuditByPName(@RequestParam("projectName") String projectName,
                                                                     @RequestParam("pageNo") Integer pageNo,
                                                                     @RequestParam("pageSize") Integer pageSize) {
        // 调用 mappingProjectService 中的 fuzzyProjectResultToAuditByPName 方法，得到查询结果
        PageInfo<MappingProject> projectPageInfo = mappingProjectService.fuzzyProjectResultToAuditByPName(projectName, pageNo, pageSize);

        // 判断 结果是否为空
        if (null != projectPageInfo) {
            // 说明结果不为空，查询成功，返回结果数据
            return projectPageInfo;
        }else {
            // 查询失败，返回null
            return null;
        }
    }

    /**
     * @author ligen
     * @description 项目审核-汇交成果审核-审核
     *      t_mapping_project：
     *          id 操作项目编号id；
     *          results_status 项目审核结果，0 通过；1 不通过；
     *      操作成功，新增审核日志：memo审核意见，status==audit_status审核状态
     * @date 2020/6/3
     * @param [audit, id, auditStatus]
     * @return java.lang.Boolean
     */
    @PostMapping("/updateProjectResultStatus")
    public Boolean updateProjectResultStatus(@RequestBody Audit audit,
                                             @RequestParam("id") Long id,
                                             @RequestParam("resultsStatus") Integer resultsStatus) {
        // 调用 mappingProjectService 中的 updateProjectAuditStatus 方法，得到结果
        Boolean aBoolean = mappingProjectService.updateProjectResultStatus(audit, id, resultsStatus);

        // 判断 结果是否为true
        if (aBoolean == true) {
            // 操作成功
            return true;
        }else {
            // 操作失败
            return false;
        }
    }

}
