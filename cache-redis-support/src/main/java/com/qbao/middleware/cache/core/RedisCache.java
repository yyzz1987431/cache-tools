/**
 * RedisCache.java
 */
package com.qbao.middleware.cache.core;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.alibaba.fastjson.JSON;
import com.qbao.middleware.cache.event.redis.hash.HashDelEvent;
import com.qbao.middleware.cache.event.redis.hash.HashExistsEvent;
import com.qbao.middleware.cache.event.redis.hash.HashGetEvent;
import com.qbao.middleware.cache.event.redis.hash.HashIncrByEvent;
import com.qbao.middleware.cache.event.redis.hash.HashIncrByFloatEvent;
import com.qbao.middleware.cache.event.redis.hash.HashLenEvent;
import com.qbao.middleware.cache.event.redis.hash.HashSetEvent;
import com.qbao.middleware.cache.event.redis.string.StringAppendEvent;
import com.qbao.middleware.cache.event.redis.string.StringDecrEvent;
import com.qbao.middleware.cache.event.redis.string.StringGetEvent;
import com.qbao.middleware.cache.event.redis.string.StringIncrEvent;
import com.qbao.middleware.cache.event.redis.string.StringSetEvent;
import com.qbao.middleware.cache.listerner.HashListener;
import com.qbao.middleware.cache.listerner.StringListener;

/**
 * @author Yate
 * @date Sep 17, 2015
 * @description TODO
 * @version 1.0
 */
public class RedisCache implements StringListener, HashListener {

    protected JedisPool redisPool;

    public RedisCache(final JedisPool pool) {
        if (pool == null)
            throw new NullPointerException();
        this.redisPool = pool;
    }

    protected Jedis getClient() {
        return redisPool.getResource();
    }

    public boolean handleEvent(StringSetEvent e) {
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

    public <T> boolean handleEvent(StringGetEvent<T> e) {
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

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.listerner.KeyValueListener#handleEvent(com.
     * qbao.middleware.cache.event.AppendOptEvent)
     */
    @Override
    public boolean handleEvent(StringAppendEvent e) {
        Jedis client = this.getClient();
        if (client == null) {
            return false;
        }

        try {
            String v = client.get(e.key);
            if (v == null || v.trim().equals(""))
                return false;
            v += e.appendValue;
            client.set(e.key, JSON.toJSONString(v));
        } finally {
            if (client != null && client.isConnected()) {
                client.close();
            }
        }

        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.listerner.NumberMathListener#handleEvent(com
     * .qbao.middleware.cache.event.IncrOptEvent)
     */
    @Override
    public boolean handleEvent(StringIncrEvent e) {
        Jedis client = this.getClient();
        if (client == null) {
            return false;
        }

        try {
            client.incrBy(e.key, e.value);
        } finally {
            if (client != null && client.isConnected()) {
                client.close();
            }
        }

        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.listerner.NumberMathListener#handleEvent(com
     * .qbao.middleware.cache.event.DecrOptEvent)
     */
    @Override
    public boolean handleEvent(StringDecrEvent e) {
        Jedis client = this.getClient();
        if (client == null) {
            return false;
        }

        try {
            client.decrBy(e.key, e.value);
        } finally {
            if (client != null && client.isConnected()) {
                client.close();
            }
        }

        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.listerner.HashListener#handleEvent(com.qbao
     * .middleware.cache.event.redis.hash.HashExistsEvent)
     */
    @Override
    public boolean handleEvent(HashExistsEvent e) {
        Jedis client = this.getClient();
        if (client == null) {
            return false;
        }

        try {
            Boolean x = client.hexists(e.key, e.field);
            if (x == null || !x.booleanValue())
                return false;
        } finally {
            if (client != null && client.isConnected()) {
                client.close();
            }
        }

        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.listerner.HashListener#handleEvent(com.qbao
     * .middleware.cache.event.redis.hash.HashGetEvent)
     */
    @Override
    public <T> boolean handleEvent(HashGetEvent<T> e) {
        Jedis client = this.getClient();
        if (client == null) {
            return false;
        }

        try {
            String v = client.hget(e.key, e.field);
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

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.listerner.HashListener#handleEvent(com.qbao
     * .middleware.cache.event.redis.hash.HashSetEvent)
     */
    @Override
    public boolean handleEvent(HashSetEvent e) {
        Jedis client = this.getClient();
        if (client == null) {
            return false;
        }

        try {
            Long v = client.hset(e.key, e.field, JSON.toJSONString(e.data));
            if (v == null)
                return false;
            e.result = v;
        } finally {
            if (client != null && client.isConnected()) {
                client.close();
            }
        }

        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.listerner.HashListener#handleEvent(com.qbao
     * .middleware.cache.event.redis.hash.HashDelEvent)
     */
    @Override
    public boolean handleEvent(HashDelEvent e) {
        Jedis client = this.getClient();
        if (client == null) {
            return false;
        }

        try {
            Long v = client.hdel(e.key, e.field);
            if (v == null)
                return false;
            e.result = v;
        } finally {
            if (client != null && client.isConnected()) {
                client.close();
            }
        }

        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.listerner.HashListener#handleEvent(com.qbao
     * .middleware.cache.event.redis.hash.HashIncrByEvent)
     */
    @Override
    public boolean handleEvent(HashIncrByEvent e) {
        Jedis client = this.getClient();
        if (client == null) {
            return false;
        }

        try {
            Long v = client.hincrBy(e.key, e.field, e.v);
            if (v == null)
                return false;
            e.result = v;
        } finally {
            if (client != null && client.isConnected()) {
                client.close();
            }
        }

        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.listerner.HashListener#handleEvent(com.qbao
     * .middleware.cache.event.redis.hash.HashIncrByFloatEvent)
     */
    @Override
    public boolean handleEvent(HashIncrByFloatEvent e) {
        Jedis client = this.getClient();
        if (client == null) {
            return false;
        }

        try {
            Double v = client.hincrByFloat(e.key, e.field, e.v);
            if (v == null)
                return false;
            e.result = v;
        } finally {
            if (client != null && client.isConnected()) {
                client.close();
            }
        }

        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.listerner.HashListener#handleEvent(com.qbao
     * .middleware.cache.event.redis.hash.HashLenEvent)
     */
    @Override
    public boolean handleEvent(HashLenEvent e) {
        Jedis client = this.getClient();
        if (client == null) {
            return false;
        }

        try {
            Long v = client.hlen(e.key);
            if (v == null)
                return false;
            e.result = v;
        } finally {
            if (client != null && client.isConnected()) {
                client.close();
            }
        }

        return true;
    }
}
