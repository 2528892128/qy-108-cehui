package com.aaa.xj.controller;

import com.aaa.xj.model.MappingProject;
import com.aaa.xj.service.MappingProjectService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/selectAll")
    public List<MappingProject> selectAllProject() {
        // 调用 mappingProjectService 中的 getAllMappingProject 方法获取数据
        List<MappingProject> projectList = mappingProjectService.selectAllMappingProject();

        return projectList;
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
    @GetMapping ("/selectAllByType")
    public List<MappingProject> selectAllByType(String projectType) {
        // 调用 mappingProjectService 中的 getAllByProjectType 方法获取数据
        List<MappingProject> allByType = mappingProjectService.selectAllByProjectType(projectType);

        // 返回查询的结果
        return allByType;
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
    @GetMapping("/selectALLByPage")
    public PageInfo selectALLByPage(MappingProject mappingProject, Integer pageNo, Integer pageSize) {
        // 调用 mappingProjectService 中的 getAllByProjectType 方法，查询
        PageInfo pageInfo = mappingProjectService.selectALLByPage(mappingProject, pageNo, pageSize);
        return pageInfo;
    }

}
