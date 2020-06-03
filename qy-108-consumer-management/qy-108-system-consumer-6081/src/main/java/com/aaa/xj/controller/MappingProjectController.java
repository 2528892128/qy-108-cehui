package com.aaa.xj.controller;

import com.aaa.xj.base.BaseController;
import com.aaa.xj.base.ResultData;
import com.aaa.xj.model.Audit;
import com.aaa.xj.model.MappingProject;
import com.aaa.xj.service.IQYService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ligen
 * @program qy-108-cehui
 * @description
 *  系统主页-测绘项目
 * @create 2020-05-22 15:32
 */
@RestController
@Api(value = "项目汇交", tags = "项目汇交接口")
public class MappingProjectController extends BaseController {
    @Autowired
    private IQYService iqyService;

    /**
     * @author ligen
     * @description 项目汇交
     *  查询所有未提交的汇交成果
     *      汇交成果状态 results_status=3
     * @date 2020/6/2
     * @param [pageNo, pageSize]
     * @return com.aaa.xj.base.ResultData<com.aaa.xj.model.MappingProject>
     */
    @GetMapping("/getAllProjectResult")
    public ResultData<MappingProject> getAllProjectResult(Integer pageNo, Integer pageSize) {
        // 调用 iqyService 中的 selectAllProjectResult 方法，得到结果
        PageInfo<MappingProject> allProjectResult = iqyService.selectAllProjectResult(pageNo, pageSize);

        // 判断 结果是否为空
        if (null != allProjectResult) {
            // 说明拿到数据，返回系统查询成功，使用系统消息、自定义返回值
            return getSuccess(allProjectResult);
        }else {
            // 查询失败，使用系统消息
            return getFalse();
        }
    }

    /**
     * @author ligen
     * @description 项目汇交-根据项目类型查询
     *  条件查询 根据项目类型 projectType，查询所有的 项目汇交信息，进行分页
     *      项目类型分为：基础测绘，专业测绘
     * @date 2020/6/2
     * @param [projectType, pageNo, pageSize]
     * @return com.aaa.xj.base.ResultData<com.aaa.xj.model.MappingProject>
     */
    @GetMapping("/getAllProjectResultByType")
    public ResultData<MappingProject> getAllProjectResultByType(String projectType, Integer pageNo, Integer pageSize){
        // 调用 iqyService 中的 selectAllProjectResultByType 方法，得到查询结果
        PageInfo<MappingProject> allProjectResultByType = iqyService.selectAllProjectResultByType(projectType, pageNo, pageSize);

        // 判断 结果是否为空
        if (null != allProjectResultByType) {
            // 说明结果不为空，查询成功，使用系统消息、自定义返回值
            return getSuccess(allProjectResultByType);
        }else {
            // 查询失败，返回null
            return getFalse();
        }
    }

    /**
     * @author ligen
     * @description 项目汇交-操作
     *  修改汇交成果状态 results_status=2
     *  场景：点击按钮提交汇交成果项目
     * @date 2020/6/2
     * @param [id]
     * @return com.aaa.xj.base.ResultData<com.aaa.xj.model.MappingProject>
     */
    @PostMapping("/updateProjectResultStatusById")
    public ResultData<MappingProject> updateProjectResultStatusById(Long id) {
        // 调用 iqyService 中的 updateProjectResultStatusById 方法，得到结果
        Boolean aBoolean = iqyService.updateProjectResultStatusById(id);

        // 判断 结果是否为true
        if (aBoolean == true) {
            // 修改成功，使用系统消息
            return updateSuccess();
        }else {
            // 修改失败，使用系统消息
            return updateFalse();
        }
    }

    /**
     * @author ligen
     * @description
     *  模糊查询-动态sql 查询测绘项目名称
     * @date 2020/5/27
     * @param [mappingProject]
     * @return com.aaa.xj.base.ResultData<com.aaa.xj.model.MappingProject>
     */
    @GetMapping("/fuzzyProjectName")
    public ResultData<MappingProject> fuzzyProjectName(String projectName, String projectType, String startDate) {
        // 调用 iqyService 中的 fuzzyProjectName 方法，得到结果
        List<MappingProject> fuzzyProjectName = iqyService.fuzzyProjectName(projectName, projectType, startDate);

        // 判断 结果是否为空
        if (fuzzyProjectName != null) {
            // 说明结果不为空，返回结果数据
            return getSuccess(fuzzyProjectName);
        }else {
            // 返回null
            return getFalse();
        }
    }

