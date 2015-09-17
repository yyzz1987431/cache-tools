/**
 * RedisCache.java
 */
package com.qbao.middleware.cache.core;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.alibaba.fastjson.JSON;
import com.qbao.middleware.cache.event.GetOptEvent;
import com.qbao.middleware.cache.event.SetOptEvent;
import com.qbao.middleware.cache.listerner.KeyValueListener;


/**
 * @author Yate
 * @date Sep 17, 2015
 * @description TODO
 * @version 1.0
 */
public class RedisCache implements KeyValueListener {

    protected JedisPool redisPool;

    public RedisCache(final JedisPool pool) {
        if (pool == null)
            throw new NullPointerException();
        this.redisPool = pool;
    }

    protected Jedis getClient() {
        return redisPool.getResource();
    }

    public boolean handleEvent(SetOptEvent e) {
        Jedis client = this.getClient();
        if (client == null) {
            return false;
        }

        try {
            if (e.expriedSec == null || e.expriedSec.intValue() <= 0l)
                client.set(e.key, JSON.toJSONString(e.data));
            else
                client.set(e.key, JSON.toJSONString(e.data), "NX", "EX",
                        e.expriedSec.intValue());

        } finally {
            if (client != null && client.isConnected()) {
                client.close();
            }
        }

        return true;
    }

    public <T> boolean handleEvent(GetOptEvent<T> e) {
        Jedis client = this.getClient();
        if (client == null) {
            return false;
        }

        try {
            String v = client.get(e.key);
            if (v == null || v.trim().equals(""))
                return false;
            e.result = JSON.parseObject(v, e.targetClass);
        } finally {
            if (client != null && client.isConnected()) {
                client.close();
            }
        }

        return true;
    }
}
