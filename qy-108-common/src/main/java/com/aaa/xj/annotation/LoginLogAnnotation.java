package com.aaa.xj.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/5/27 14:25
 * @Description
 *      自定义注解
 *      实现自定义注解必须要添加两个注解:
 *          1.@Target:标识了该注解所使用的位置(所使用的范围)
 *              TYPE:作用于类
 *              METHOD:作用于方法
 *              FIELD:作用于属性
 *          2.Retention:标识了该注解什么时候生效
 *              RUNTIME:项目运行时生效
 *              TEST:测试阶段生效
 *              ...
 *
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginLogAnnotation {

    /**
     * 需要执行的操作类型:
     *      eg:
     *          delete操作，login操作，register注册...
     * @return
     */
    String operationType() default "";

    /**
     * 要执行的具体操作内容:
     *      eg:
     *          删除用户操作，删除图书操作。。。
     * @return
     */
    String operationName() default "";

}
