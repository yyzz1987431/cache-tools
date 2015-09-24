/**
 * CacheRedisUtils.java
 */
package com.qbao.middleware.cache.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.qbao.middleware.cache.core.support.redis.IHashCommand;
import com.qbao.middleware.cache.core.support.redis.IKeyCommand;
import com.qbao.middleware.cache.core.support.redis.IStringCommand;
import com.qbao.middleware.cache.event.redis.hash.HashDelEvent;
import com.qbao.middleware.cache.event.redis.hash.HashExistsEvent;
import com.qbao.middleware.cache.event.redis.hash.HashGetEvent;
import com.qbao.middleware.cache.event.redis.hash.HashIncrByEvent;
import com.qbao.middleware.cache.event.redis.hash.HashIncrByFloatEvent;
import com.qbao.middleware.cache.event.redis.hash.HashLenEvent;
import com.qbao.middleware.cache.event.redis.hash.HashSetEvent;
import com.qbao.middleware.cache.event.redis.key.KeyDelEvent;
import com.qbao.middleware.cache.event.redis.key.KeyExistsEvent;
import com.qbao.middleware.cache.event.redis.key.KeyExpireAtEvent;
import com.qbao.middleware.cache.event.redis.key.KeyExpirtEvent;
import com.qbao.middleware.cache.event.redis.key.KeyTtlEvent;
import com.qbao.middleware.cache.event.redis.key.KeyTypeEvent;
import com.qbao.middleware.cache.event.redis.string.StringAppendEvent;
import com.qbao.middleware.cache.event.redis.string.StringDecrEvent;
import com.qbao.middleware.cache.event.redis.string.StringGetEvent;
import com.qbao.middleware.cache.event.redis.string.StringIncrEvent;
import com.qbao.middleware.cache.event.redis.string.StringSetEvent;
import com.qbao.middleware.cache.exception.CacheCodeException;
import com.qbao.middleware.cache.exception.CacheExceptionEnum;
import com.qbao.middleware.cache.listener.HashListener;
import com.qbao.middleware.cache.listener.KeyListener;
import com.qbao.middleware.cache.listener.StringListener;
import com.qbao.middleware.cache.listerner.CacheListener;

/**
 * @author Yate
 * @date Sep 23, 2015
 * @description TODO
 * @version 1.0
 */
