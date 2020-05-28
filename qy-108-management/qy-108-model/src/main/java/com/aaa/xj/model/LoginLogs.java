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


}