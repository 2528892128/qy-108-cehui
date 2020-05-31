package com.aaa.xj.controller;

import com.aaa.xj.base.BaseController;
import com.aaa.xj.base.ResultData;
import com.aaa.xj.model.MappingProject;
import com.aaa.xj.service.IQYService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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
     *  查询所有的 项目汇交信息，带分页
     *      参数：
     *          pageNo 当前页数，
     *          pageSize 每页数据个数
     * @date 2020/5/22
     * @param []
     * @return com.aaa.xj.base.ResultData<com.aaa.xj.model.MappingProject>
     */
    @PostMapping("/getAllProjectResult")
    public ResultData<MappingProject> getAllProjectResult(MappingProject mappingProject, Integer pageNo, Integer pageSize) {
        // 调用 iqyService 中的 queryAllProjectResult 方法，得到结果
        PageInfo<MappingProject> allProjectResult = iqyService.queryAllProjectResult(mappingProject, pageNo, pageSize);

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
     * @date 2020/5/29
     * @param [mappingProject, pageNo, pageSize]
     * @return com.github.pagehelper.PageInfo
     */
    @GetMapping("/getAllProjectResultByType")
    public ResultData<MappingProject> getAllProjectResultByType(String projectType, Integer pageNo, Integer pageSize){
        // 调用 iqyService 中的 queryALLProjectByPage 方法，得到查询结果
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

}
