package com.aaa.xj.dynamic.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/5/30 14:53
 * @Description
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class PropertiesUtils implements Serializable {

    private String primary;

    // 所以必然需要一个属性值叫做dynamic
    private Map<String, DBProperties> dynamic = new LinkedHashMap<String, DBProperties>();

}
