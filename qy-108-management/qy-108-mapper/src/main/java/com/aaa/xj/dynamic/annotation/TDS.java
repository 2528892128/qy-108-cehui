package com.aaa.xj.dynamic.annotation;

import java.lang.annotation.*;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/5/30 14:58
 * @Description
 *      切换数据源的注解(也就是说被这个注解所标识的方法或者类可以使用这个注解所指定的数据源)
 *
 *      @Inherited:
 *          这个注解的作用是在类的继承的情况下才会体现
 *          一般会有三种情况:
 *              1.当类被继承的时候，如果父类中如果使用了TDS注解，则子类也会默认自动的携带TDS
 *                  @TDS
 *                  public class BaseService{
 *
 *                  }
 *
 *                  public class UserService extends BaseService {
 *
 *                  }
 *                  相当于UserService上也会带这个TDS注解
 *
 *              2.当接口出现继承的时候该注解不会生效
 *              3.当某一个接口被实现的时候，最终@Inherited也不会生效
 *
 **/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface TDS {

    String value() default "mysql";

}
