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
@Table(name = "t_equipment")
public class Equipment {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 仪器设备名称
     */
    private String name;

    /**
     * 品牌型号
     */
    private String brand;

    /**
     * 出厂编号
     */
    @Column(name = "production_id")
    private String productionId;

    /**
     * 数量
     */
    private Integer number;

    /**
     * 检定日期
     */
    @Column(name = "check_date")
    private String checkDate;

    /**
     * 检定有效日期
     */
    @Column(name = "effective_date")
    private String effectiveDate;

    /**
     * 发票代码
     */
    @Column(name = "invoice_code")
    private String invoiceCode;

    /**
     * 检定机构
     */
    @Column(name = "check_department")
    private String checkDepartment;

    /**
     * 检定证书号
     */
    @Column(name = "check_certificate_id")
    private String checkCertificateId;

    /**
     * 认定
     */
    private String identified;

    /**
     * 单位用户编号
     */
    @Column(name = "user_id")
    private Long userId;

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

}