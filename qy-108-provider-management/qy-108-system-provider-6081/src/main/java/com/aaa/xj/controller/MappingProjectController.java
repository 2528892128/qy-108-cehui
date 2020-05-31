package com.aaa.xj.controller;

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
 *  项目汇交
 * @create 2020-05-22 14:01
 */
@RestController
public class MappingProjectController {
    @Autowired
    private MappingProjectService mappingProjectService;

    /**
     * @author ligen
     * @description
     *  查询所有的，已提交的项目信息
     * @date 2020/5/22
     * @param []
     * @return java.util.List<com.aaa.xj.model.MappingProject>
     */
    @GetMapping("/selectAllProject")
    public List<MappingProject> selectAllProject() {
        // 调用 mappingProjectService 中的 selectAllProject 方法，返回结果
        List<MappingProject> projectList = mappingProjectService.selectAllProject();

        // 判断 结果不为空
        if (projectList != null && !"".equals(projectList)){
            // 说明结果不为空，返回结果
            return projectList;
        }else {
            // 返回null
            return null;
        }
    }

    /**
     * @author ligen
     * @description
     *  查询测绘项目的详情信息
     * @date 2020/5/29
     * @param [id]
     * @return com.aaa.xj.model.MappingProject
     */
    @GetMapping("/selectAllProjectDetailById")
    public MappingProject selectAllProjectDetailById(@RequestParam("id") Long id) {
        // 调用 mappingProjectService 中的 selectAllProjectDetailById 方法，返回结果
        MappingProject mappingProject = mappingProjectService.selectAllProjectDetailById(id);

        // 判断 结果不为空
        if (mappingProject != null) {
            // 说明结果不为空，返回结果
            return mappingProject;
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
     * @param projectType
     * @return java.util.List<com.aaa.xj.model.MappingProject>
     */
    @GetMapping ("/selectAllProjectByType")
    public List<MappingProject> selectAllProjectByType(@RequestParam("projectType") String projectType) {
        // 调用 mappingProjectService 中的 selectAllProjectByType 方法，得到结果
        List<MappingProject> allByType = mappingProjectService.selectAllProjectByType(projectType);

        // 判断 结果是否为空
        if (allByType != null){
            // 说明结果不为空，返回结果
            return allByType;
        }else {
            // 返回null
            return null;
        }
    }

    /**
     * @author ligen
     * @description
     *  分页查询 ，将查询的所有已提交项目信息，进行分页
     *      参数：pageNo 当前页数，pageSize 每页数据个数
     * @date 2020/5/23
     * @param [mappingProject, pageNo, pageSize]
     * @return com.github.pagehelper.PageInfo
     */
    @PostMapping("/queryALLProjectByPage")
    public PageInfo queryALLProjectByPage(@RequestBody MappingProject mappingProject,
                                          @RequestParam("pageNo") Integer pageNo,
                                          @RequestParam("pageSize") Integer pageSize) {
        // 调用 mappingProjectService 中的 selectALLProjectByPage 方法，得到结果
        PageInfo allProjectByPage = mappingProjectService.selectALLProjectByPage(mappingProject, pageNo, pageSize);

        // 判断 结果是否为空
        if (allProjectByPage != null) {
            // 说明结果不为空，返回结果
            return allProjectByPage;
        }else {
            // 返回null
            return null;
        }
    }

    /**
     * @author ligen
     * @description
     *  查询分页，将 根据项目类型查询的结果进行分页
     * @date 2020/5/29
     * @param [projectType, pageNo, pageSize]
     * @return com.github.pagehelper.PageInfo<com.aaa.xj.model.MappingProject>
     */
    @GetMapping("/queryProjectByPage")
    public PageInfo<MappingProject> queryListByPage(@RequestParam("projectType") String projectType,
                                                    @RequestParam("pageNo") Integer pageNo,
                                                    @RequestParam("pageSize") Integer pageSize) {
        PageInfo<MappingProject> projectPageInfo = mappingProjectService.queryListByPage(projectType, pageNo, pageSize);

        // 判断 结果是否为空
        if (projectPageInfo != null) {
            // 说明结果不为空，返回结果
            return projectPageInfo;
        }else {
            // 返回null
            return null;
        }
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

}
