/**
 * INumberMathClient.java
 */
package com.qbao.middleware.cache.core.support;

import com.qbao.middleware.cache.core.ICacheClient;
import com.qbao.middleware.cache.exception.CacheCodeException;

/**
 * @author Yate
 * @date Sep 23, 2015
 * @description TODO
 * @version 1.0
 */
public interface INumberMathClient extends ICacheClient {

    void decr(String key) throws CacheCodeException;

    void decrBy(String key, Integer v) throws CacheCodeException;

    void incr(String key) throws CacheCodeException;

    void incrBy(String key, Integer v) throws CacheCodeException;
}
