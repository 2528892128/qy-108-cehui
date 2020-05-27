package com.aaa.xj.model;

import com.aaa.xj.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "t_technicist")
public class Technicist extends BaseModel {

    /**
     * 姓名
     */
    private String name;

    /**
     * 计入专业技术人员类别
     */
    @Column(name = "major_type")
    private String majorType;

    /**
     * 证件类型
     */
    @Column(name = "id_type")
    private String idType;

    /**
     * 证件号码
     */
    @Column(name = "id_number")
    private String idNumber;

    /**
     * 性别 0:女 1:男 2:保密
     */
    private Integer sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 工作年限
     */
    @Column(name = "work_year")
    private Integer workYear;

    /**
     * 职务
     */
    private String duty;

    /**
     * 职称
     */
    private String title;

    /**
     * 毕业院校
     */
    private String school;

    /**
     * 毕业时间
     */
    @Column(name = "graduation_date")
    private Date graduationDate;

    /**
     * 学位
     */
    private String degree;

    /**
     * 学历
     */
    @Column(name = "education_background")
    private String educationBackground;

    /**
     * 所学专业
     */
    private String major;

    /**
     * 职称专业
     */
    @Column(name = "title_major")
    private String titleMajor;

    /**
     * 进入本单位时间
     */
    @Column(name = "start_time")
    private Date startTime;

    /**
     * 任职时间(获得职称时间)
     */
    @Column(name = "title_time")
    private Date titleTime;

    /**
     * 聘用或劳动合同开始时间
     */
    @Column(name = "start_contract")
    private Date startContract;

    /**
     * 聘用或劳动合同终止时间
     */
    @Column(name = "end_contract")
    private Date endContract;

    /**
     * 现从事工作
     */
    private String post;

    /**
     * 从事测绘工作年限
     */
    @Column(name = "mapping_year")
    private Integer mappingYear;

    /**
     * 特殊岗位
     */
    @Column(name = "special_post")
    private String specialPost;

    /**
     * 认定
     */
    private String affirm;

    /**
     * 单位用户编号
     */
    @Column(name = "user_id")
    private Long userId;

}