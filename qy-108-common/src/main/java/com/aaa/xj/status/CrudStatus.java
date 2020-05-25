package com.aaa.xj.status;

/**
 * @author ligen
 * @program mapping-project-parent
 * @description
 *  CrudStatus 增删改查CRUD
 * @create 2020-05-13 13:26
 */
public enum CrudStatus {
    //增删改查
    GET_SUCCESS("20001","获取数据成功"),
    GET_FAILED("10001","获取数据失败"),
    ADD_SUCCESS("20001","新增数据成功"),
    ADD_FAILED("10001","新增数据失败"),
    UPDATE_SUCCESS("20001","更改数据成功"),
    UPDATE_FAILED("10001","更改数据失败"),
    DELETE_SUCCESS("20001","删除数据成功"),
    DElETE_FAILED("10001","删除数据失败");

    CrudStatus(String code, String msg) {
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
