package com.aaa.xj.controller;

import com.aaa.xj.base.BaseController;
import com.aaa.xj.base.ResultData;
import com.aaa.xj.model.ResultCommit;
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
 *  测绘成果
 * @create 2020-05-27 20:24
 */
@RestController
@Api(value = "测绘成果", tags = "测绘成果接口")
public class ResultCommitController extends BaseController {
    @Autowired
    private IQYService qyService;

    /**
     * @author ligen
     * @description
     *  模糊查询-动态sql 查询测绘成果名称
     * @date 2020/5/27
     * @param [name, projectType, resultDate]
     * @return com.aaa.xj.base.ResultData<com.aaa.xj.base.ResultData>
     */
    @GetMapping("/fuzzyResultName")
    public ResultData<ResultData> fuzzyResultName(String name, String projectType, String resultDate) {
        // 调用 qyService 中的 fuzzyResultName 方法获取数据
        List<ResultCommit> resultCommits = qyService.fuzzyResultName(name, projectType, resultDate);

        // 判断 结果是否为空
        if (resultCommits != null) {
            // 说明拿到数据，返回系统查询成功，使用系统消息、自定义返回值
            return getSuccess(resultCommits);
        }else {
            // 查询失败，使用系统消息
            return getFalse();
        }
    }

    /**
     * @author ligen
     * @description
     *  查询测绘成果详情，根据成果主键id
     * @date 2020/5/27
     * @param [id]
     * @return com.aaa.xj.base.ResultData<com.aaa.xj.model.ResultCommit>
     */
    @GetMapping("/selectResultDetails")
    public ResultData<ResultCommit> selectResultDetails(Long id) {
        // 调用 qyService 中的 selectResultDetails 方法获取数据
        ResultCommit resultCommit = qyService.selectResultDetails(id);

        // 判断 结果是否为空
        if (resultCommit != null) {
            // 说明拿到数据，返回系统查询成功，使用系统消息、自定义返回值
            return getSuccess(resultCommit);
        }else {
            // 查询失败，使用系统消息
            return getFalse();
        }
    }

}
