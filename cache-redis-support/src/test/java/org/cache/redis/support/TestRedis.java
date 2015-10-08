/**
 * TestRedis.java
 */
package org.cache.redis.support;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.qbao.middleware.cache.core.RedisCommandHandle;
import com.qbao.middleware.cache.exception.CacheCodeException;
import com.qbao.middleware.cache.listerner.CacheListener;
import com.qbao.middleware.cache.utils.CacheRedisUtils;

/**
 * @author Yate
 * @date Sep 17, 2015
 * @description TODO
 * @version 1.0
 */
public class TestRedis {

    public static class TestA implements Serializable {

        public String a;
        public Long b;
    }

    @Test
    public void init() {
        JedisPoolConfig config = new JedisPoolConfig();
        // 设置最大连接数
        config.setMaxTotal(1);
        // 设置最大空闲数
        config.setMaxIdle(1);
        // 设置超时时间
        config.setMaxWaitMillis(30000);

        // 初始化连接池
//        JedisPool jedisPool1 = new JedisPool(config, "192.168.7.33", 6379);
         JedisPool jedisPool2 = new JedisPool(config, "192.168.7.85", 6379);

//        RedisCommandHandle redis1 = new RedisCommandHandle(jedisPool1);
         RedisCommandHandle redis2 = new RedisCommandHandle(jedisPool2);
        Map<String, CacheListener> h = new HashMap<String, CacheListener>();
//        h.put("redis1", redis1);
         h.put("redis2", redis2);

        CacheRedisUtils key = new CacheRedisUtils(h);
        try {
//            key.registerListener("test1", redis1);
             key.registerListener("test2", redis2);

            // System.out.println(key.get("yate1", String.class));
            // for (int i = 0; i < 10; i++) {
            // key.<String> set("yate" + i, "yate", 10000);
            //
            // TestA a = new TestA();
            // a.a = "hahaha";
            // a.b = 11111L;
            //
            // key.<TestA> set("yate_" + i, a, 10000);
            // }
            //
            // System.out.println(key.get("yate1", String.class));
            // TestA x = key.get("yate_1", TestA.class);
            // System.out.println(x.a);
            // System.out.println(x.b);
            //

            for (int i = 0; i < 10; i++) {
                key.hset("test.hash.all", "test" + i, "hahaha" + i);
            }

            Map<String, Object> x = key.hgetall("test.hash.all", String.class);

            for (Map.Entry<String, Object> xxx : x.entrySet()) {
                System.out.println(((String) xxx.getValue()).toString());
            }

        } catch (CacheCodeException e) {
            e.printStackTrace();
        }
    }
}
