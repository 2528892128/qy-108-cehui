package com.aaa.xj.base;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/5/12 16:42
 * @Description
 **/
@Data
@Accessors(chain = true)
public class ResultData<T> implements Serializable {

    private String code;
    private String msg;
    private String detail;
    private T data;

}
