package com.aaa.xj.controller;

import com.aaa.xj.base.BaseController;
import com.aaa.xj.base.ResultData;
import com.aaa.xj.model.Audit;
import com.aaa.xj.service.IQYService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ligen
 * @program qy-108-cehui
 * @description
 *  审核日志
 * @create 2020-05-25 20:20
 */
@RestController
@Api(value = "审核日志", tags = "审核日志接口")
public class AuditController extends BaseController {
    @Autowired
    private IQYService iqyService;

    /**
     * @author ligen
     * @description 项目汇交-查看项目的审核日志
     *  查询 该项目的审核日志
     *      项目id 作为日志表的refId，进行查询该项目的审核日志
     * @date 2020/5/31
     * @param [refId]
     * @return com.aaa.xj.base.ResultData<com.aaa.xj.model.Audit>
     */
    @GetMapping("/getProjectAuditById")
    public ResultData<Audit> getProjectAuditById(Long refId, Integer pageNo, Integer pageSize) {
        // 调用 iqyService 中的 selectAuditByRefId 方法，得到结果
        PageInfo<Audit> auditPageInfo = iqyService.selectProjectAuditById(refId, pageNo, pageSize);

        // 判断 结果是否为空
        if (null != auditPageInfo) {
            // 说明结果不为空，查询成功，使用系统消息 自定义返回值
            return getSuccess(auditPageInfo);
        }else {
            // 查询失败，使用系统消息
            return getFalse();
        }
    }

    /**
     * @author ligen
     * @description
     *  查询分页--查询所有的审核日志
     * @date 2020/5/29
     * @param [audit, pageNo, pageSize]
     * @return com.aaa.xj.base.ResultData<com.aaa.xj.model.Audit>
     */
    @PostMapping("/getAllAuditPageInfo")
    public ResultData<Audit> getAllAuditPageInfo(Audit audit, Integer pageNo, Integer pageSize) {
        // 调用 iqyService 中的 queryAllAudit 方法，得到结果
        PageInfo<Audit> auditPageInfo = iqyService.selectAllAudit(audit, pageNo, pageSize);

        // 判断 结果是否为空
        if (auditPageInfo != null) {
            // 说明结果不为空，查询成功，使用系统消息、自定义返回值
            return getSuccess(auditPageInfo);
        }else {
            // 查询失败，使用系统消息
            return getFalse();
        }
    }



}
