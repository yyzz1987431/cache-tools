/**
 * RedisUtils.java
 */
package com.qbao.middleware.cache.utils;

import java.util.HashMap;
import java.util.Map;

import com.qbao.middleware.cache.core.support.redis.IKeyCommand;
import com.qbao.middleware.cache.event.redis.key.KeyDelEvent;
import com.qbao.middleware.cache.event.redis.key.KeyExistsEvent;
import com.qbao.middleware.cache.event.redis.key.KeyExpireAtEvent;
import com.qbao.middleware.cache.event.redis.key.KeyExpirtEvent;
import com.qbao.middleware.cache.event.redis.key.KeyTtlEvent;
import com.qbao.middleware.cache.event.redis.key.KeyTypeEvent;
import com.qbao.middleware.cache.exception.CacheCodeException;
import com.qbao.middleware.cache.exception.CacheExceptionEnum;
import com.qbao.middleware.cache.listener.KeyListener;

/**
 * @author Yate
 * @date Sep 17, 2015
 * @description TODO
 * @version 1.0
 */
public class KeyCommandUtils implements IKeyCommand {

    protected final Map<String, KeyListener> handles = new HashMap<String, KeyListener>();

    public void registerListener(String key, KeyListener l)
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
     * com.qbao.middleware.cache.core.support.redis.IKeyClient#del(java.lang
     * .String)
     */
    @Override
    public Long del(String key) throws CacheCodeException {
        if (key == null || key.trim().equalsIgnoreCase("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        KeyDelEvent e = new KeyDelEvent(key, this);
        for (KeyListener l : handles.values()) {
            if (l.handleEvent(e))
                break;
        }
        return e.result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.redis.IKeyClient#exists(java.lang
     * .String)
     */
    @Override
    public Boolean exists(String key) throws CacheCodeException {
        if (key == null || key.trim().equalsIgnoreCase("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        KeyExistsEvent e = new KeyExistsEvent(key, this);
        for (KeyListener l : handles.values()) {
            if (l.handleEvent(e))
                break;
        }
        return e.result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.redis.IKeyClient#ttl(java.lang
     * .String)
     */
    @Override
    public Long ttl(String key) throws CacheCodeException {
        if (key == null || key.trim().equalsIgnoreCase("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        KeyTtlEvent e = new KeyTtlEvent(key, this);
        for (KeyListener l : handles.values()) {
            if (l.handleEvent(e))
                break;
        }
        return e.result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.redis.IKeyClient#expire(java.lang
     * .String)
     */
    @Override
    public Long expire(String key, int seconds) throws CacheCodeException {
        if (key == null || key.trim().equalsIgnoreCase("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        KeyExpirtEvent e = new KeyExpirtEvent(key, seconds, this);
        for (KeyListener l : handles.values()) {
            if (l.handleEvent(e))
                break;
        }
        return e.result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.redis.IKeyClient#expireAt(java
     * .lang.String)
     */
    @Override
    public Long expireAt(String key, long unixTime) throws CacheCodeException {
        if (key == null || key.trim().equalsIgnoreCase("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        KeyExpireAtEvent e = new KeyExpireAtEvent(key, unixTime, this);
        for (KeyListener l : handles.values()) {
            if (l.handleEvent(e))
                break;
        }
        return e.result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.redis.IKeyClient#type(java.lang
     * .String)
     */
    @Override
    public String type(String key) throws CacheCodeException {
        if (key == null || key.trim().equalsIgnoreCase("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        KeyTypeEvent e = new KeyTypeEvent(key, this);
        for (KeyListener l : handles.values()) {
            if (l.handleEvent(e))
                break;
        }
        return e.result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.redis.IKeyClient#scan(java.lang
     * .String)
     */
    @Override
    public void scan(String pettey) throws CacheCodeException {
        throw new CacheCodeException(CacheExceptionEnum.不支持的操作类型);
    }

}
