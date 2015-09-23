/**
 * IListClient.java
 */
package com.qbao.middleware.cache.core.support.redis;

import java.io.Serializable;

import com.qbao.middleware.cache.core.ICacheClient;
import com.qbao.middleware.cache.exception.CacheCodeException;

/**
 * @author Yate
 * @date Sep 17, 2015
 * @description TODO
 * @version 1.0
 */
public interface IListClient extends ICacheClient {

    <T extends Serializable> void set(String key, Integer index, T data)
            throws CacheCodeException;

    <T extends Serializable> void pop(String key, Class<T> clazz)
            throws CacheCodeException;

    // LPUSH key value [value ...]
    // <T extends Serializable> T lPush(String key, Class<T> clazz) throws
    // CacheCodeException;
}
