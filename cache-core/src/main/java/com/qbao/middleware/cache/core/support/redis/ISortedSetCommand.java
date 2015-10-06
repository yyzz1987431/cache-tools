/**
 * IHashClient.java
 */
package com.qbao.middleware.cache.core.support.redis;

import com.qbao.middleware.cache.core.ICacheCommand;
import com.qbao.middleware.cache.exception.CacheCodeException;

/**
 * @author Yate
 * @date Sep 23, 2015
 * @description TODO
 * @version 1.0
 */
public interface ISortedSetCommand extends ICacheCommand {
    // Set（集合）
    // SADD
    // SCARD
    // SDIFF
    // SDIFFSTORE
    // SINTER
    // SINTERSTORE
    // SISMEMBER
    // SMEMBERS
    // SMOVE
    // SPOP
    // SRANDMEMBER
    // SREM
    // SUNION
    // SUNIONSTORE
    // SSCAN

    public void zAdd() throws CacheCodeException;

    public void zCard() throws CacheCodeException;

    public void zCount() throws CacheCodeException;

    public void zIncrBy() throws CacheCodeException;

    public void zRange() throws CacheCodeException;

    public void zRangeByScore() throws CacheCodeException;

    public void zRank() throws CacheCodeException;

    public void zRem() throws CacheCodeException;

    public void zRemRangeByRank() throws CacheCodeException;

    public void zRemRangeByScore() throws CacheCodeException;

    public void zRevRange() throws CacheCodeException;

    public void zRevRangeByScore() throws CacheCodeException;

    public void zRevRank() throws CacheCodeException;

    public void zScore() throws CacheCodeException;

    public void zUnionStore() throws CacheCodeException;

    public void zInterStore() throws CacheCodeException;

    public void zScan() throws CacheCodeException;

    public void zRangeByLex() throws CacheCodeException;

    public void zLexCount() throws CacheCodeException;

    public void zRemRangeByLex() throws CacheCodeException;
}
