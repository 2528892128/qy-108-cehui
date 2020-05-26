package com.aaa.xj.model;

import javax.persistence.*;
import java.util.Date;

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
    private Date checkDate;

    /**
     * 检定有效日期
     */
    @Column(name = "effective_date")
    private Date effectiveDate;

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
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "modify_time")
    private Date modifyTime;

    /**
     * 获取编号
     *
     * @return id - 编号
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置编号
     *
     * @param id 编号
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取仪器设备名称
     *
     * @return name - 仪器设备名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置仪器设备名称
     *
     * @param name 仪器设备名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取品牌型号
     *
     * @return brand - 品牌型号
     */
    public String getBrand() {
        return brand;
    }

    /**
     * 设置品牌型号
     *
     * @param brand 品牌型号
     */
    public void setBrand(String brand) {
        this.brand = brand == null ? null : brand.trim();
    }

    /**
     * 获取出厂编号
     *
     * @return production_id - 出厂编号
     */
    public String getProductionId() {
        return productionId;
    }

    /**
     * 设置出厂编号
     *
     * @param productionId 出厂编号
     */
    public void setProductionId(String productionId) {
        this.productionId = productionId == null ? null : productionId.trim();
    }

    /**
     * 获取数量
     *
     * @return number - 数量
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * 设置数量
     *
     * @param number 数量
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * 获取检定日期
     *
     * @return check_date - 检定日期
     */
    public Date getCheckDate() {
        return checkDate;
    }

    /**
     * 设置检定日期
     *
     * @param checkDate 检定日期
     */
    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    /**
     * 获取检定有效日期
     *
     * @return effective_date - 检定有效日期
     */
    public Date getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * 设置检定有效日期
     *
     * @param effectiveDate 检定有效日期
     */
    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    /**
     * 获取发票代码
     *
     * @return invoice_code - 发票代码
     */
    public String getInvoiceCode() {
        return invoiceCode;
    }

    /**
     * 设置发票代码
     *
     * @param invoiceCode 发票代码
     */
    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode == null ? null : invoiceCode.trim();
    }

    /**
     * 获取检定机构
     *
     * @return check_department - 检定机构
     */
    public String getCheckDepartment() {
        return checkDepartment;
    }

    /**
     * 设置检定机构
     *
     * @param checkDepartment 检定机构
     */
    public void setCheckDepartment(String checkDepartment) {
        this.checkDepartment = checkDepartment == null ? null : checkDepartment.trim();
    }

    /**
     * 获取检定证书号
     *
     * @return check_certificate_id - 检定证书号
     */
    public String getCheckCertificateId() {
        return checkCertificateId;
    }

    /**
     * 设置检定证书号
     *
     * @param checkCertificateId 检定证书号
     */
    public void setCheckCertificateId(String checkCertificateId) {
        this.checkCertificateId = checkCertificateId == null ? null : checkCertificateId.trim();
    }

    /**
     * 获取认定
     *
     * @return identified - 认定
     */
    public String getIdentified() {
        return identified;
    }

    /**
     * 设置认定
     *
     * @param identified 认定
     */
    public void setIdentified(String identified) {
        this.identified = identified == null ? null : identified.trim();
    }

    /**
     * 获取单位用户编号
     *
     * @return user_id - 单位用户编号
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置单位用户编号
     *
     * @param userId 单位用户编号
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取创建时间

     *
     * @return create_time - 创建时间

     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间

     *
     * @param createTime 创建时间

     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取修改时间
     *
     * @return modify_time - 修改时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 设置修改时间
     *
     * @param modifyTime 修改时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}