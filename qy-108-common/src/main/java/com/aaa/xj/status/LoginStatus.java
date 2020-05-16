package com.aaa.xj.status;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/5/12 16:52
 * @Description
 **/
public enum LoginStatus {

    LOGIN_SUCCESS("20001", "登录成功"),
    LOGIN_FAILED("10001", "登录失败"),
    USER_EXIST("20002", "用户存在"),
    USER_NOT_EXIST("10002", "用户不存在"),
    PASSWORD_WRONG("10003", "密码错误"),
    USER_LOCKED("10004", "账号被锁定"),
    LOGOUT_WRONG("10005", "用户退出异常"),
    DELETE_SUCCESS("10007","删除成功" ),
    DELETE_FAILED("10008","删除失败" ),
    UPDATE_FAILED("10010" ,"更新失败"),
    UPDATE_SUCCESS("10009","更新成功" ),
    QUERY_SUCCESS("10011","查询成功" ),
    QUERY_FALSE("10012","查询失败" );




    LoginStatus(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
