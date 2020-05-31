package com.aaa.xj.controller;

import com.aaa.xj.model.Audit;
import com.aaa.xj.service.AuditService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * @description 项目汇交-查看项目的审核日志
     *  查询 该项目的审核日志
     *      项目id 作为日志表的refId，进行查询该项目的审核日志
     * @date 2020/5/25
     * @param [refId]
     * @return java.util.List<com.aaa.xj.model.Audit>
     */
    @GetMapping("/selectProjectAuditById")
    public PageInfo<Audit> selectProjectAuditById(@RequestParam("refId") Long refId,
                                              @RequestParam("pageNo") Integer pageNo,
                                              @RequestParam("pageSize") Integer pageSize) {
        // 调用 auditService 中的 selectProjectAuditById 方法，返回查询的结果
        PageInfo<Audit> auditPageInfo = auditService.selectProjectAuditById(refId, pageNo, pageSize);

        // 判断 结果是否为空
        if (null != auditPageInfo) {
            // 说明结果不为空，查询成功，返回结果
            return auditPageInfo;
        }else {
            // 查询失败，返回null
            return null;
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
    @PostMapping("/selectAllAudit")
    public PageInfo<Audit> selectAllAudit(@RequestBody Audit audit,
                                          @RequestParam("pageNo") Integer pageNo,
                                          @RequestParam("pageSize") Integer pageSize) {
        // 调用 auditService 中的 selectAllAudit 方法，返回查询的结果
        PageInfo<Audit> auditPageInfo = auditService.selectAllAudit(audit, pageNo, pageSize);

        // 判断 结果是否为空
        if (null != auditPageInfo) {
            // 说明结果不为空，查询成功，返回结果
            return auditPageInfo;
        }else {
            // 查询失败，返回null
            return null;
        }
    }


}
