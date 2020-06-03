package com.aaa.xj.service;

import com.aaa.xj.base.BaseService;
import com.aaa.xj.mapper.AuditMapper;
import com.aaa.xj.mapper.MappingProjectMapper;
import com.aaa.xj.model.Audit;
import com.aaa.xj.model.MappingProject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
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
    @Autowired
    private AuditMapper auditMapper;

    /**
     * @author ligen
     * @description 项目汇交
     *  查询所有未提交的汇交成果
     *      汇交成果状态 results_status=3
     * @date 2020/6/2
     * @param [pageNo, pageSize]
     * @return com.github.pagehelper.PageInfo<com.aaa.xj.model.MappingProject>
     */
    public PageInfo<MappingProject> selectAllProjectResult(Integer pageNo, Integer pageSize) {
        PageInfo<MappingProject> projectPageInfo = null;
        try {
            // 设置分页，pageNO 当前页数，pageSize 每页数据个数
            PageHelper.startPage(pageNo, pageSize);
            // 调用 mappingProjectMapper 中的 selectAllProjectResult 方法，得到查询结果
            List<MappingProject> mappingProjectList = mappingProjectMapper.selectAllProjectResult();
            // 将查询的结果 进行分页
            projectPageInfo = new PageInfo<>(mappingProjectList);
        }catch (Exception e) {
            e.printStackTrace();
        }

        // 判断 结果是否为空
        if (null != projectPageInfo && !"".equals(projectPageInfo)){
            // 说明结果不为空，查询成功，返回分页结果
            return projectPageInfo;
        }else {
            // 查询失败，返回null
            return null;
        }
    }


    /**
     * @author ligen
     * @description 项目汇交-根据项目类型查询
     *  条件查询 根据项目类型 projectType，查询所有未提交的汇交成果
     *      项目类型分为：基础测绘，专业测绘
     * @date 2020/6/2
     * @param [projectType, pageNo, pageSize]
     * @return com.github.pagehelper.PageInfo<com.aaa.xj.model.MappingProject>
     */
    public PageInfo<MappingProject> selectAllProjectResultByType(String projectType, Integer pageNo, Integer pageSize) {
        PageInfo<MappingProject> pageInfoByType = null;
        try {
            // 设置分页，pageNO 当前页数，pageSize 每页数据个数
            PageHelper.startPage(pageNo, pageSize);
            // 调用 mappingProjectMapper 中的 selectAllProjectResultByType 方法，得到查询结果
            List<MappingProject> allByProjectType = mappingProjectMapper.selectAllProjectResultByType(projectType);
            // 将查询的结果 进行分页
            pageInfoByType = new PageInfo<MappingProject>(allByProjectType);
        } catch (IllegalArgumentException e) {
            // 非法参数异常
            e.printStackTrace();
        }

        // 判断 结果是否为空
        if (null != pageInfoByType && !"".equals(pageInfoByType)) {
            // 说明结果不为空，查询成功，返回查询的数据
            return pageInfoByType;
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
    public Boolean updateProjectResultStatusById(Long id) {
        int i = 0;
        try {
            // 调用 mappingProjectMapper 中的 updateProjectResultStatusById 方法，返回受影响行数
            i = mappingProjectMapper.updateProjectResultStatusById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 判断受影响行数是否大于0
        if (i>0) {
            // 说明修改成功，返回true
            return true;
        }else {
            // 修改失败，返回false
            return false;
        }
    }

    /**
     * @author ligen
     * @description 系统主页-测绘项目
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
     *  查询，根据单位id 查询该单位下的所有项目信息，进行分页
     * @date 2020/5/31
     * @param [id]
     * @return java.util.List<com.aaa.xj.model.MappingProject>
     */
    public PageInfo<MappingProject> selectProjectForUnitByUserId(Long id, Integer pageNo, Integer pageSize) {
        PageInfo<MappingProject> pageInfoByType = null;
        try {
            // 设置分页，pageNO 当前页数，pageSize 每页数据个数
            PageHelper.startPage(pageNo, pageSize);
            // 调用 mappingProjectMapper 中的 selectProjectForUnitByUserId 方法，得到查询结果
            List<MappingProject>  mappingProjects = mappingProjectMapper.selectProjectForUnitByUserId(id);
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

    /**
     * @author ligen
     * @description 项目审核-项目信息
     *  查询所有的汇交成果为通过的项目信息 results_status=0
     * @date 2020/6/1
     * @param [pageNo, pageSize]
     * @return com.github.pagehelper.PageInfo<com.aaa.xj.model.MappingProject>
     */
    public PageInfo<MappingProject> selectAllProjectAudit(Integer pageNo, Integer pageSize) {
        PageInfo<MappingProject> projectPageInfo = null;
        try {
            // 设置分页，pageNO 当前页数，pageSize 每页数据个数
            PageHelper.startPage(pageNo, pageSize);
            // 调用 mappingProjectMapper 中的 selectAllProjectAudit 方法，得到查询结果
            List<MappingProject> allProjectAudit = mappingProjectMapper.selectAllProjectAudit();
            // 将查询的结果 进行分页
            projectPageInfo = new PageInfo<>(allProjectAudit);
        }catch (Exception e) {
            e.printStackTrace();
        }

        // 判断 结果是否为空
        if (null != projectPageInfo && !"".equals(projectPageInfo)){
            // 说明结果不为空，查询成功，返回分页结果
            return projectPageInfo;
        }else {
            // 查询失败，返回null
            return null;
        }
    }

    /**
     * @author ligen
     * @description 项目审核-项目信息
     *  查询所有审核结果为通过的项目信息
     *      条件查询-模糊查询，
     *      条件：项目名称 projectName
     * @date 2020/6/1
     * @param [projectName, pageNo, pageSize]
     * @return com.github.pagehelper.PageInfo<com.aaa.xj.model.MappingProject>
     */
    public PageInfo<MappingProject> fuzzyProjectAuditByType(String projectName, Integer pageNo, Integer pageSize) {
        PageInfo<MappingProject> projectPageInfo = null;
        try {
            // 设置分页，pageNO 当前页数，pageSize 每页数据个数
            PageHelper.startPage(pageNo, pageSize);
            // 调用 mappingProjectMapper 中的 fuzzyProjectAuditByType 方法，得到查询结果
            List<MappingProject> fuzzyProjectAuditByType = mappingProjectMapper.fuzzyProjectAuditByType(projectName);
            // 将查询的结果 进行分页
            projectPageInfo = new PageInfo<>(fuzzyProjectAuditByType);
        }catch (Exception e) {
            e.printStackTrace();
        }

        // 判断 结果是否为空
        if (null != projectPageInfo && !"".equals(projectPageInfo)){
            // 说明结果不为空，查询成功，返回分页结果
            return projectPageInfo;
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
    public MappingProject selectProjectAuditById(Long id) {
        MappingProject mappingProject = null;
        try {
            // 调用 mappingProjectMapper 中的 selectProjectAuditById 方法，得到查询结果
            mappingProject = mappingProjectMapper.selectProjectAuditById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 判断 结果是否为空
        if (null != mappingProject && !"".equals(mappingProject)){
            // 说明结果不为空，查询成功，返回分页结果
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
    public PageInfo<MappingProject> selectAllProjectResultAudit(Integer pageNo, Integer pageSize) {
        PageInfo<MappingProject> projectPageInfo = null;
        try {
            // 设置分页，pageNO 当前页数，pageSize 每页数据个数
            PageHelper.startPage(pageNo, pageSize);
            // 调用 mappingProjectMapper 中的 selectAllProjectResultAudit 方法，得到查询结果
            List<MappingProject> allProjectAudit = mappingProjectMapper.selectAllProjectResultAudit();
            // 将查询的结果 进行分页
            projectPageInfo = new PageInfo<>(allProjectAudit);
        }catch (Exception e) {
            e.printStackTrace();
        }

        // 判断 结果是否为空
        if (null != projectPageInfo && !"".equals(projectPageInfo)){
            // 说明结果不为空，查询成功，返回分页结果
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
    public PageInfo<MappingProject> fuzzyProjectResultAuditByType(String projectName, Integer pageNo, Integer pageSize) {
        PageInfo<MappingProject> projectPageInfo = null;
        try {
            // 设置分页，pageNO 当前页数，pageSize 每页数据个数
            PageHelper.startPage(pageNo, pageSize);
            // 调用 mappingProjectMapper 中的 fuzzyProjectAuditByType 方法，得到查询结果
            List<MappingProject> fuzzyProjectResultAuditByType = mappingProjectMapper.fuzzyProjectResultAuditByType(projectName);
            // 将查询的结果 进行分页
            projectPageInfo = new PageInfo<>(fuzzyProjectResultAuditByType);
        }catch (Exception e) {
            e.printStackTrace();
        }

        // 判断 结果是否为空
        if (null != projectPageInfo && !"".equals(projectPageInfo)){
            // 说明结果不为空，查询成功，返回分页结果
            return projectPageInfo;
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
    public MappingProject selectProjectResultAuditById(Long id) {
        MappingProject mappingProject = null;
        try {
            // 调用 mappingProjectMapper 中的 selectProjectResultAuditById 方法，得到查询结果
            mappingProject = mappingProjectMapper.selectProjectResultAuditById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 判断 结果是否为空
        if (null != mappingProject && !"".equals(mappingProject)){
            // 说明结果不为空，查询成功，返回分页结果
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
    public PageInfo<MappingProject> selectAllProjectToAudit(Integer pageNo, Integer pageSize) {
        PageInfo<MappingProject> projectPageInfo = null;
        try {
            // 设置分页，pageNO 当前页数，pageSize 每页数据个数
            PageHelper.startPage(pageNo, pageSize);
            // 调用 mappingProjectMapper 中的 selectAllProjectTiAudit 方法，得到查询结果
            List<MappingProject> projectList = mappingProjectMapper.selectAllProjectToAudit();
            // 将查询的结果 进行分页
            projectPageInfo = new PageInfo<>(projectList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 判断 结果是否为空
        if (null != projectPageInfo && !"".equals(projectPageInfo)){
            // 说明结果不为空，查询成功，返回分页结果
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
    public PageInfo<MappingProject> fuzzyProjectToAuditByPName(String projectName, Integer pageNo, Integer pageSize) {
        PageInfo<MappingProject> projectPageInfo = null;
        try {
            // 设置分页，pageNO 当前页数，pageSize 每页数据个数
            PageHelper.startPage(pageNo, pageSize);
            // 调用 mappingProjectMapper 中的 fuzzyProjectToAuditByPName 方法，得到查询结果
            List<MappingProject> projectList = mappingProjectMapper.fuzzyProjectToAuditByPName(projectName);
            // 将查询的结果 进行分页
            projectPageInfo = new PageInfo<>(projectList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 判断 结果是否为空
        if (null != projectPageInfo && !"".equals(projectPageInfo)){
            // 说明结果不为空，查询成功，返回分页结果
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
     *  并 添加审核日志，auditService-insertProjectAudit
     * @date 2020/6/3
     * @param [audit, id, auditStatus]
     * @return java.lang.Boolean
     */
    public Boolean updateProjectAuditStatus(Audit audit, Long id, Integer auditStatus) {
        int i = 0;
        try {
            // 调用 mappingProjectMapper 中的 updateProjectAuditStatus 方法，返回受影响行数
            i = mappingProjectMapper.updateProjectAuditStatus(id, auditStatus);
            if (i > 0){
                // 更改项目审核状态失败
                // 获取当前时间
                Date date = new Date();
                // 设置日期格式
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String nowTime = simpleDateFormat.format(date);

                // 生成审核日志 编号
                Long aMill = System.currentTimeMillis();
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(120501);
                stringBuffer.append(aMill);
                Long myId = Long.valueOf(stringBuffer.toString());

                String pName = "项目登记审核";

                Audit audit1 = audit.setId(myId)
                        .setStatus(auditStatus)
                        .setRefId(id)
                        .setMemo(audit.getMemo())
                        .setUserId(audit.getUserId())
                        .setName(pName)
                        .setType(2)
                        .setAuditTime(nowTime)
                        .setCreateTime(nowTime);

                int addAudit = auditMapper.insert(audit1);
                if (addAudit > 0) {
                    return true;
                }else {
                    return false;
                }
            }else {
                // 更改项目审核状态失败
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @author ligen
     * @description 项目审核-汇交成果审核
     *  查询所有待审核的汇交成果项目信息
     *      汇交成果状态为已提交 results_status=2
     * @date 2020/6/3
     * @param [pageNo, pageSize]
     * @return com.github.pagehelper.PageInfo<com.aaa.xj.model.MappingProject>
     */
    public PageInfo<MappingProject> selectAllProjectResultToAudit(Integer pageNo, Integer pageSize) {
        PageInfo<MappingProject> projectPageInfo = null;
        try {
            // 设置分页，pageNO 当前页数，pageSize 每页数据个数
            PageHelper.startPage(pageNo, pageSize);
            // 调用 mappingProjectMapper 中的 selectAllProjectResultToAudit 方法，得到查询结果
            List<MappingProject> projectList = mappingProjectMapper.selectAllProjectResultToAudit();
            // 将查询的结果 进行分页
            projectPageInfo = new PageInfo<>(projectList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 判断 结果是否为空
        if (null != projectPageInfo && !"".equals(projectPageInfo)){
            // 说明结果不为空，查询成功，返回分页结果
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
     * @date 2020/6/3
     * @param [projectName, pageNo, pageSize]
     * @return com.github.pagehelper.PageInfo<com.aaa.xj.model.MappingProject>
     */
    public PageInfo<MappingProject> fuzzyProjectResultToAuditByPName(String projectName, Integer pageNo, Integer pageSize) {
        PageInfo<MappingProject> projectPageInfo = null;
        try {
            // 设置分页，pageNO 当前页数，pageSize 每页数据个数
            PageHelper.startPage(pageNo, pageSize);
            // 调用 mappingProjectMapper 中的 fuzzyProjectResultToAuditByPName 方法，得到查询结果
            List<MappingProject> projectList = mappingProjectMapper.fuzzyProjectResultToAuditByPName(projectName);
            // 将查询的结果 进行分页
            projectPageInfo = new PageInfo<>(projectList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 判断 结果是否为空
        if (null != projectPageInfo && !"".equals(projectPageInfo)){
            // 说明结果不为空，查询成功，返回分页结果
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
     *      操作成功，新增审核日志
     * @date 2020/6/3
     * @param [audit, id, resultsStatus]
     * @return java.lang.Boolean
     */
    public Boolean updateProjectResultStatus(Audit audit, Long id, Integer resultsStatus) {
        int i = 0;
        try {
            // 调用 mappingProjectMapper 中的 updateProjectResultStatus 方法，返回受影响行数
            i = mappingProjectMapper.updateProjectResultStatus(id, resultsStatus);
            if (i > 0){
                // 更改项目审核状态成功
                // 获取当前时间
                Date date = new Date();
                // 设置日期格式
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String nowTime = simpleDateFormat.format(date);

                // 生成审核日志 编号
                Long aMill = System.currentTimeMillis();
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(123827);
                stringBuffer.append(aMill);
                Long myId = Long.valueOf(stringBuffer.toString());

                String pName = "项目成果汇交";

                String auditTime = null;
                String submitTime = null;
                if (null != resultsStatus && !"".equals(resultsStatus)) {
                    auditTime = nowTime;
                    submitTime = null;
                }else {
                    auditTime = null;
                    submitTime = nowTime;
                }

                Audit audit1 = audit.setId(myId)
                        .setStatus(resultsStatus)
                        .setRefId(id)
                        .setMemo(audit.getMemo())
                        .setUserId(audit.getUserId())
                        .setName(pName)
                        .setType(4)
                        .setAuditTime(auditTime)
                        .setSubmitTime(submitTime)
                        .setCreateTime(nowTime);

                int addAudit = auditMapper.insert(audit1);
                if (addAudit > 0) {
                    // 新增日志成功
                    return true;
                }else {
                    // 新增日志失败
                    return false;
                }
            }else {
                // 更改项目审核状态失败
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 审核操作失败
        return false;
    }






}
