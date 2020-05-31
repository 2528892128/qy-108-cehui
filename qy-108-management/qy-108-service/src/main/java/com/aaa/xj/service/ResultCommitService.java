package com.aaa.xj.service;

import com.aaa.xj.base.BaseService;
import com.aaa.xj.mapper.ResultCommitMapper;
import com.aaa.xj.model.ResultCommit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ligen
 * @program qy-108-cehui
 * @description
 * @create 2020-05-27 19:49
 */
@Service
public class ResultCommitService extends BaseService<ResultCommit> {
    @Autowired
    private ResultCommitMapper resultCommitMapper;

    /**
     * @author ligen
     * @description
     *  模糊查询 查询测绘成果名称
     * @date 2020/5/27
     * @param [name, resultDate, projectType]
     * @return java.util.List<com.aaa.xj.model.ResultCommit>
     */
    public List<ResultCommit> fuzzyResultName(String name, String projectType, String resultDate) {
        List<ResultCommit> fuzzyResultName = null;
        try {
            // 调用 resultCommitMapper 中的 fuzzyResultName 方法，得到查询结果
            fuzzyResultName = resultCommitMapper.fuzzyResultName(name, projectType, resultDate);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 判断 结果是否为空
        if (fuzzyResultName != null && fuzzyResultName.size() > 0){
            // 说明结果不为空，查询成果，返回结果
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
    public ResultCommit selectResultInfoById(Long id) {
        ResultCommit resultCommit = null;
        try {
            // 调用 resultCommitMapper 中的 selectResultInfoById 方法，得到查询结果
            resultCommit = resultCommitMapper.selectResultInfoById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 判断 结果是否为空
        if (null != resultCommit && !"".equals(resultCommit)){
            // 说明结果不为空，查询成果，返回结果
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
    public ResultCommit selectResultDetails(Long id) {
        ResultCommit resultCommit = null;
        try {
            // 调用 resultCommitMapper 中的 fuzzyResultName 方法，得到查询结果
            resultCommit = resultCommitMapper.selectResultDetails(id);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }

        // 判断 结果是否为空
        if (resultCommit != null && !"".equals(resultCommit)) {
            // 说明结果不为空，查询成功，返回结果
            return resultCommit;
        }else {
            // 查询失败，返回null
            return null;
        }
    }




}
