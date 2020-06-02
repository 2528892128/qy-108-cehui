package com.aaa.xj.controller;

import com.aaa.xj.base.BaseController;
import com.aaa.xj.base.ResultData;
import com.aaa.xj.model.Audit;
import com.aaa.xj.service.IQYService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * @description 项目汇交-查看汇交项目审核日志
     *  查询 该项目的审核记录
     *      项目id 作为日志表的refId，进行查询该项目的审核日志
     *      type=4，成果汇交审核
     * @date 2020/6/1
     * @param [refId, pageNo, pageSize]
     * @return com.aaa.xj.base.ResultData<com.aaa.xj.model.Audit>
     */
    @GetMapping("/getAuditProjectResult")
    public ResultData<Audit> getAuditProjectResult(Long refId, Integer pageNo, Integer pageSize) {
        // 调用 iqyService 中的 selectAuditProjectResult 方法，得到查询结果
        PageInfo<Audit> auditPageInfo = iqyService.selectAuditProjectResult(refId, pageNo, pageSize);

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
     *  查询 该项目的审核记录，分页
     *      项目id 作为日志表的refId，进行查询该项目的审核日志
     *      type=2，项目等级审核
     * @date 2020/6/1
     * @param [refId, pageNo, pageSize]
     * @return com.aaa.xj.base.ResultData<com.aaa.xj.model.Audit>
     */
    @GetMapping("/getAuditProjectByRefId")
    public ResultData<Audit> getAuditProjectByRefId(Long refId, Integer pageNo, Integer pageSize) {
        // 调用 iqyService 中的 selectAuditByRefId 方法，得到查询结果
        PageInfo<Audit> auditPageInfo = iqyService.selectAuditProjectByRefId(refId, pageNo, pageSize);

        // 判断 结果是否为空
        if (null != auditPageInfo) {
            // 说明结果不为空，查询成功，使用系统消息 自定义返回值
            return getSuccess(auditPageInfo);
        }else {
            // 查询失败，使用系统消息
            return getFalse();
        }
    }


}
