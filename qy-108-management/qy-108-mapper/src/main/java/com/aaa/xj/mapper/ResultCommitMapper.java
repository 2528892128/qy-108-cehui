package com.aaa.xj.mapper;

import com.aaa.xj.model.ResultCommit;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ResultCommitMapper extends Mapper<ResultCommit> {

    /**
     * @author ligen
     * @description
     *  模糊查询-动态sql 查询测绘成果名称
     * @date 2020/5/27
     * @param [resultCommit]
     * @return java.util.List<com.aaa.xj.model.ResultCommit>
     */
    List<ResultCommit> fuzzyResultName(@Param("name") String name,
                                       @Param("projectType") String projectType,
                                       @Param("resultDate") String resultDate);

    /**
     * @author ligen
     * @description
     *  查询测绘成果详情，根据成果主键id
     *      一对一
     * @date 2020/5/29
     * @param [id]
     * @return com.aaa.xj.model.ResultCommit
     */
    ResultCommit selectResultDetails(@Param("id") Long id);
}