    /**
     * @author ligen
     * @description 系统主页-测绘项目
     *  查询项目基本信息
     * @date 2020/5/31
     * @param [id]
     * @return com.aaa.xj.base.ResultData<com.aaa.xj.model.MappingProject>
     */
    @GetMapping("/selectProjectInfoById")
    public ResultData<MappingProject> selectProjectInfoById(Long id) {
        // 调用 iqyService 中的 selectProjectInfoById 方法，得到查询结果
        MappingProject mappingProject = iqyService.selectProjectInfoById(id);

        // 判断 结果是否为空
        if (mappingProject != null) {
            // 说明结果不为空，返回结果数据
            return getSuccess(mappingProject);
        }else {
            // 返回null
            return getFalse();
        }
    }

    /**
     * @author ligen
     * @description 系统主页-测绘项目-查看详情
     *  查询测绘项目详情-根据主键id查询
     * @date 2020/5/31
     * @param [id]
     * @return com.aaa.xj.base.ResultData<com.aaa.xj.model.MappingProject>
     */
    @GetMapping("/selectProjectDetailById")
    public ResultData<MappingProject> selectProjectDetailById(Long id) {
        // 调用 iqyService 中的 selectProjectDetailById 方法，得到查询结果
        MappingProject mappingProject = iqyService.selectProjectDetailById(id);

        // 判断 结果是否为空
        if (mappingProject != null) {
            // 说明结果不为空，返回结果数据
            return getSuccess(mappingProject);
        }else {
            // 返回null
            return getFalse();
        }
    }

    /**
     * @author ligen
     * @description 系统主页-测绘单位-查看详情-项目信息
     *  查询，
     *      根据单位id 查询该单位下的项目信息，进行分页
     * @date 2020/5/31
     * @param [id, pageNo, pageSize]
     * @return com.aaa.xj.base.ResultData<com.aaa.xj.model.MappingProject>
     */
    @GetMapping("/queryProjectForUnitByUserId")
    public ResultData<MappingProject> queryProjectForUnitByUserId(Long id, Integer pageNo, Integer pageSize) {
        // 调用 iqyService 中的 queryProjectForUnitByUserId 方法，得到结果
        PageInfo<MappingProject> projectPageInfo = iqyService.queryProjectForUnitByUserId(id, pageNo, pageSize);

        // 判断 结果是否为空
        if (projectPageInfo != null) {
            // 说明结果不为空，返回结果数据
            return getSuccess(projectPageInfo);
        }else {
            // 返回null
            return getFalse();
        }
    }

