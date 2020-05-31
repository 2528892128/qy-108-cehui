package com.aaa.xj.service;

import com.aaa.xj.base.BaseService;
import com.aaa.xj.mapper.MappingProjectMapper;
import com.aaa.xj.model.MappingProject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ligen
 * @program qy-108-cehui
 * @description
 *  测绘项目信息
 * @create 2020-05-22 13:50
 */
@Service
public class MappingProjectService extends BaseService<MappingProject> {
    @Autowired
    private MappingProjectMapper mappingProjectMapper;

    /**
     * @author ligen
     * @description
     *  查询所有的 已提交的项目信息
     * @date 2020/5/22
     * @param []
     * @return java.util.List<com.aaa.xj.model.MappingProject>
     */
    public List<MappingProject> selectAllProject() {
        List<MappingProject> projectList = null;
        try {
            // 调用 mappingProjectMapper 中的 selectAllProject 方法获取数据
            projectList = mappingProjectMapper.selectAllProject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 判断 如果结果不为空并且结果的个数大于0，返回拿到的数据
        if (null != projectList && projectList.size() > 0) {
            // 说明查询到了结果，返回查询的数据
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
    public MappingProject selectAllProjectDetailById(Long id) {
        MappingProject projectDetailById = null;
        try {
            // 调用 mappingProjectMapper 中的 selectAllProjectDetailById 方法，返回查询的结果
            projectDetailById = mappingProjectMapper.selectAllProjectDetailById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 判断 如果结果不为空并且结果的个数大于0，返回拿到的数据
        if (null != projectDetailById && !"".equals(projectDetailById)) {
            // 说明查询到了结果，返回查询的数据
            return projectDetailById;
        }else {
            // 返回null
            return null;
        }
    }

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
    public List<MappingProject> selectAllProjectByType(String projectType) {
        List<MappingProject> allByProjectType = null;
        try {
            // 调用 mappingProjectMapper 中的 selectAllByProjectType 方法获取数据
            allByProjectType = mappingProjectMapper.selectAllProjectByType(projectType);
        } catch (IllegalArgumentException e) {
            // 非法参数异常
            e.printStackTrace();
        }

        // 判断 如果结果不为空并且结果的个数大于0，返回拿到的数据
        if (null != allByProjectType && allByProjectType.size() > 0) {
            // 说明结果不为空，返回查询的数据
            return allByProjectType;
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
    public PageInfo selectALLProjectByPage(MappingProject mappingProject, Integer pageNo, Integer pageSize) {
        PageInfo<MappingProject> projectPageInfo = null;
        try {
            // 调用重写的分页查询方法，得到分页结果
            projectPageInfo = queryListByPage(mappingProject, pageNo, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 判断 结果是否为空
        if (null != projectPageInfo && !"".equals(projectPageInfo)) {
            // 说明查询的结果不是空，返回结果
            return projectPageInfo;
        }else {
            // 返回null
            return null;
        }
    }

    /**
     * @author ligen
     * @description
     *  分页查询方法
     *  重写父类 BaseService 中的 queryListByPage 方法
     *      使用自定义的 sql 语句，查询所有的 已提交的项目信息，将查询的结果进行分页
     * @date 2020/5/23
     * @param [mappingProject, pageNo, pageSize]
     * @return com.github.pagehelper.PageInfo<com.aaa.xj.model.MappingProject>
     */
    @Override
    public PageInfo<MappingProject> queryListByPage(MappingProject mappingProject, Integer pageNo, Integer pageSize) {
        List<MappingProject> select = null;
        PageInfo<MappingProject> pageInfo = null;
        try {
            // 设置分页，pageNO 当前页数，pageSize 每页数据个数
            PageHelper.startPage(pageNo, pageSize);
            // 使用自定义的sql语句，返回查询结果
            select = mappingProjectMapper.selectAllProject();
            // 将查询的结果 进行分页
            pageInfo = new PageInfo<MappingProject>(select);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        // 判断 结果是否为空
        if (null == pageInfo && "".equals(pageInfo)){
            // 说明结果是空，返回null
            return null;
        }else {
            // 返回 分页结果
            return pageInfo;
        }
    }

    /**
     * @author ligen
     * @description
     *  重载queryListByPage方法
     *  查询分页，将 根据项目类型查询的结果进行分页
     * @date 2020/5/29
     * @param [projectType, pageNo, pageSize]
     * @return com.github.pagehelper.PageInfo<com.aaa.xj.model.MappingProject>
     */
    public PageInfo<MappingProject> queryListByPage(String projectType, Integer pageNo, Integer pageSize) {
        List<MappingProject> selectByType = null;
        PageInfo<MappingProject> pageInfoByType = null;
        try {
            // 设置分页，pageNO 当前页数，pageSize 每页数据个数
            PageHelper.startPage(pageNo, pageSize);
            // 使用自定义的sql语句，返回查询结果
            selectByType = mappingProjectMapper.selectAllProjectByType(projectType);
            // 将查询的结果 进行分页
            pageInfoByType = new PageInfo<MappingProject>(selectByType);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        // 判断 结果是否为空
        if (null == pageInfoByType && "".equals(pageInfoByType)){
            // 说明结果是空，返回null
            return null;
        }else {
            // 返回 分页结果
            return pageInfoByType;
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
    public List<MappingProject> fuzzyProjectName(String projectName, String projectType, String startDate) {
        // 调用 mappingProjectMapper 中的 fuzzyProjectName 方法，得到查询结果
        List<MappingProject> mappingProjects = mappingProjectMapper.fuzzyProjectName(projectName, projectType, startDate);

        // 判断 结果是否为空
        if (mappingProjects != null && mappingProjects.size() > 0){
            // 说明结果不为空，返回结果
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
    public MappingProject selectProjectInfoById(Long id) {
        // 调用 mappingProjectMapper 中的 selectProjectInfoById 方法，得到查询结果
        MappingProject mappingProject = mappingProjectMapper.selectProjectInfoById(id);

        // 判断 结果是否为空
        if (null != mappingProject && !"".equals(mappingProject)) {
            // 说明结果不为空，查询成功，返回分页结果
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
    public MappingProject selectProjectDetailById(Long id) {
        // 调用 mappingProjectMapper 中的 selectProjectDetailById 方法，得到查询结果
        MappingProject mappingProject = null;
        try {
            mappingProject = mappingProjectMapper.selectProjectDetailById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 判断 结果是否为空
        if (null != mappingProject && !"".equals(mappingProject)) {
            // 说明结果不为空，查询成功
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
     * @param [id]
     * @return java.util.List<com.aaa.xj.model.MappingProject>
     */
    public PageInfo<MappingProject> selectProjectForUnitByUserId(Long id, Integer pageNo, Integer pageSize) {
        List<MappingProject> mappingProjects = null;
        PageInfo<MappingProject> pageInfoByType = null;
        try {
            // 设置分页，pageNO 当前页数，pageSize 每页数据个数
            PageHelper.startPage(pageNo, pageSize);
            // 调用 mappingProjectMapper 中的 selectProjectForUnitByUserId 方法，得到查询结果
            mappingProjects = mappingProjectMapper.selectProjectForUnitByUserId(id);
            // 将查询的结果 进行分页
            pageInfoByType = new PageInfo<MappingProject>(mappingProjects);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 判断 结果是否为空
        if (null != pageInfoByType && !"".equals(pageInfoByType)){
            // 说明结果不为空，返回分页结果
            return pageInfoByType;
        }else {
            // 查询失败，返回null
            return null;
        }
    }



}
