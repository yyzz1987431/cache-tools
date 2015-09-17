/**
 * IKeyValueCache.java
 */
package com.qbao.middleware.cache.core.support;

import java.io.Serializable;

import com.qbao.middleware.cache.core.ICacheClient;
import com.qbao.middleware.cache.exception.CacheCodeException;

/**
 * @author Yate
 * @date Sep 17, 2015
 * @description TODO
 * @version 1.0
 */
public interface IKeyValueClient extends ICacheClient {

    <T extends Serializable> void set(String key, T data) throws CacheCodeException;

    <T extends Serializable> void set(String key, T data, Integer time) throws CacheCodeException;

    <T extends Serializable> T get(String key, Class<T> clazz) throws CacheCodeException;
}
