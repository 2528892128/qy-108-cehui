package com.aaa.xj.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "t_mapping_unit")
public class Mapping_unit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private String createTime;

    /**
     * 修改时间
     */
    @Column(name = "modify_time")
    private String modifyTime;

    /**
     * 备注
     */
    private String memo;

    /**
     * 单位名称
     */
    @Column(name = "unit_name")
    private String unitName;

    /**
     * 注册地址
     */
    @Column(name = "register_address")
    private String registerAddress;

    /**
     * 注册时间
     */
    @Column(name = "register_time")
    private String registerTime;

    /**
     * 办公地址
     */
    private String oa;

    /**
     * 法人
     */
    private String corporation;

    /**
     * 联系人
     */
    private String linkman;

    /**
     * 所属行政区
     */
    @Column(name = "owned_district")
    private String ownedDistrict;

    /**
     * 注册资金(万元)
     */
    @Column(name = "register_fund")
    private Double registerFund;

    /**
     * 统一社会信用代码
     */
    @Column(name = "social_credit_code")
    private String socialCreditCode;

    /**
     * 资质等级
     */
    @Column(name = "qualification_level")
    private String qualificationLevel;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 联系方式
     */
    @Column(name = "contact_way")
    private String contactWay;

    /**
     * 单位曾用名
     */
    @Column(name = "used_name")
    private String usedName;

    /**
     * 注册地址经度
     */
    @Column(name = "register_address_lon")
    private String registerAddressLon;

    /**
     * 注册单位纬度
     */
    @Column(name = "register_address_lat")
    private String registerAddressLat;

    /**
     * 注册地址邮编
     */
    @Column(name = "register_address_postcode")
    private String registerAddressPostcode;

    /**
     * 办公地址邮编
     */
    @Column(name = "oa_postcode")
    private String oaPostcode;

    /**
     * 办公场所面积（平方米）
     */
    @Column(name = "oa_area")
    private Double oaArea;

    /**
     * 单位成立时间
     */
    @Column(name = "establish_time")
    private String establishTime;

    /**
     * 手机号码
     */
    @Column(name = "mobile_phone")
    private String mobilePhone;

    /**
     * 传真号码
     */
    @Column(name = "fax_num")
    private String faxNum;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 测绘人员总数
     */
    @Column(name = "surveying_num")
    private Integer surveyingNum;

    /**
     * 职工总数
     */
    @Column(name = "staff_num")
    private Integer staffNum;

    /**
     * 单位性质
     */
    @Column(name = "unit_nature")
    private String unitNature;

    /**
     * 所属行业
     */
    @Column(name = "belong_industry")
    private String belongIndustry;

    /**
     * 证书编号
     */
    @Column(name = "certificate_code")
    private String certificateCode;

    /**
     * 发证日期
     */
    @Column(name = "certificate_send_date")
    private String certificateSendDate;

    /**
     * 公司类型
     */
    @Column(name = "company_type")
    private String companyType;

    /**
     * 股东及控股情况
     */
    @Column(name = "stock_details")
    private String stockDetails;

    /**
     * 合资形式
     */
    @Column(name = "joint_venture")
    private String jointVenture;

    /**
     * 合资企业中方控股（%）
     */
    @Column(name = "joint_ch_p")
    private Integer jointChP;

    /**
     * 合资企业批注文号
     */
    @Column(name = "joint_ratify_code")
    private String jointRatifyCode;

    /**
     * 主管部门
     */
    @Column(name = "competent_depart")
    private String competentDepart;

    /**
     * 首次取得资质等级
     */
    @Column(name = "first_qualification_level")
    private String firstQualificationLevel;

    /**
     * 首次取得资质发证日期
     */
    @Column(name = "first_qualification_date")
    private String firstQualificationDate;

    /**
     * 申请资质前单位进行过程
     */
    @Column(name = "qualification_process")
    private String qualificationProcess;

    /**
     * 组织机构代码
     */
    @Column(name = "organization_code")
    private String organizationCode;

    /**
     * 单位直属类型
     */
    @Column(name = "unit_type")
    private String unitType;

    /**
     * 单位用户编号
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 单位审核状态 0:通过 1:未通过 2:已提交 3:未提交
     */
    @Column(name = "audit_status")
    private Integer auditStatus;

    /**
     * 
单位代码
     */
    @Column(name = "unit_code")
    private String unitCode;

    /**
     * 1白名单2
黑名单3待定
     */
    @Column(name = "unit_status")
    private Integer unitStatus;

    /**
     * 同步状态 0：已同步 1：未同步
     */
    @Column(name = "synchronization_status")
    private Integer synchronizationStatus;

    /**
     * 单位分值 默认为100分 小于60分
进黑名单,大于等于100分进白名单
     */
    private Integer score;

    /**
     * 备案业务分类: 不动产测绘和联合测绘
     */
    @Column(name = "filing_business")
    private String filingBusiness;

    /**
     * 业务范围
     */
    @Column(name = "business_scope")
    private String businessScope;

    /**
     * 单位简介
     */
    @Column(name = "unit_intro")
    private String unitIntro;

    /**
     * 坐标
     */
    private String coordinate;

}