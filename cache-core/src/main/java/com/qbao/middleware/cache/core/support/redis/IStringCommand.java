/**
 * IKeyValueCache.java
 */
package com.qbao.middleware.cache.core.support.redis;

import java.io.Serializable;

import com.qbao.middleware.cache.core.ICacheCommand;
import com.qbao.middleware.cache.exception.CacheCodeException;

/**
 * @author Yate
 * @date Sep 17, 2015
 * @description TODO
 * @version 1.0
 */
public interface IStringCommand extends ICacheCommand {

    // String（字符串）
    // BITCOUNT
    // BITOP
    // GETBIT
    // GETRANGE
    // GETSET
    // INCRBYFLOAT
    // MGET
    // MSET
    // MSETNX
    // PSETEX
    // SETBIT
    // SETEX
    // SETNX
    // SETRANGE
    // STRLEN

    <T extends Serializable> void set(String key, T data)
            throws CacheCodeException;

    <T extends Serializable> void set(String key, T data, Integer time)
            throws CacheCodeException;

    <T extends Serializable> T get(String key, Class<T> clazz)
            throws CacheCodeException;

    void append(String key, String appendStr) throws CacheCodeException;

    void decr(String key) throws CacheCodeException;

    void decrBy(String key, Integer v) throws CacheCodeException;

    void incr(String key) throws CacheCodeException;

    void incrBy(String key, Integer v) throws CacheCodeException;
}
