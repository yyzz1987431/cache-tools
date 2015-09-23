/**
 * IHashClient.java
 */
package com.qbao.middleware.cache.core.support.redis;

import java.io.Serializable;

import com.qbao.middleware.cache.core.ICacheClient;
import com.qbao.middleware.cache.exception.CacheCodeException;

/**
 * @author Yate
 * @date Sep 23, 2015
 * @description TODO
 * @version 1.0
 */
public interface IHashClient extends ICacheClient {

    // Hash（哈希表）
    // HGETALL
    // HKEYS
    // HMGET
    // HMSET
    // HSETNX
    // HVALS
    // HSCAN

    Boolean exists(String key, String field) throws CacheCodeException;

    <T extends Serializable> T get(String key, String field, Class<T> clazz)
            throws CacheCodeException;

    <T extends Serializable> Long set(String key, String field, T data)
            throws CacheCodeException;

    Long del(String key, String field) throws CacheCodeException;

    Long incrBy(String key, String field, long v) throws CacheCodeException;

    Double incrByFloat(String key, String field, double v)
            throws CacheCodeException;

    Long len(String key) throws CacheCodeException;
}
