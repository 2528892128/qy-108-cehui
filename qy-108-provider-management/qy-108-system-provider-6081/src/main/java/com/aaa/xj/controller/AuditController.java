package com.aaa.xj.controller;

import com.aaa.xj.model.Audit;
import com.aaa.xj.service.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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


}
