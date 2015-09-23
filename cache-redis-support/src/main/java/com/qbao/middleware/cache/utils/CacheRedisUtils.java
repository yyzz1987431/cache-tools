/**
 * CacheRedisUtils.java
 */
package com.qbao.middleware.cache.utils;

import java.io.Serializable;

import com.qbao.middleware.cache.core.support.redis.IHashCommand;
import com.qbao.middleware.cache.core.support.redis.IKeyCommand;
import com.qbao.middleware.cache.core.support.redis.IStringCommand;
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

    protected final KeyCommandUtils keyUtils = new KeyCommandUtils();
    protected final StringCommandUtils strUtils = new StringCommandUtils();
    protected final HashCommandUtils hashUtils = new HashCommandUtils();

    public void registerListener(String key, CacheListener l)
            throws CacheCodeException {
        if (key == null || l == null || key.trim().equals(""))
            throw new CacheCodeException(CacheExceptionEnum.参数异常);

        if (l instanceof KeyListener) {
            keyUtils.registerListener(key, (KeyListener) l);
        }
        if (l instanceof StringListener) {
            strUtils.registerListener(key, (StringListener) l);
        }
        if (l instanceof HashListener) {
            hashUtils.registerListener(key, (HashListener) l);
        }
    }

    public void registerKeyListener(String key, KeyListener l)
            throws CacheCodeException {
        if (key == null || l == null || key.trim().equals(""))
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        keyUtils.registerListener(key, l);
    }

    public void registerStringListener(String key, StringListener l)
            throws CacheCodeException {
        if (key == null || l == null || key.trim().equals(""))
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        strUtils.registerListener(key, l);
    }

    public void registerHashListener(String key, HashListener l)
            throws CacheCodeException {
        if (key == null || l == null || key.trim().equals(""))
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        hashUtils.registerListener(key, l);
    }

    public void unregisterListener(String key) throws CacheCodeException {
        if (key == null || key.trim().equals(""))
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        keyUtils.unregisterListener(key);
        strUtils.unregisterListener(key);
        hashUtils.unregisterListener(key);
    }

    public void unregisterKeyListener(String key) throws CacheCodeException {
        if (key == null || key.trim().equals(""))
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        keyUtils.unregisterListener(key);
    }

    public void unregisterStringListener(String key) throws CacheCodeException {
        if (key == null || key.trim().equals(""))
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        strUtils.unregisterListener(key);
    }

    public void unregisterHashListener(String key) throws CacheCodeException {
        if (key == null || key.trim().equals(""))
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        hashUtils.unregisterListener(key);
    }

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
        this.strUtils.set(key, data);
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
        return null;
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
        return this.keyUtils.del(key);
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
        return null;
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
        return null;
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
        return null;
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
        return null;
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
        return null;
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
        return null;
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
        return null;
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
        return null;
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
        return null;
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
        return null;
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
        return null;
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
        return null;
    }

}
