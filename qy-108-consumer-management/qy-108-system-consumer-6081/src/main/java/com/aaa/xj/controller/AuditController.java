package com.aaa.xj.controller;

import com.aaa.xj.base.BaseController;
import com.aaa.xj.base.ResultData;
import com.aaa.xj.model.Audit;
import com.aaa.xj.service.IQYService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
     * @description
     *  根据 业务编号 ref_id，查询该业务的审核日志
     * @date 2020/5/25
     * @param [refId]
     * @return com.aaa.xj.base.ResultData<com.aaa.xj.model.Audit>
     */
    @GetMapping("/getAuditByRefId")
    public ResultData<Audit> getAuditByRefId(Long refId) {
        // 调用 iqyService 中的 selectAllByType 方法，得到结果
        List<Audit> audits = iqyService.selectAuditByRefId(refId);

        // 判断 结果是否为空
        if (audits == null) {
            // 说明结果为空，返回系统查询失败，使用系统消息
            return getFalse();
        }else {
            // 查询成功，使用系统消息、自定义返回值
            return getSuccess(audits);
        }
    }




}
