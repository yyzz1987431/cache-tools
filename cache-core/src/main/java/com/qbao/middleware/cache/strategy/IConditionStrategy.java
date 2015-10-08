/**
 * IConditionStrategy.java
 */
package com.qbao.middleware.cache.strategy;

import com.qbao.middleware.cache.event.redis.RedisBaseEvent;
import com.qbao.middleware.cache.listerner.CacheListener;

/**
 * @author Yate
 * @date Dec 29, 2015
 * @description TODO
 * @version 1.0
 */
public interface IConditionStrategy {

    public boolean exceute(RedisBaseEvent event, CacheListener... ls);
}
