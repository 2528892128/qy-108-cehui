package com.aaa.xj.controller;

import com.aaa.xj.model.Audit;
import com.aaa.xj.service.AuditService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ligen
 * @program qy-108-cehui
 * @description
 * @create 2020-05-25 19:04
 */
@RestController
public class AuditController {
    @Autowired
    private AuditService auditService;

    /**
     * @author ligen
     * @description
     *  根据 业务编号 ref_id，查询该业务的审核日志
     * @date 2020/5/25
     * @param [refId]
     * @return java.util.List<com.aaa.xj.model.Audit>
     */
    @GetMapping("/selectAuditByRefId")
    public List<Audit> selectAuditByRefId(@RequestParam("refId") Long refId) {
        // 调用 auditService 中的 selectAuditByRefId 方法，返回查询的结果
        List<Audit> audits = auditService.selectAuditByRefId(refId);

        // 判断 结果是否为空
        if (audits == null) {
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
     * @date 2020/5/29
     * @param [audit, pageNo, pageSize]
     * @return com.github.pagehelper.PageInfo<com.aaa.xj.model.Audit>
     */
    @PostMapping("/queryAllAudit")
    public PageInfo<Audit> queryAllAudit(@RequestBody Audit audit,
                                         @RequestParam("pageNo") Integer pageNo,
                                         @RequestParam("pageSize") Integer pageSize) {
        // 调用 auditService 中的 queryAllAudit 方法，返回查询的结果
        PageInfo<Audit> auditPageInfo = auditService.queryAllAudit(audit, pageNo, pageSize);

        // 判断 结果是否为空
        if (auditPageInfo == null) {
            // 说明结果为空，返回null
            return null;
        }else {
            // 返回结果
            return auditPageInfo;
        }
    }


}
