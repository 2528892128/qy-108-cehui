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
 *  项目汇交
 * @create 2020-05-22 15:32
 */
@RestController
@Api(value = "项目汇交", tags = "项目汇交接口")
public class MappingProjectController extends BaseController {
    @Autowired
    private IQYService iqyService;

    /**
     * @author ligen
     * @description
     *  查询所有的，已提交的项目信息
     * @date 2020/5/22
     * @param []
     * @return com.aaa.xj.base.ResultData<com.aaa.xj.model.MappingProject>
     */
    @GetMapping("/getAllProject")
    public ResultData<MappingProject> getAllProject() {
        // 调用 iqyService 中的 selectAllProject 方法获取数据
        List<MappingProject> allMapping = iqyService.selectAllProject();

        // 判断 结果是否为空
        if (null != allMapping && allMapping.size() > 0) {
            // 说明拿到数据，返回系统查询成功，使用系统消息、自定义返回值
            return getSuccess(allMapping);
        }else {
            // 查询失败，使用系统消息
            return getFalse();
        }
    }

    /**
     * @author ligen
     * @description
     *  查询测绘项目的详情信息
     * @date 2020/5/29
     * @param [id]
     * @return com.aaa.xj.base.ResultData<com.aaa.xj.model.MappingProject>
     */
    @GetMapping("/getAllProjectDetailById")
    public ResultData<MappingProject> selectAllProjectDetailById(Long id) {
        // 调用 iqyService 中的 selectAllProjectDetailById 方法获取数据
        MappingProject projectDetailById = iqyService.selectAllProjectDetailById(id);

        // 判断 结果是否为空
        if (projectDetailById != null && !"".equals(projectDetailById)) {
            // 说明结果不为空，查询成果，返回系统消息 自定义返回值
            return getSuccess(projectDetailById);
        }else {
            // 返回null
            return null;
        }
    }

    /**
     * @author ligen
     * @description
     *  条件查询，根据项目类型 projectType，查询所有的 已提交的项目信息
     *      项目类型分为：基础测绘，专业测绘
     * @date 2020/5/22
     * @param [projectType]
     * @return java.util.List<com.aaa.xj.model.MappingProject>
     */
    @GetMapping ("/getAllProjectByType")
    public ResultData<MappingProject> getAllProjectByType(String projectType) {
        // 调用 iqyService 中的 selectAllProjectByType 方法获取数据
        List<MappingProject> projectList = iqyService.selectAllProjectByType(projectType);

        // 判断 结果是否为空
        if (null != projectList && projectList.size() > 0) {
            // 说明结果不为空，查询成功，使用系统消息、自定义返回值
            return getSuccess(projectList);
        }else {
            // 查询失败，返回null
            return getFalse();
        }
    }

    /**
     * @author ligen
     * @description
     *  分页查询 ，将查询的所有已提交项目信息，进行分页
     *      参数：pageNo 当前页数，pageSize 每页数据个数
     * @date 2020/5/29
     * @param [mappingProject, pageNo, pageSize]
     * @return com.github.pagehelper.PageInfo
     */
    @PostMapping("/queryALLProjectByPage")
    public ResultData<MappingProject> queryALLProjectByPage(MappingProject mappingProject, Integer pageNo, Integer pageSize){
        // 调用 iqyService 中的 queryALLProjectByPage 方法获取数据
        PageInfo allProjectByPage = iqyService.queryALLProjectByPage(mappingProject, pageNo, pageSize);

        // 判断 结果是否为空
        if (null != allProjectByPage && !"".equals(allProjectByPage)) {
            // 说明结果不为空，查询成功，使用系统消息、自定义返回值
            return getSuccess(allProjectByPage);
        }else {
            // 查询失败，返回null
            return getFalse();
        }
    }

    /**
     * @author ligen
     * @description
     *  查询分页，将 根据项目类型查询的结果进行分页
     * @date 2020/5/29
     * @param [projectType, pageNo, pageSize]
     * @return com.aaa.xj.base.ResultData<com.aaa.xj.model.MappingProject>
     */
    @GetMapping("/queryProjectByType")
    public ResultData<MappingProject> queryALLProjectByType(String projectType, Integer pageNo, Integer pageSize) {
        // 调用 iqyService 中的 queryListByPage 方法获取数据
        PageInfo<MappingProject> mappingProjectPageInfo = iqyService.queryListByPage(projectType, pageNo, pageSize);

        // 判断 结果是否为空
        if (null != mappingProjectPageInfo && !"".equals(mappingProjectPageInfo)) {
            // 说明结果不为空，查询成功，使用系统消息、自定义返回值
            return getSuccess(mappingProjectPageInfo);
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

}
