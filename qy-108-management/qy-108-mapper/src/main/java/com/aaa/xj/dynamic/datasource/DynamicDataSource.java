package com.aaa.xj.dynamic.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/5/30 16:06
 * @Description
 *      重写数据源的路由规则
 **/
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getDataSourceType();
    }
}
