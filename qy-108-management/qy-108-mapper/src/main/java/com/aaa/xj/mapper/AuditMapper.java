package com.aaa.xj.mapper;

import com.aaa.xj.model.Audit;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AuditMapper extends Mapper<Audit> {

    /**
     * @author ligen
     * @description
     *  查询 该项目的审核日志
     *      项目id 作为日志表的refId，进行查询该项目的审核日志
     * @date 2020/5/25
     * @param []
     * @return java.util.List<com.aaa.two.model.Audit>
     */
    List<Audit> selectProjectAuditById(Long refId);
}