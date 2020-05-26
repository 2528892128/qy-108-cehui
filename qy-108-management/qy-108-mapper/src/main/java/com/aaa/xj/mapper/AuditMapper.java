package com.aaa.xj.mapper;

import com.aaa.xj.model.Audit;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AuditMapper extends Mapper<Audit> {

    /**
     * @author ligen
     * @description
     *  根据 业务编号 ref_id，查询该业务的审核日志
     * @date 2020/5/25
     * @param []
     * @return java.util.List<com.aaa.two.model.Audit>
     */
    List<Audit> selectAuditByRefId(Long refId);
}