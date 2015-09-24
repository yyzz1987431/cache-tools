/**
 * IListClient.java
 */
package com.qbao.middleware.cache.core.support.redis;

import com.qbao.middleware.cache.core.ICacheCommand;
import com.qbao.middleware.cache.exception.CacheCodeException;

/**
 * @author Yate
 * @date Sep 17, 2015
 * @description TODO
 * @version 1.0
 */
public interface IListCommand extends ICacheCommand {

    // List（列表）
    // BRPOPLPUSH
    // LINSERT
    // LPUSHX
    // LRANGE
    // LREM
    // LSET
    // LTRIM
    // RPUSHX

    void bLPop(String key, int timeOut) throws CacheCodeException;

    void bLPop(String[] keys, int timeOut) throws CacheCodeException;

    void bRPop(String key, int timeOut) throws CacheCodeException;

    void bRPop(String[] keys, int timeOut) throws CacheCodeException;

    void bRPopLpush() throws CacheCodeException;

    void lindex() throws CacheCodeException;

    void lLen() throws CacheCodeException;

    void lPop() throws CacheCodeException;

    void rPop() throws CacheCodeException;

    void lPush() throws CacheCodeException;

    void rPush() throws CacheCodeException;

    void lInsert() throws CacheCodeException;

    void rPopLPush() throws CacheCodeException;
}