public class CacheRedisUtils implements IHashCommand, IKeyCommand,
        IStringCommand {

    protected final Map<String, CacheListener> handles = new HashMap<String, CacheListener>();

    // protected final KeyCommandUtils keyUtils = new KeyCommandUtils();
    // protected final StringCommandUtils strUtils = new StringCommandUtils();
    // protected final HashCommandUtils hashUtils = new HashCommandUtils();

    public void registerListener(String key, CacheListener l)
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

        // if (l instanceof KeyListener) {
        // keyUtils.registerListener(key, (KeyListener) l);
        // }
        // if (l instanceof StringListener) {
        // strUtils.registerListener(key, (StringListener) l);
        // }
        // if (l instanceof HashListener) {
        // hashUtils.registerListener(key, (HashListener) l);
        // }
    }

    // public void registerKeyListener(String key, KeyListener l)
    // throws CacheCodeException {
    // if (key == null || l == null || key.trim().equals(""))
    // throw new CacheCodeException(CacheExceptionEnum.参数异常);
    // keyUtils.registerListener(key, l);
    // }
    //
    // public void registerStringListener(String key, StringListener l)
    // throws CacheCodeException {
    // if (key == null || l == null || key.trim().equals(""))
    // throw new CacheCodeException(CacheExceptionEnum.参数异常);
    // strUtils.registerListener(key, l);
    // }
    //
    // public void registerHashListener(String key, HashListener l)
    // throws CacheCodeException {
    // if (key == null || l == null || key.trim().equals(""))
    // throw new CacheCodeException(CacheExceptionEnum.参数异常);
    // hashUtils.registerListener(key, l);
    // }

    public void unregisterListener(String key) throws CacheCodeException {
        if (key == null || key.trim().equals(""))
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        // keyUtils.unregisterListener(key);
        // strUtils.unregisterListener(key);
        // hashUtils.unregisterListener(key);

        if (handles.containsKey(key)) {
            handles.remove(key);
        }
    }

    // public void unregisterKeyListener(String key) throws CacheCodeException {
    // if (key == null || key.trim().equals(""))
    // throw new CacheCodeException(CacheExceptionEnum.参数异常);
    // keyUtils.unregisterListener(key);
    // }
    //
    // public void unregisterStringListener(String key) throws
    // CacheCodeException {
    // if (key == null || key.trim().equals(""))
    // throw new CacheCodeException(CacheExceptionEnum.参数异常);
    // strUtils.unregisterListener(key);
    // }
    //
    // public void unregisterHashListener(String key) throws CacheCodeException
    // {
    // if (key == null || key.trim().equals(""))
    // throw new CacheCodeException(CacheExceptionEnum.参数异常);
    // hashUtils.unregisterListener(key);
    // }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.redis.IStringCommand#set(java.
     * lang.String, java.io.Serializable)
     */
    @Override
    public <T extends Serializable> void set(String key, T data)
            throws CacheCodeException {
        this.set(key, data, null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.redis.IStringCommand#set(java.
     * lang.String, java.io.Serializable, java.lang.Integer)
     */
    @Override
    public <T extends Serializable> void set(String key, T data, Integer time)
            throws CacheCodeException {
        if (key == null || data == null || key.trim().equalsIgnoreCase("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        StringSetEvent e = new StringSetEvent(key, data, time, this);

        for (CacheListener l : handles.values()) {
            if (l instanceof StringListener) {
                if (((StringListener) l).handleEvent(e))
                    break;
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.redis.IStringCommand#get(java.
     * lang.String, java.lang.Class)
     */
    @Override
    public <T extends Serializable> T get(String key, Class<T> clazz)
            throws CacheCodeException {
        if (key == null || clazz == null || key.trim().equalsIgnoreCase("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        StringGetEvent<T> e = new StringGetEvent<T>(key, clazz, this);
        for (CacheListener l : handles.values()) {
            if (l instanceof StringListener) {
                if (((StringListener) l).handleEvent(e))
                    break;
            }
        }
        return e.result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.redis.IStringCommand#append(java
     * .lang.String, java.lang.String)
     */
    @Override
    public void append(String key, String appendStr) throws CacheCodeException {
        if (key == null || appendStr == null || key.trim().equalsIgnoreCase("")
                || appendStr.trim().equalsIgnoreCase(""))
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        StringAppendEvent e = new StringAppendEvent(key, appendStr, this);
        for (CacheListener l : handles.values()) {
            if (l instanceof StringListener) {
                if (((StringListener) l).handleEvent(e))
                    break;
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.redis.IStringCommand#decr(java
     * .lang.String)
     */
    @Override
    public void decr(String key) throws CacheCodeException {
        if (key == null || key.trim().equalsIgnoreCase("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        StringDecrEvent e = new StringDecrEvent(key, this);
        for (CacheListener l : handles.values()) {
            if (l instanceof StringListener) {
                if (((StringListener) l).handleEvent(e))
                    break;
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.redis.IStringCommand#decrBy(java
     * .lang.String, java.lang.Integer)
     */
    @Override
    public void decrBy(String key, Integer v) throws CacheCodeException {
        if (key == null || key.trim().equalsIgnoreCase("") || v == null
                || v.intValue() >= 0) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        StringDecrEvent e = new StringDecrEvent(key, v, this);
        for (CacheListener l : handles.values()) {
            if (l instanceof StringListener) {
                if (((StringListener) l).handleEvent(e))
                    break;
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.redis.IStringCommand#incr(java
     * .lang.String)
     */
    @Override
    public void incr(String key) throws CacheCodeException {
        if (key == null || key.trim().equalsIgnoreCase("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        StringIncrEvent e = new StringIncrEvent(key, this);
        for (CacheListener l : handles.values()) {
            if (l instanceof StringListener) {
                if (((StringListener) l).handleEvent(e))
                    break;
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.redis.IStringCommand#incrBy(java
     * .lang.String, java.lang.Integer)
     */
    @Override
    public void incrBy(String key, Integer v) throws CacheCodeException {
        if (key == null || key.trim().equalsIgnoreCase("") || v == null
                || v.intValue() <= 0) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        StringIncrEvent e = new StringIncrEvent(key, v, this);
        for (CacheListener l : handles.values()) {
            if (l instanceof StringListener) {
                if (((StringListener) l).handleEvent(e))
                    break;
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.redis.IKeyCommand#del(java.lang
     * .String)
     */
    @Override
    public Long del(String key) throws CacheCodeException {
        if (key == null || key.trim().equalsIgnoreCase("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        KeyDelEvent e = new KeyDelEvent(key, this);
        for (CacheListener l : handles.values()) {
            if (l instanceof KeyListener) {
                if (((KeyListener) l).handleEvent(e))
                    break;
            }
        }
        return e.result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.redis.IKeyCommand#exists(java.
     * lang.String)
     */
    @Override
    public Boolean exists(String key) throws CacheCodeException {
        if (key == null || key.trim().equalsIgnoreCase("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        KeyExistsEvent e = new KeyExistsEvent(key, this);
        for (CacheListener l : handles.values()) {
            if (l instanceof KeyListener) {
                if (((KeyListener) l).handleEvent(e))
                    break;
            }
        }
        return e.result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.redis.IKeyCommand#ttl(java.lang
     * .String)
     */
    @Override
    public Long ttl(String key) throws CacheCodeException {
        if (key == null || key.trim().equalsIgnoreCase("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        KeyTtlEvent e = new KeyTtlEvent(key, this);
        for (CacheListener l : handles.values()) {
            if (l instanceof KeyListener) {
                if (((KeyListener) l).handleEvent(e))
                    break;
            }
        }
        return e.result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.redis.IKeyCommand#expire(java.
     * lang.String, int)
     */
    @Override
    public Long expire(String key, int seconds) throws CacheCodeException {
        if (key == null || key.trim().equalsIgnoreCase("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        KeyExpirtEvent e = new KeyExpirtEvent(key, seconds, this);
        for (CacheListener l : handles.values()) {
            if (l instanceof KeyListener) {
                if (((KeyListener) l).handleEvent(e))
                    break;
            }
        }
        return e.result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.redis.IKeyCommand#expireAt(java
     * .lang.String, long)
     */
    @Override
    public Long expireAt(String key, long unixTime) throws CacheCodeException {
        if (key == null || key.trim().equalsIgnoreCase("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        KeyExpireAtEvent e = new KeyExpireAtEvent(key, unixTime, this);
        for (CacheListener l : handles.values()) {
            if (l instanceof KeyListener) {
                if (((KeyListener) l).handleEvent(e))
                    break;
            }
        }
        return e.result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.redis.IKeyCommand#type(java.lang
     * .String)
     */
    @Override
    public String type(String key) throws CacheCodeException {
        if (key == null || key.trim().equalsIgnoreCase("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        KeyTypeEvent e = new KeyTypeEvent(key, this);
        for (CacheListener l : handles.values()) {
            if (l instanceof KeyListener) {
                if (((KeyListener) l).handleEvent(e))
                    break;
            }
        }
        return e.result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.redis.IKeyCommand#scan(java.lang
     * .String)
     */
    @Override
    public void scan(String pettey) throws CacheCodeException {
        throw new CacheCodeException(CacheExceptionEnum.不支持的操作类型);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.redis.IHashCommand#exists(java
     * .lang.String, java.lang.String)
     */
    @Override
    public Boolean exists(String key, String field) throws CacheCodeException {
        if (key == null || field == null || key.trim().equalsIgnoreCase("")
                || field.trim().equalsIgnoreCase("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        HashExistsEvent e = new HashExistsEvent(key, field, this);
        for (CacheListener l : handles.values()) {
            if (l instanceof HashListener) {
                if (((HashListener) l).handleEvent(e))
                    break;
            }
        }
        return e.result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.redis.IHashCommand#get(java.lang
     * .String, java.lang.String, java.lang.Class)
     */
    @Override
    public <T extends Serializable> T get(String key, String field,
            Class<T> clazz) throws CacheCodeException {
        if (key == null || field == null || key.trim().equalsIgnoreCase("")
                || field.trim().equalsIgnoreCase("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        HashGetEvent<T> e = new HashGetEvent<T>(key, field, clazz, this);
        for (CacheListener l : handles.values()) {
            if (l instanceof HashListener) {
                if (((HashListener) l).handleEvent(e))
                    break;
            }
        }
        return e.result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.redis.IHashCommand#set(java.lang
     * .String, java.lang.String, java.io.Serializable)
     */
    @Override
    public <T extends Serializable> Long set(String key, String field, T data)
            throws CacheCodeException {
        if (key == null || data == null || key.trim().equalsIgnoreCase("")
                || field == null || field.trim().equalsIgnoreCase("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        HashSetEvent e = new HashSetEvent(key, field, data, this);
        for (CacheListener l : handles.values()) {
            if (l instanceof HashListener) {
                if (((HashListener) l).handleEvent(e))
                    break;
            }
        }
        return e.result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.redis.IHashCommand#del(java.lang
     * .String, java.lang.String)
     */
    @Override
    public Long del(String key, String field) throws CacheCodeException {
        if (key == null || key.trim().equalsIgnoreCase("") || field == null
                || field.trim().equalsIgnoreCase("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        HashDelEvent e = new HashDelEvent(key, field, this);
        for (CacheListener l : handles.values()) {
            if (l instanceof HashListener) {
                if (((HashListener) l).handleEvent(e))
                    break;
            }
        }
        return e.result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.redis.IHashCommand#incrBy(java
     * .lang.String, java.lang.String, long)
     */
    @Override
    public Long incrBy(String key, String field, long v)
            throws CacheCodeException {
        if (key == null || key.trim().equalsIgnoreCase("") || field == null
                || field.trim().equalsIgnoreCase("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        HashIncrByEvent e = new HashIncrByEvent(key, field, v, this);
        for (CacheListener l : handles.values()) {
            if (l instanceof HashListener) {
                if (((HashListener) l).handleEvent(e))
                    break;
            }
        }
        return e.result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.redis.IHashCommand#incrByFloat
     * (java.lang.String, java.lang.String, double)
     */
    @Override
    public Double incrByFloat(String key, String field, double v)
            throws CacheCodeException {
        if (key == null || key.trim().equalsIgnoreCase("") || field == null
                || field.trim().equalsIgnoreCase("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        HashIncrByFloatEvent e = new HashIncrByFloatEvent(key, field, v, this);
        for (CacheListener l : handles.values()) {
            if (l instanceof HashListener) {
                if (((HashListener) l).handleEvent(e))
                    break;
            }
        }
        return e.result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.redis.IHashCommand#len(java.lang
     * .String)
     */
    @Override
    public Long len(String key) throws CacheCodeException {
        if (key == null || key.trim().equalsIgnoreCase("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        HashLenEvent e = new HashLenEvent(key, this);
        for (CacheListener l : handles.values()) {
            if (l instanceof HashListener) {
                if (((HashListener) l).handleEvent(e))
                    break;
            }
        }
        return e.result;
    }

}
