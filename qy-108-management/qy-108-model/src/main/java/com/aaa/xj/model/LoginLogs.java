package com.aaa.xj.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "t_login_log")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginLogs  implements Serializable {
    /**
     * 用户名
     */
    @Column(name = "USERNAME")
    private String username;

    /**
     * 登录时间
     */
    @Column(name = "LOGIN_TIME")
    private String loginTime;

    /**
     * 登录地点
     */
    @Column(name = "LOCATION")
    private String location;

    /**
     * IP地址
     */
    @Column(name = "IP")
    private String ip;

    /**
     * 执行操作的类型
     */
    @Column(name = "OPERATION_TYPE")
    private String operationType;

    /**
     * 操作内容
     */
    @Column(name = "OPERATION_NAME")
    private String operationName;

    /**
     * 获取用户名
     *
     * @return USERNAME - 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * 获取登录时间
     *
     * @return LOGIN_TIME - 登录时间
     */
    public String getLoginTime() {
        return loginTime;
    }

    /**
     * 设置登录时间
     *
     * @param loginTime 登录时间
     */
    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime == null ? null : loginTime.trim();
    }

    /**
     * 获取登录地点
     *
     * @return LOCATION - 登录地点
     */
    public String getLocation() {
        return location;
    }

    /**
     * 设置登录地点
     *
     * @param location 登录地点
     */
    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    /**
     * 获取IP地址
     *
     * @return IP - IP地址
     */
    public String getIp() {
        return ip;
    }

    /**
     * 设置IP地址
     *
     * @param ip IP地址
     */
    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    /**
     * 获取执行操作的类型
     *
     * @return OPERATION_TYPE - 执行操作的类型
     */
    public String getOperationType() {
        return operationType;
    }

    /**
     * 设置执行操作的类型
     *
     * @param operationType 执行操作的类型
     */
    public void setOperationType(String operationType) {
        this.operationType = operationType == null ? null : operationType.trim();
    }

    /**
     * 获取操作内容
     *
     * @return OPERATION_NAME - 操作内容
     */
    public String getOperationName() {
        return operationName;
    }

    /**
     * 设置操作内容
     *
     * @param operationName 操作内容
     */
    public void setOperationName(String operationName) {
        this.operationName = operationName == null ? null : operationName.trim();
    }
}