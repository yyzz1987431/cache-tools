/**
 * IHashClient.java
 */
package com.qbao.middleware.cache.core.support.redis;

import java.io.Serializable;

import com.qbao.middleware.cache.core.ICacheCommand;
import com.qbao.middleware.cache.exception.CacheCodeException;

/**
 * @author Yate
 * @date Sep 23, 2015
 * @description TODO
 * @version 1.0
 */
public interface IHashCommand extends ICacheCommand {

    // Hash（哈希表）
    // HGETALL
    // HKEYS
    // HMGET
    // HMSET
    // HSETNX
    // HVALS
    // HSCAN

    Boolean hexists(String key, String field) throws CacheCodeException;

    <T extends Serializable> T hget(String key, String field, Class<T> clazz)
            throws CacheCodeException;

    <T extends Serializable> Long hset(String key, String field, T data)
            throws CacheCodeException;

    Long hdel(String key, String field) throws CacheCodeException;

    Long hincrBy(String key, String field, long v) throws CacheCodeException;

    Double hincrByFloat(String key, String field, double v)
            throws CacheCodeException;

    Long hlen(String key) throws CacheCodeException;
}
