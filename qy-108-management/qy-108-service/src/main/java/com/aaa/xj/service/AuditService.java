package com.aaa.xj.service;

import com.aaa.xj.base.BaseService;
import com.aaa.xj.mapper.AuditMapper;
import com.aaa.xj.model.Audit;
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
     * @description
     *  根据 业务编号 ref_id，查询该业务的审核日志
     * @date 2020/5/25
     * @param []
     * @return java.util.List<com.aaa.two.model.Audit>
     */
    public List<Audit> selectAuditByRefId(Long refId) {
        List<Audit> audits = null;

        try {
            // 调用 auditMapper 中的 selectAuditByRefId 方法，返回查询的结果
            audits = auditMapper.selectAuditByRefId(refId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 判断 结果是否为空
        if ("".equals(audits) && audits == null) {
            // 说明结果为空，返回null
            return null;
        }else {
            // 返回结果
            return audits;
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
    public PageInfo<Audit> queryAllAudit(Audit audit, Integer pageNo, Integer pageSize) {
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
