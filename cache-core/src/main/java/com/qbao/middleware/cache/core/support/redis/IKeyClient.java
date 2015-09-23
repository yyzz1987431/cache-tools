/**
 * IKeyClient.java
 */
package com.qbao.middleware.cache.core.support.redis;

import com.qbao.middleware.cache.core.ICacheClient;
import com.qbao.middleware.cache.exception.CacheCodeException;

/**
 * @author Yate
 * @date Sep 23, 2015
 * @description TODO
 * @version 1.0
 */
public interface IKeyClient extends ICacheClient {
    // Key（键）
    // DUMP
    // EXISTS
    // MIGRATE
    // MOVE
    // OBJECT
    // PERSIST
    // PEXPIRE
    // PEXPIREAT
    // PTTL
    // RANDOMKEY
    // RENAME
    // RENAMENX
    // RESTORE
    // SORT

    void del(String key) throws CacheCodeException;

    void exists(String key) throws CacheCodeException;

    void ttl(String key) throws CacheCodeException;

    void expire(String key) throws CacheCodeException;

    void expireAt(String key) throws CacheCodeException;

    void type(String key) throws CacheCodeException;

    void scan(String pettey) throws CacheCodeException;

}
