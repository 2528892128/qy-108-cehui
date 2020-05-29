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
 *  项目汇交
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
    public List<MappingProject> selectAllMappingProject() {
        List<MappingProject> projectList = null;
        try {
            // 调用 mappingProjectMapper 中的 selectAllMappingProject 方法，返回查询的结果
            projectList = mappingProjectMapper.selectAllMappingProject();
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
     *  条件查询 根据项目类型 projectType，
     *  查询所有的 已提交的项目信息
     *      项目类型分为：基础测绘，专业测绘
     * @date 2020/5/22
     * @param [projectType]
     * @return java.util.List<com.aaa.xj.model.MappingProject>
     */
    public List<MappingProject> selectAllByProjectType(String projectType) {
        List<MappingProject> allByProjectType = null;

        try {
            // 调用 mappingProjectMapper 中的 selectAllByProjectType 方法获取数据
            allByProjectType = mappingProjectMapper.selectAllByProjectType(projectType);
        } catch (Exception e) {
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
    public PageInfo selectALLByPage(MappingProject mappingProject, Integer pageNo, Integer pageSize) {
        PageInfo<MappingProject> projectPageInfo = null;
        try {
            // 调用重写的分页查询方法，得到分页结果
            projectPageInfo = queryListByPage(mappingProject, pageNo, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (null == projectPageInfo && "".equals(projectPageInfo)) {
            // 说明查询的结果是空，没有数据，返回null
            return null;
        }else {
            // 返回结果
            return projectPageInfo;
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
            select = mappingProjectMapper.selectAllMappingProject();
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
     *  模糊查询-动态sql 查询测绘项目名称
     * @date 2020/5/27
     * @param [mappingProject]
     * @return java.util.List<com.aaa.xj.model.MappingProject>
     */
    public List<MappingProject> fuzzyProjectName(String projectName, String projectType, String startDate) {
        // 调用 mappingProjectMapper 中的 fuzzyProjectName 方法，得到结果
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



}
