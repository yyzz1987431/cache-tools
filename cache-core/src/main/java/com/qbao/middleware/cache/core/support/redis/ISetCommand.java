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
public interface ISetCommand extends ICacheCommand {
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

    public void sAdd() throws CacheCodeException;

    public void sCard() throws CacheCodeException;

    public void sDiff() throws CacheCodeException;

    public void sDiffStore() throws CacheCodeException;

    public void sInter() throws CacheCodeException;

    public void sInterStore() throws CacheCodeException;

    public void sIsMember() throws CacheCodeException;

    public void sMembers() throws CacheCodeException;

    public void sMove() throws CacheCodeException;

    public void sPop() throws CacheCodeException;

    public void sRandMember() throws CacheCodeException;

    public void sRem() throws CacheCodeException;

    public void sUnion() throws CacheCodeException;

    public void sUnionStore() throws CacheCodeException;

    public void sScan() throws CacheCodeException;

}
