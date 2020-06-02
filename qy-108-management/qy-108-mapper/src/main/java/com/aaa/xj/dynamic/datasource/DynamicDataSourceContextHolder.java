package com.aaa.xj.dynamic.datasource;

import com.aaa.xj.utils.StringUtils;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/5/30 15:17
 * @Description
 *      创建动态数据源上下文的一个操作对象
 *      (其实它就是一个所给用户提供的API工具类)
 **/
public class DynamicDataSourceContextHolder {

    private DynamicDataSourceContextHolder() {

    }

    // 设置一个变量，存放当前正在运行线程的数据源信息
    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<String>();
    // 存放数据源的id
    public static List<String> dataSourceIds = new ArrayList<String>();

    /**
     * @author Seven Lee
     * @description
     *      设置所选择数据源类型
     * @param [datasourceType]
     * @date 2020/5/30
     * @return void
     * @throws
    **/
    public static void setDatasourceType(String datasourceType) {
        CONTEXT_HOLDER.set(datasourceType);
    }

    /**
     * @author Seven Lee
     * @description
     *      获取当前选择数据源类型，如果没有选择，则获取默认的
     * @param []
     * @date 2020/5/30
     * @return java.lang.String
     * @throws
    **/
    public static String getDataSourceType() {
        return CONTEXT_HOLDER.get();
    }

    /**
     * @author Seven Lee
     * @description
     *      清除数据源
     * @param []
     * @date 2020/5/30
     * @return void
     * @throws
    **/
    public static void clearDataSouceType() {
        CONTEXT_HOLDER.remove();
    }

    /**
     * @author Seven Lee
     * @description
     *      判断当前数据源是否存在
     * @param [dataSourceId]
     * @date 2020/5/30
     * @return java.lang.Boolean
     * @throws
    **/
    public static Boolean isContainsDataSource(String dataSourceId) {
        return dataSourceIds.contains(dataSourceId);
    }

    /**
     * @author Seven Lee
     * @description
     *      防止数据源切换的时候造成混乱
     *          使用链表的优势就在于可以直接从栈中把数据源查出来，防止数据源切换的时候混乱
     *
     *      eg:
     *          现在有A，B，C这三个service层
     *          而且这三个service使用都是不同的数据源
     *          但是问题来了:
     *              AService里面某一个方法调用BService中的方法，恰巧BService的这个方法又需要
     *              去调用CService
     *                  这么一来就一级一级的来切换，最终形成了链条
     *              传统的多数据源切换只能设置到当前线程(也就是说这一个线程只能使用一个事务)
     *              不能满足当前业务需求
     *              所以必须要去模拟JVM的栈操作(栈有一个规则:后进先出，所以就防止了在一个线程中
     *              多次切换数据源的实话发生混乱)
     *
     *        ArrayDeque:
     *          数组的双向队列(特点就是没有容量的限制可以无限扩容)
     * @param
     * @date 2020/5/30
     * @return
     * @throws
    **/
    public static final ThreadLocal<Deque<String>> LOOKUP_KEY_HOLDER = new ThreadLocal() {
        @Override
        protected Object initialValue() {
            return new ArrayDeque();
        }
    };

    /**
     * @author Seven Lee
     * @description
     *      获取当前线程的数据源
     * @param []
     * @date 2020/5/30
     * @return java.lang.String
     * @throws
    **/
    public static String peek() {
        return LOOKUP_KEY_HOLDER.get().peek();
    }

    /**
     * @author Seven Lee
     * @description
     *      设置当前线程的数据源(不要轻易使用)
     *          请遵循源码规则，如果不是必须手动，则千万不要手动调用
     *          避免造成大量未及时清理的垃圾数据存在
     *          如果必须要调动，则调用以后一定要确保清理完毕，否则会造成大量内容被占用，
     *          项目运行效率低下
     * @param [dataSource]
     * @date 2020/5/30
     * @return void
     * @throws 
    **/
    public static void push(String dataSource) {
        LOOKUP_KEY_HOLDER.get().push(StringUtils.isEmpty(dataSource) ? "" :dataSource);
    }

    /**
     * @author Seven Lee
     * @description
     *      清空当前线程数据源
     * @param []
     * @date 2020/5/30
     * @return void
     * @throws
    **/
    public static void poll() {
        Deque<String> deque = LOOKUP_KEY_HOLDER.get();
        deque.poll();// 失败了
        if(deque.isEmpty()) {
            LOOKUP_KEY_HOLDER.remove();
        }
    }

    /**
     * @author Seven Lee
     * @description
     *      强制清空本地线程的数据源
     *      如果你手动调用push()方法，一定最终要调用一个clear()方法
     * @param []
     * @date 2020/5/30
     * @return void
     * @throws
    **/
    public static void clear() {
        LOOKUP_KEY_HOLDER.remove();
    }

}
