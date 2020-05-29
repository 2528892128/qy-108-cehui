package com.aaa.xj.controller;

import com.aaa.xj.base.BaseController;
import com.aaa.xj.base.ResultData;
import com.aaa.xj.model.MappingProject;
import com.aaa.xj.service.IQYService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    @GetMapping("/getAll")
    public ResultData<MappingProject> getAllMapping() {
        // 调用 iqyService 中的 getAllMappingProject 方法获取数据
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
     *  条件查询，根据项目类型 projectType，查询所有的 已提交的项目信息
     *      项目类型分为：基础测绘，专业测绘
     * @date 2020/5/22
     * @param [projectType]
     * @return java.util.List<com.aaa.xj.model.MappingProject>
     */
    @GetMapping("/getAllByType/{projectType}")
    public ResultData<MappingProject> getAllByType(@PathVariable("projectType") String projectType) {
        List<MappingProject> projectList = null;
        try {
            // 调用 iqyService 中的 selectAllByType 方法获取数据
            projectList = iqyService.selectAllByType(projectType);
        } catch (IllegalArgumentException e) {
            // 非法参数异常
            e.printStackTrace();
        }

        // 判断 结果是否为空
        if (null != projectList && projectList.size() > 0) {
            // 说明拿到数据，返回系统查询成功，使用系统消息、自定义返回值
            return getSuccess(projectList);
        }else {
            // 返回提出查询失败，使用系统消息
            return getFalse();
        }
    }

    /**
     * @author ligen
     * @description
     *  分页查询方法
     *  重写父类 BaseService 中的 queryListByPage 方法
     *      使用自定义的 sql 语句，查询所有的 已提交的项目信息，将查询的结果进行分页
     * @date 2020/5/29
     * @param [mappingProject, pageNo, pageSize]
     * @return com.github.pagehelper.PageInfo
     */
    @PostMapping("/selectALLByPage")
    public PageInfo selectALLByPage(MappingProject mappingProject, Integer pageNo, Integer pageSize){
        return iqyService.selectALLByPage(mappingProject,pageNo,pageSize);
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
