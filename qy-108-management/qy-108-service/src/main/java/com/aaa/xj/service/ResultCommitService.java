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
        // 调用 resultCommitMapper 中的 fuzzyResultName 方法，得到结果
        List<ResultCommit> fuzzyResultName = resultCommitMapper.fuzzyResultName(name, projectType, resultDate);

        // 判断 结果是否为空
        if (fuzzyResultName != null && fuzzyResultName.size() > 0){
            // 说明结果不为空，返回结果
            return fuzzyResultName;
        }else {
            // 返回null
            return null;
        }
    }

    /**
     * @author ligen
     * @description
     *  查询测绘成果详情，根据成果主键id
     * @date 2020/5/29
     * @param [id]
     * @return com.aaa.xj.model.ResultCommit
     */
    public ResultCommit selectResultDetails(Long id) {

        ResultCommit resultCommit = null;
        try {
            // 调用 resultCommitMapper 中的 fuzzyResultName 方法，得到结果
            resultCommit = resultCommitMapper.selectResultDetails(id);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }

        // 判断 结果是否为空
        if (resultCommit != null && !"".equals(resultCommit)) {
            // 说明结果不为空，返回结果
            return resultCommit;
        }else {
            // 返回null
            return null;
        }
    }




}
