package com.aaa.xj.dynamic.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/5/30 14:47
 * @Description
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class DBProperties implements Serializable {

    private String pollName;// 称之为唯一标识的名字(这个标识就是来装载mysql,oracle,sqlserver)

    private String driverClassName;

    private String url;

    private String username;

    private String password;

    private String type;

}
