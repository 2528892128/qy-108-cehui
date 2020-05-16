package com.aaa.xj.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 *   @PropertySource这个注解的作用是加载某一个properties文件中属性的值
 */
@Component
@ConfigurationProperties(prefix = "spring.redis")
@PropertySource("classpath:properties/redis_cluster.properties")
@Data
public class RedisClusterProperties {

    private String nodes;
    private Integer maxAttempts;
    private Integer commandTimeout;
}
