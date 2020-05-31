package com.aaa.xj.service;

import com.aaa.xj.base.BaseService;
import com.aaa.xj.mapper.AuditMapper;
import com.aaa.xj.model.Audit;
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
 *  审核日志
 * @create 2020-05-25 18:37
 */
@Service
public class AuditService extends BaseService<Audit> {
    @Autowired
    private AuditMapper auditMapper;

    /**
     * @author ligen
     * @description 项目汇交-查看项目的审核日志
     *  查询 该项目的审核日志
     *      项目id 作为日志表的refId，进行查询该项目的审核日志
     * @date 2020/5/25
     * @param []
     * @return java.util.List<com.aaa.two.model.Audit>
     */
    public PageInfo<Audit> selectProjectAuditById(Long refId, Integer pageNo, Integer pageSize) {
        PageInfo<Audit> auditPageInfo = null;
        try {
            // 设置分页，pageNO 当前页数，pageSize 每页数据个数
            PageHelper.startPage(pageNo, pageSize);
            // 调用 auditMapper 中的 selectProjectAuditById 方法，返回查询的结果
            List<Audit> audits = auditMapper.selectProjectAuditById(refId);
            // 将查询的结果 进行分页
            auditPageInfo = new PageInfo<Audit>(audits);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 判断 结果是否为空
        if (null != auditPageInfo && !"".equals(auditPageInfo)) {
            // 说明结果不为空，查询成功，返回结果
            return auditPageInfo;
        }else {
            // 查询失败，返回结果
            return null;
        }
    }

    /**
     * @author ligen
     * @description
     *  查询分页--查询所有的审核日志
     *      调用BaseService 封装的 queryListByPage 查询所有
     * @date 2020/5/29
     * @param [audit, pageNo, pageSize]
     * @return com.github.pagehelper.PageInfo<com.aaa.xj.model.Audit>
     */
    public PageInfo<Audit> selectAllAudit(Audit audit, Integer pageNo, Integer pageSize) {
        PageInfo<Audit> auditPageInfo = null;
        try {
            // 调用 BaseService 中的 queryListByPage 查询分页方法，得到结果
            auditPageInfo = queryListByPage(audit, pageNo, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 判断 结果是否为空
        if (auditPageInfo != null && !"".equals(auditPageInfo)) {
            // 说明结果不为空，查询成功，返回结果
            return auditPageInfo;
        }else {
            // 返回结果
            return null;
        }


    }










}
