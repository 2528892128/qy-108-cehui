package com.aaa.xj.mapper;

import com.aaa.xj.model.Audit;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AuditMapper extends Mapper<Audit> {

    /**
     * @author ligen
     * @description 项目汇交-查看汇交项目审核日志
     *  查询 该项目的审核记录
     *      项目id 作为日志表的refId，进行查询该项目的审核日志
     *      type=4，成果汇交审核
     * @date 2020/6/1
     * @param [refId]
     * @return java.util.List<com.aaa.xj.model.Audit>
     */
    List<Audit> selectAuditProjectResult(Long refId);

    /**
     * @author ligen
     * @description
     *  查询 该项目的审核记录
     *      项目id 作为日志表的refId，进行查询该项目的审核日志
     *      type=2，项目等级审核
     * @date 2020/6/1
     * @param [refId]
     * @return java.util.List<com.aaa.xj.model.Audit>
     */
    List<Audit> selectAuditProjectByRefId(Long refId);
}