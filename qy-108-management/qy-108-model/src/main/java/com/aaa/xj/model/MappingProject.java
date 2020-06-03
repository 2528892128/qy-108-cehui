package com.aaa.xj.model;

import com.aaa.xj.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "t_mapping_project")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class MappingProject extends BaseModel {

    /**
     * 项目类型
     */
    @Column(name = "project_type")
    private String projectType;

    /**
     * 项目名称
     */
    @Column(name = "project_name")
    private String projectName;

    /**
     * 项目负责人
     */
    @Column(name = "project_leader")
    private String projectLeader;

    /**
     * 开工日期
     */
    @Column(name = "start_date")
    private String startDate;

    /**
     * 完工日期
     */
    @Column(name = "end_date")
    private String endDate;

    /**
     * 验收机构
     */
    @Column(name = "acceptance_department")
    private String acceptanceDepartment;

    /**
     * 项目金额（万元）
     */
    @Column(name = "project_amount")
    private Double projectAmount;

    /**
     * 项目成果汇交状态 0:通过 1:未通过 2:已提交 3:未提交
     */
    @Column(name = "results_status")
    private Integer resultsStatus;

    /**
     * 完成工期
     */
    @Column(name = "complete_time")
    private String completeTime;

    /**
     * 验收报告文号
     */
    @Column(name = "acceptance_report")
    private String acceptanceReport;

    /**
     * 任务来源
     */
    @Column(name = "task_source")
    private String taskSource;

    /**
     * 项目面积（km²）
     */
    @Column(name = "project_area")
    private Double projectArea;

    /**
     * 比例尺（开本，册）
     */
    private String scale;

    /**
     * 图幅数量（线路里程）
     */
    @Column(name = "sheet_number")
    private String sheetNumber;

    /**
     * 颁奖单位
     */
    @Column(name = "awards_department")
    private String awardsDepartment;

    /**
     * 获奖类型等级
     */
    @Column(name = "prize_level")
    private String prizeLevel;

    /**
     * 项目质量认可
     */
    @Column(name = "project_quality_approval")
    private String projectQualityApproval;

    /**
     * 获奖时间
     */
    @Column(name = "winning_time")
    private String winningTime;

    /**
     * 验收时间
     */
    @Column(name = "acceptance_time")
    private String acceptanceTime;

    /**
     * 基本内容
     */
    @Column(name = "basic_content")
    private String basicContent;

    /**
     * 履约情况
     */
    @Column(name = "credit_status")
    private String creditStatus;

    /**
     * 向测绘主管部门汇交成果情况
     */
    @Column(name = "submit_status")
    private String submitStatus;

    /**
     * 单位用户编号
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 进度（百分比）
     */
    private Integer schedule;

    /**
     * 备注
     */
    private String memo;

    /**
     * 项目进行状态 2:未完成 3:已完成
     */
    private Integer status;

    /**
     * 项目审核结果 0:通过 1:未通过 2:已提交 3:未提交
     */
    @Column(name = "audit_status")
    private Integer auditStatus;

    /**
     * 中央子午线
     */
    private String meridian;

    /**
     * 坐标系
     */
    @Column(name = "coordinate_system")
    private String coordinateSystem;

    /**
     * 管理级别
     */
    @Column(name = "management_level")
    private String managementLevel;

    /**
     * 资金来源
     */
    @Column(name = "funding_source")
    private String fundingSource;

    /**
     * 
委托单位
     */
    @Column(name = "entrust_unit")
    private String entrustUnit;

    /**
     * 
承建单位
     */
    @Column(name = "accept_unit")
    private String acceptUnit;

    /**
     * 手机号码
     */
    @Column(name = "mobile_phone")
    private String mobilePhone;

    /**
     * 固定
电话
     */
    private String phone;

    /**
     * 项目所在地
     */
    private String address;

    /**
     * 项目中心点
     */
    @Column(name = "center_point")
    private String centerPoint;

    /**
     * 同步状态 0：已同步 1：未同步
     */
    @Column(name = "synchronization_status")
    private Integer synchronizationStatus;

    /**
     * 合同上传时间
     */
    @Column(name = "contract_time")
    private String contractTime;

    /**
     * 坐标
     */
    private String coordinate;

    public Mapping_unit mappingUnit;

    private ResultCommit resultCommit;


}