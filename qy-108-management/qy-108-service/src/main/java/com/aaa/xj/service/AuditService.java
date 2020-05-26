package com.aaa.xj.service;

import com.aaa.xj.mapper.AuditMapper;
import com.aaa.xj.model.Audit;
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
public class AuditService {
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
            System.out.println("refId==="+ refId);
            System.out.println("audits==="+audits);
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











}
