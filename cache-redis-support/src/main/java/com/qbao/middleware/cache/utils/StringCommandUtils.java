/**
 * RedisUtils.java
 */
package com.qbao.middleware.cache.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.qbao.middleware.cache.core.support.redis.IStringCommand;
import com.qbao.middleware.cache.event.redis.string.StringAppendEvent;
import com.qbao.middleware.cache.event.redis.string.StringDecrEvent;
import com.qbao.middleware.cache.event.redis.string.StringGetEvent;
import com.qbao.middleware.cache.event.redis.string.StringIncrEvent;
import com.qbao.middleware.cache.event.redis.string.StringSetEvent;
import com.qbao.middleware.cache.exception.CacheCodeException;
import com.qbao.middleware.cache.exception.CacheExceptionEnum;
import com.qbao.middleware.cache.listener.StringListener;

/**
 * @author Yate
 * @date Sep 17, 2015
 * @description TODO
 * @version 1.0
 */
public class StringCommandUtils implements IStringCommand {

    protected final Map<String, StringListener> handles = new HashMap<String, StringListener>();

    public void registerListener(String key, StringListener l)
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
     * @see com.qbao.middleware.cache.core.IKeyValueCache#set(java.lang.String,
     * java.lang.Object)
     */
    public <T extends Serializable> void set(String key, T data)
            throws CacheCodeException {
        this.set(key, data, null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.qbao.middleware.cache.core.IKeyValueCache#set(java.lang.String,
     * java.lang.Object, long)
     */
    public <T extends Serializable> void set(String key, T data, Integer time)
            throws CacheCodeException {
        if (key == null || data == null || key.trim().equalsIgnoreCase("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        StringSetEvent e = new StringSetEvent(key, data, time, this);
        for (StringListener l : handles.values()) {
            if (l.handleEvent(e))
                break;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.qbao.middleware.cache.core.IKeyValueCache#get(java.lang.String,
     * java.lang.Class)
     */
    public <T extends Serializable> T get(String key, Class<T> clazz)
            throws CacheCodeException {
        if (key == null || clazz == null || key.trim().equalsIgnoreCase("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        StringGetEvent<T> e = new StringGetEvent<T>(key, clazz, this);
        for (StringListener l : handles.values()) {
            if (l.handleEvent(e))
                break;
        }
        return e.result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.IKeyValueClient#append(java.lang
     * .String, java.lang.String)
     */
    @Override
    public void append(String key, String appendStr) throws CacheCodeException {
        if (key == null || appendStr == null || key.trim().equalsIgnoreCase("")
                || appendStr.trim().equalsIgnoreCase(""))
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        StringAppendEvent e = new StringAppendEvent(key, appendStr, this);
        for (StringListener l : handles.values()) {
            if (l.handleEvent(e))
                break;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.INumberMathClient#decr(java.lang
     * .String)
     */
    @Override
    public void decr(String key) throws CacheCodeException {
        if (key == null || key.trim().equalsIgnoreCase("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        StringDecrEvent e = new StringDecrEvent(key, this);
        for (StringListener l : handles.values()) {
            if (l.handleEvent(e))
                break;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.INumberMathClient#decrBy(java.
     * lang.String, java.lang.Integer)
     */
    @Override
    public void decrBy(String key, Integer v) throws CacheCodeException {
        if (key == null || key.trim().equalsIgnoreCase("") || v == null
                || v.intValue() >= 0) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        StringDecrEvent e = new StringDecrEvent(key, v, this);
        for (StringListener l : handles.values()) {
            if (l.handleEvent(e))
                break;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.INumberMathClient#incr(java.lang
     * .String)
     */
    @Override
    public void incr(String key) throws CacheCodeException {
        if (key == null || key.trim().equalsIgnoreCase("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        StringIncrEvent e = new StringIncrEvent(key, this);
        for (StringListener l : handles.values()) {
            if (l.handleEvent(e))
                break;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.INumberMathClient#incrBy(java.
     * lang.String, java.lang.Integer)
     */
    @Override
    public void incrBy(String key, Integer v) throws CacheCodeException {
        if (key == null || key.trim().equalsIgnoreCase("") || v == null
                || v.intValue() <= 0) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        StringIncrEvent e = new StringIncrEvent(key, v, this);
        for (StringListener l : handles.values()) {
            if (l.handleEvent(e))
                break;
        }
    }
}
