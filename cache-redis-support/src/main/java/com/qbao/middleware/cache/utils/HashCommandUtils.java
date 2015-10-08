/**
 * RedisUtils.java
 */
package com.qbao.middleware.cache.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.qbao.middleware.cache.core.support.redis.IHashCommand;
import com.qbao.middleware.cache.event.redis.hash.HashDelEvent;
import com.qbao.middleware.cache.event.redis.hash.HashExistsEvent;
import com.qbao.middleware.cache.event.redis.hash.HashGetEvent;
import com.qbao.middleware.cache.event.redis.hash.HashIncrByEvent;
import com.qbao.middleware.cache.event.redis.hash.HashIncrByFloatEvent;
import com.qbao.middleware.cache.event.redis.hash.HashLenEvent;
import com.qbao.middleware.cache.event.redis.hash.HashSetEvent;
import com.qbao.middleware.cache.exception.CacheCodeException;
import com.qbao.middleware.cache.exception.CacheExceptionEnum;
import com.qbao.middleware.cache.listener.HashListener;

/**
 * @author Yate
 * @date Sep 17, 2015
 * @description TODO
 * @version 1.0
 */
public class HashCommandUtils implements IHashCommand {

    protected final Map<String, HashListener> handles = new HashMap<String, HashListener>();

    public void registerListener(String key, HashListener l)
            throws CacheCodeException {
        if (key == null || l == null || key.trim().equals(""))
            throw new CacheCodeException(CacheExceptionEnum.参数异常);

        if (!handles.containsKey(key)) {
            synchronized (this) {
                if (!handles.containsKey(key)) {
                    handles.put(key, l);
                }
            }
        }
    }

    public void unregisterListener(String key) throws CacheCodeException {
        if (key == null || key.trim().equals(""))
            throw new CacheCodeException(CacheExceptionEnum.参数异常);

        if (handles.containsKey(key)) {
            handles.remove(key);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.redis.IHashClient#exists(java.
     * lang.String, java.lang.String)
     */
    @Override
    public Boolean hexists(String key, String field) throws CacheCodeException {
        if (key == null || field == null || key.trim().equalsIgnoreCase("")
                || field.trim().equalsIgnoreCase("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        HashExistsEvent e = new HashExistsEvent(key, field, this);
        for (HashListener l : handles.values()) {
            if (l.handleEvent(e))
                break;
        }
        return e.result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.redis.IHashClient#get(java.lang
     * .String, java.lang.String)
     */
    @Override
    public <T extends Serializable> T hget(String key, String field,
            Class<T> clazz) throws CacheCodeException {
        if (key == null || field == null || key.trim().equalsIgnoreCase("")
                || field.trim().equalsIgnoreCase("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        HashGetEvent<T> e = new HashGetEvent<T>(key, field, clazz, this);
        for (HashListener l : handles.values()) {
            if (l.handleEvent(e))
                break;
        }
        return e.result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.redis.IHashClient#set(java.lang
     * .String, java.lang.String, java.io.Serializable)
     */
    @Override
    public <T extends Serializable> Long hset(String key, String field, T data)
            throws CacheCodeException {
        if (key == null || data == null || key.trim().equalsIgnoreCase("")
                || field == null || field.trim().equalsIgnoreCase("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        HashSetEvent e = new HashSetEvent(key, field, data, this);
        for (HashListener l : handles.values()) {
            if (l.handleEvent(e))
                break;
        }
        return e.result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.redis.IHashClient#del(java.lang
     * .String, java.lang.String)
     */
    @Override
    public Long hdel(String key, String field) throws CacheCodeException {
        if (key == null || key.trim().equalsIgnoreCase("") || field == null
                || field.trim().equalsIgnoreCase("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        HashDelEvent e = new HashDelEvent(key, field, this);
        for (HashListener l : handles.values()) {
            if (l.handleEvent(e))
                break;
        }
        return e.result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.redis.IHashClient#incrBy(java.
     * lang.String, java.lang.String, long)
     */
    @Override
    public Long hincrBy(String key, String field, long v)
            throws CacheCodeException {
        if (key == null || key.trim().equalsIgnoreCase("") || field == null
                || field.trim().equalsIgnoreCase("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        HashIncrByEvent e = new HashIncrByEvent(key, field, v, this);
        for (HashListener l : handles.values()) {
            if (l.handleEvent(e))
                break;
        }
        return e.result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.redis.IHashClient#incrByFloat(
     * java.lang.String, java.lang.String, double)
     */
    @Override
    public Double hincrByFloat(String key, String field, double v)
            throws CacheCodeException {
        
        if (key == null || key.trim().equalsIgnoreCase("") || field == null
                || field.trim().equalsIgnoreCase("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        HashIncrByFloatEvent e = new HashIncrByFloatEvent(key, field, v, this);
        for (HashListener l : handles.values()) {
            if (l.handleEvent(e))
                break;
        }
        return e.result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.redis.IHashClient#len(java.lang
     * .String)
     */
    @Override
    public Long hlen(String key) throws CacheCodeException {
        if (key == null || key.trim().equalsIgnoreCase("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        HashLenEvent e = new HashLenEvent(key, this);
        for (HashListener l : handles.values()) {
            if (l.handleEvent(e))
                break;
        }
        return e.result;
    }

}
