package com.aaa.xj.redis;

import com.aaa.xj.utils.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

import static com.aaa.xj.staticstatus.RedisProperties.*;
import static com.aaa.xj.staticstatus.RedisProperties.NX;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/5/13 15:00
 * @Description
 **/
@Service
public class RedisService<T> {

    @Autowired
    private JedisCluster jedisCluster;

    /**
     * @author Seven Lee
     * @description
     *      向redis中保存数据(并设置时效时间)
     *      key:redis中的key
     *      value:向redis中保存的数据
     *      nxxx:
     *          这里的值是固定的
     *          "nx":如果redis中key不存在，则可以存数据
     *          "xx":如果redis中的key存在，则才可以存储数据
     *              因为到公司之后实现的都是团队开发:
     *                  假设:
     *                      张三 正在登陆业务--->存redis
     *                          redis.set("book", book);
     *                      李四 正在订单业务--->存redis
     *                          redis.set("book", bookOrder);
     *                      这个时候张三从redis中取数据--->发现取的是bookOrder--->转换异常
     *
     *      expx:时间的单位(这里的值也是固定的)
     *          "ex":
     *              ex的失效时间单位为秒
     *          "px":
     *              px的失效时间单位为毫秒
     *
     *      seconds:
     *          失效时间(如果缺省则说明你不需要设置时间时间)
     * @param [key, value, nxxx, expx, seconds]
     * @date 2020/5/15
     * @return java.lang.String
     * @throws
    **/
    public String set(String key, T value, String nxxx, String expx, Integer seconds) {
        if(null != seconds && 0 < seconds && (NX.equals(nxxx) || XX.equals(nxxx)) && (EX.equals(expx) || PX.equals(expx))) {
            // 说明需要设置失效时间

           return jedisCluster.set(key, JSONUtil.toJsonString(value), nxxx, expx, seconds);

        } else {
            // 说明不需要设置失效时间
            // 就算不需要失效时间，但是我也得知道最终你所传递的是nx还是xx，所以需要再次判断
            if(NX.equals(nxxx)) {
                return String.valueOf(jedisCluster.setnx(key, JSONUtil.toJsonString(value)));
            } else if(XX.equals(nxxx)) {
                return jedisCluster.set(key, JSONUtil.toJsonString(value));
            }
        }
        return NO;
    }
    /**
     * @Summary:
     * @Author:  xj
     * @description
     *          获取redis中的token值
     * @Data: 2020/5/20 16:22
     * @param
     * @param key
     * @Return:java.util.HashMap<java.lang.String,java.lang.Object>
     */
//    public String getTokenByRedis(String key){
//
//        //通过key来获取value token值
//        Object value = redisTemplate.opsForValue().get(key);
//
//       return jedisCluster.set(KEY,JSONUtil.toJsonString(value));
//    }

}
