package com.aaa.xj.model;

import com.aaa.xj.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "t_special_post")
public class SpecialPost extends BaseModel {

    /**
     * 姓名
     */
    private String name;

    /**
     * 有效证件号
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
     * 毕业院校
     */
    private String school;

    /**
     * 所学专业
     */
    private String major;

    /**
     * 学历
     */
    @Column(name = "education_background")
    private String educationBackground;

    /**
     * 学位
     */
    private String degree;

    /**
     * 特殊岗位
     */
    @Column(name = "special_post")
    private String specialPost;

    /**
     * 单位用户编号
     */
    @Column(name = "user_id")
    private Long userId;


}