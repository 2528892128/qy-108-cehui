package com.aaa.xj.controller;

import com.aaa.xj.model.ResultCommit;
import com.aaa.xj.service.ResultCommitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ligen
 * @program qy-108-cehui
 * @description
 * @create 2020-05-27 19:58
 */
@RestController
public class ResultCommitController {
    @Autowired
    private ResultCommitService resultCommitService;

    /**
     * @author ligen
     * @description
     *  模糊查询-动态sql 查询测绘成果名称
     * @date 2020/5/27
     * @param [name, resultDate, projectType]
     * @return java.util.List<com.aaa.xj.model.ResultCommit>
     */
    @GetMapping("/fuzzyResultName")
    public List<ResultCommit> fuzzyResultName(@RequestParam("name") String name,
                                              @RequestParam("projectType") String projectType,
                                              @RequestParam("resultDate") String resultDate) {
        // 调用 resultCommitService 中的 fuzzyResultName 方法，得到查询结果
        List<ResultCommit> fuzzyResultName = resultCommitService.fuzzyResultName(name, projectType, resultDate);

        // 判断 结果是否为空
        if (fuzzyResultName != null){
            // 说明结果不为空，查询成功，返回结果
            return fuzzyResultName;
        }else {
            // 查询失败，返回null
            return null;
        }
    }

    /**
     * @author ligen
     * @description 系统主页-测绘成果
     *  查询测绘成果基本信息
     * @date 2020/5/31
     * @param [id]
     * @return com.aaa.xj.model.ResultCommit
     */
    @GetMapping("/selectResultInfoById")
    public ResultCommit selectResultInfoById(@RequestParam("id") Long id) {
        // 调用 resultCommitService 中的 fuzzyResultName 方法，得到查询结果
        ResultCommit resultCommit = resultCommitService.selectResultInfoById(id);

        // 判断 结果是否为空
        if (resultCommit != null){
            // 说明结果不为空，查询成功，返回结果
            return resultCommit;
        }else {
            // 查询失败，返回null
            return null;
        }
    }

    /**
     * @author ligen
     * @description 系统主页-测绘成果-查看详情
     *  查询测绘成果详情，根据成果主键id
     * @date 2020/5/29
     * @param [id]
     * @return com.aaa.xj.model.ResultCommit
     */
    @GetMapping("/selectResultDetails")
    public ResultCommit selectResultDetails(@RequestParam("id") Long id) {
        // 调用 resultCommitService 中的 fuzzyResultName 方法，得到查询结果
        ResultCommit details = resultCommitService.selectResultDetails(id);

        // 判断 结果是否为空
        if (details != null) {
            // 说明结果不为空，查询成功，返回结果
            return details;
        }else {
            // 查询失败，返回null
            return null;
        }
    }


}