    /**
     * @author ligen
     * @description 项目审核-项目信息
     *  查询所有的汇交成果为通过的项目信息 results_status=0
     * @date 2020/6/1
     * @param [pageNo, pageSize]
     * @return com.aaa.xj.base.ResultData<com.aaa.xj.model.MappingProject>
     */
    @GetMapping("/getAllProjectAudit")
    public ResultData<MappingProject> getAllProjectAudit(Integer pageNo, Integer pageSize) {
        // 调用 iqyService 中的 selectAllProjectAudit 方法，得到查询结果
        PageInfo<MappingProject> projectPageInfo = iqyService.selectAllProjectAudit(pageNo, pageSize);

        // 判断 结果是否为空
        if (projectPageInfo != null) {
            // 说明结果不为空，查询成功，返回结果数据
            return getSuccess(projectPageInfo);
        }else {
            // 查询失败返回null
            return getFalse();
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
     * @return com.aaa.xj.base.ResultData<com.aaa.xj.model.MappingProject>
     */
    @GetMapping("/fuzzyProjectAuditByType")
    public ResultData<MappingProject> fuzzyProjectAuditByType(String projectName, Integer pageNo, Integer pageSize) {
        // 调用 iqyService 中的 fuzzyProjectAuditByType 方法，得到查询结果
        PageInfo<MappingProject> projectPageInfo = iqyService.fuzzyProjectAuditByType(projectName, pageNo, pageSize);

        // 判断 结果是否为空
        if (null != projectPageInfo) {
            // 说明结果不为空，查询成功，返回结果数据
            return getSuccess(projectPageInfo);
        }else {
            // 查询失败返回null
            return getFalse();
        }
    }

    /**
     * @author ligen
     * @description 项目审核-项目信息
     *  查询项目详细信息-主键查询
     * @date 2020/6/1
     * @param [id]
     * @return com.aaa.xj.base.ResultData<com.aaa.xj.model.MappingProject>
     */
    @GetMapping("/getProjectAuditById")
    public ResultData<MappingProject> getProjectAuditById(Long id) {
        // 调用 iqyService 中的 selectProjectAuditById 方法，得到查询结果
        MappingProject mappingProject = iqyService.selectProjectAuditById(id);

        // 判断 结果是否为空
        if (null != mappingProject) {
            // 说明结果不为空，查询成功，返回结果数据
            return getSuccess(mappingProject);
        }else {
            // 查询失败返回null
            return getFalse();
        }
    }

    /**
     * @author ligen
     * @description 项目审核-汇交成果信息
     *  查询所有的汇交成果为通过的项目信息 results_status=0
     * @date 2020/6/2
     * @param [pageNo, pageSize]
     * @return com.aaa.xj.base.ResultData<com.aaa.xj.model.MappingProject>
     */
    @GetMapping("/getAllProjectResultAudit")
    public ResultData<MappingProject> getAllProjectResultAudit(Integer pageNo, Integer pageSize) {
        // 调用 iqyService 中的 selectAllProjectAudit 方法，得到查询结果
        PageInfo<MappingProject> projectPageInfo = iqyService.selectAllProjectResultAudit(pageNo, pageSize);

        // 判断 结果是否为空
        if (projectPageInfo != null) {
            // 说明结果不为空，查询成功，返回结果数据
            return getSuccess(projectPageInfo);
        }else {
            // 查询失败返回null
            return getFalse();
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
     * @return com.aaa.xj.base.ResultData<com.aaa.xj.model.MappingProject>
     */
    @GetMapping("/fuzzyProjectResultAuditByType")
    public ResultData<MappingProject> fuzzyProjectResultAuditByType(String projectName, Integer pageNo, Integer pageSize) {
        // 调用 iqyService 中的 fuzzyProjectResultAuditByType 方法，得到查询结果
        PageInfo<MappingProject> projectPageInfo = iqyService.fuzzyProjectResultAuditByType(projectName, pageNo, pageSize);

        // 判断 结果是否为空
        if (null != projectPageInfo) {
            // 说明结果不为空，查询成功，返回结果数据
            return getSuccess(projectPageInfo);
        }else {
            // 查询失败返回null
            return getFalse();
        }
    }

    /**
     * @author ligen
     * @description 项目审核-汇交成果信息-查看
     *  查看汇交成果项目详情
     * @date 2020/6/2
     * @param [id]
     * @return com.aaa.xj.base.ResultData<com.aaa.xj.model.MappingProject>
     */
    @GetMapping("/getProjectResultAuditById")
    public ResultData<MappingProject> getProjectResultAuditById(Long id) {
        // 调用 iqyService 中的 selectProjectResultAuditById 方法，得到查询结果
        MappingProject mappingProject = iqyService.selectProjectResultAuditById(id);

        // 判断 结果是否为空
        if (null != mappingProject) {
            // 说明结果不为空，查询成功，返回结果数据
            return getSuccess(mappingProject);
        }else {
            // 查询失败返回null
            return getFalse();
        }
    }

    /**
     * @author ligen
     * @description 项目审核-项目审核
     *  查询所有待审核的项目信息
     *      项目审核结果为已提交 audit_status=2
     * @date 2020/6/2
     * @param [pageNo, pageSize]
     * @return com.aaa.xj.base.ResultData<com.aaa.xj.model.MappingProject>
     */
    @GetMapping("/getAllProjectToAudit")
    public ResultData<MappingProject> getAllProjectToAudit(Integer pageNo, Integer pageSize) {
        // 调用 iqyService 中的 selectAllProjectToAudit 方法，得到查询结果
        PageInfo<MappingProject> projectPageInfo = iqyService.selectAllProjectToAudit(pageNo, pageSize);

        // 判断 结果是否为空
        if (null != projectPageInfo) {
            // 说明结果不为空，查询成功，返回结果数据
            return getSuccess(projectPageInfo);
        }else {
            // 查询失败返回null
            return getFalse();
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
     * @return com.aaa.xj.base.ResultData<com.aaa.xj.model.MappingProject>
     */
    @GetMapping("/fuzzyProjectToAuditByPName")
    public ResultData<MappingProject> fuzzyProjectToAuditByPName(String projectName, Integer pageNo, Integer pageSize) {
        // 调用 iqyService 中的 fuzzyProjectToAuditByPName 方法，得到查询结果
        PageInfo<MappingProject> projectPageInfo = iqyService.fuzzyProjectToAuditByPName(projectName, pageNo, pageSize);

        // 判断 结果是否为空
        if (null != projectPageInfo) {
            // 说明结果不为空，查询成功，返回结果数据
            return getSuccess(projectPageInfo);
        }else {
            // 查询失败返回null
            return getFalse();
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
    public ResultData<MappingProject> updateProjectAuditStatus(@RequestBody Audit audit,
                                                               @RequestParam("id") Long id,
                                                               @RequestParam("auditStatus") Integer auditStatus) {
        // 调用 iqyService 中的 updateProjectAuditStatus 方法，得到审核操作结果
        Boolean aBoolean = iqyService.updateProjectAuditStatus(audit, id, auditStatus);

        // 判断 结果是否为true
        if (aBoolean == true) {
            // 操作成功
            return updateSuccess();
        }else {
            // 操作失败
            return updateFalse();
        }
    }

    /**
     * @author ligen
     * @description 项目审核-汇交成果审核
     *  查询所有待审核的汇交成果项目信息
     *      汇交成果状态为已提交 results_status=2
     * @date 2020/6/3
     * @param [pageNo, pageSize]
     * @return com.aaa.xj.base.ResultData<com.aaa.xj.model.MappingProject>
     */
    @GetMapping("/selectAllProjectResultToAudit")
    public ResultData<MappingProject> selectAllProjectResultToAudit(Integer pageNo, Integer pageSize) {
        // 调用 iqyService 中的 selectAllProjectResultToAudit 方法，得到查询结果
        PageInfo<MappingProject> projectPageInfo = iqyService.selectAllProjectResultToAudit(pageNo, pageSize);

        // 判断 结果是否为空
        if (null != projectPageInfo) {
            // 说明结果不为空，查询成功，返回结果数据
            return getSuccess(projectPageInfo);
        }else {
            // 查询失败返回null
            return getFalse();
        }
    }

    /**
     * @author ligen
     * @description 项目审核-汇交成果审核-条件模糊查询
     *  条件查询-模糊查询，项目名称 projectName
     *  查询所有待审核的汇交成果项目信息
     *      汇交成果状态为已提交 results_status=2
     * @date 2020/6/3
     * @param [projectName, pageNo, pageSize]
     * @return com.aaa.xj.base.ResultData<com.aaa.xj.model.MappingProject>
     */
    @GetMapping("/fuzzyProjectResultToAuditByPName")
    public ResultData<MappingProject> fuzzyProjectResultToAuditByPName(String projectName, Integer pageNo, Integer pageSize) {
        // 调用 iqyService 中的 fuzzyProjectResultToAuditByPName 方法，得到查询结果
        PageInfo<MappingProject> projectPageInfo = iqyService.fuzzyProjectResultToAuditByPName(projectName, pageNo, pageSize);

        // 判断 结果是否为空
        if (null != projectPageInfo) {
            // 说明结果不为空，查询成功，返回结果数据
            return getSuccess(projectPageInfo);
        }else {
            // 查询失败返回null
            return getFalse();
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
     * @param [audit, id, resultsStatus]
     * @return com.aaa.xj.base.ResultData<com.aaa.xj.model.MappingProject>
     */
    @PostMapping("/updateProjectResultStatus")
    public ResultData<MappingProject> updateProjectResultStatus(@RequestBody Audit audit,
                                                                @RequestParam("id") Long id,
                                                                @RequestParam("resultsStatus") Integer resultsStatus) {
        // 调用 iqyService 中的 updateProjectResultStatus 方法，得到审核操作结果
        Boolean aBoolean = iqyService.updateProjectResultStatus(audit, id, resultsStatus);

        // 判断 结果是否为true
        if (aBoolean == true) {
            // 操作成功
            return updateSuccess();
        }else {
            // 操作失败
            return updateFalse();
        }
    }
}
