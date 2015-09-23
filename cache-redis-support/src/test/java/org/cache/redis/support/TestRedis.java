/**
 * TestRedis.java
 */
package org.cache.redis.support;

import java.io.Serializable;

import org.junit.Test;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.qbao.middleware.cache.core.RedisCacheClient;
import com.qbao.middleware.cache.exception.CacheCodeException;
import com.qbao.middleware.cache.utils.StringCommandUtils;

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
        JedisPool jedisPool = new JedisPool(config, "127.0.0.1", 6379);

        RedisCacheClient redis = new RedisCacheClient(jedisPool);

        StringCommandUtils key = new StringCommandUtils();
        try {
            key.registerListener("test", redis);
            
            for(int i=0;i<10;i++){
                key.set("yate"+i, "yate",10000);
                
                TestA a = new TestA();
                a.a="hahaha";
                a.b=11111L;
                
                key.<TestA>set("yate_"+i, a,10000);
            }
            
            System.out.println(key.get("yate1", String.class));
            TestA x = key.get("yate_1", TestA.class);
            System.out.println(x.a);
            System.out.println(x.b);
            
        } catch (CacheCodeException e) {
            e.printStackTrace();
        }
    }
}
