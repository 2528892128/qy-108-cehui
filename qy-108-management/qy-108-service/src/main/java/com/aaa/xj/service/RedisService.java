package com.aaa.xj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/5/13 15:00
 * @Description
 **/
@Service
public class RedisService {

    @Autowired
    private JedisCluster jedisCluster;

}
