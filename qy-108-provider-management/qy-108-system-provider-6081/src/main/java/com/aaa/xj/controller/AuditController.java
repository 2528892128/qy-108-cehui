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
     * @description 项目汇交-查看汇交项目审核日志
     *  查询 该项目的审核记录
     *      项目id 作为日志表的refId，进行查询该项目的审核日志
     *      type=4，成果汇交审核
     * @date 2020/6/1
     * @param [refId, pageNo, pageSize]
     * @return com.github.pagehelper.PageInfo<com.aaa.xj.model.Audit>
     */
    @GetMapping("/selectAuditProjectResult")
    public PageInfo<Audit> selectAuditProjectResult(@RequestParam("refId") Long refId,
                                                    @RequestParam("pageNo") Integer pageNo,
                                                    @RequestParam("pageSize") Integer pageSize) {
        // 调用 auditService 中的 selectAuditProjectByRefId 方法，得到查询结果
        PageInfo<Audit> auditPageInfo = auditService.selectAuditProjectResult(refId, pageNo, pageSize);

        // 判断 结果是否为空
        if (null != auditPageInfo) {
            // 说明结果不为空，查询成功，返回结果数据
            return auditPageInfo;
        }else {
            // 查询失败，返回null
            return null;
        }
    }

    /**
     * @author ligen
     * @description
     *  查询 该项目的审核记录，分页
     *      项目id 作为日志表的refId，进行查询该项目的审核日志
     *      type=2，项目等级审核
     * @date 2020/6/1
     * @param [refId, pageNo, pageSize]
     * @return com.github.pagehelper.PageInfo<com.aaa.xj.model.Audit>
     */
    @GetMapping("/selectAuditProjectByRefId")
    public PageInfo<Audit> selectAuditProjectByRefId(@RequestParam("refId") Long refId,
                                                     @RequestParam("pageNo") Integer pageNo,
                                                     @RequestParam("pageSize") Integer pageSize) {
        // 调用 auditService 中的 selectAuditProjectByRefId 方法，得到查询结果
        PageInfo<Audit> auditPageInfo = auditService.selectAuditProjectByRefId(refId, pageNo, pageSize);

        // 判断 结果是否为空
        if (null != auditPageInfo) {
            // 说明结果不为空，查询成功，返回结果数据
            return auditPageInfo;
        }else {
            // 查询失败，返回null
            return null;
        }
    }

}
