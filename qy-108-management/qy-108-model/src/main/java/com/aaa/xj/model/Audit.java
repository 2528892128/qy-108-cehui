package com.aaa.xj.model;

import com.aaa.xj.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "t_audit")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Audit implements Serializable {

    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 审核项
     */
    private String name;

    /**
     * 审核状态 0:通过 1:拒绝
     */
    private Integer status;

    /**
     * 备注
     */
    private String memo;

    /**
     * 提交时间
     */
    @Column(name = "submit_time")
    private String submitTime;

    /**
     * 审核时间
     */
    @Column(name = "audit_time")
    private String auditTime;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private String createTime;

    /**
     * 审核类别 1:单位信息审核 2:项目登记审核 3:项目上交审核 4:成果汇交审核
     */
    private Integer type;

    /**
     * 单位用户编号
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 关联业务编号
     */
    @Column(name = "ref_id")
    private Long refId;

}