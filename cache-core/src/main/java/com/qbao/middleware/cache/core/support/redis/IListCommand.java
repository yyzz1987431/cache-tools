/**
 * IListClient.java
 */
package com.qbao.middleware.cache.core.support.redis;

import java.util.List;

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

    List<String> bLPop(String key, int timeOut) throws CacheCodeException;

    List<String> bLPop(String[] keys, int timeOut) throws CacheCodeException;

    List<String> bRPop(String key, int timeOut) throws CacheCodeException;

    List<String> bRPop(String[] keys, int timeOut) throws CacheCodeException;

    String bRPopLpush(String key, String srcKey, String destKey, int timeout)
            throws CacheCodeException;

    String lindex(String key, long index) throws CacheCodeException;

    Long lLen(String key) throws CacheCodeException;

    String lPop(String key) throws CacheCodeException;

    String rPop(String key) throws CacheCodeException;

    Long lPush(String key, String... values) throws CacheCodeException;

    Long rPush(String key, String... values) throws CacheCodeException;

    void lInsert() throws CacheCodeException;

    String rPopLPush(String key, String srcKey, String destKey)
            throws CacheCodeException;
}
