/**
 * AllHandleStrategy.java
 */
package com.qbao.middleware.cache.strategy;

import com.qbao.middleware.cache.event.redis.RedisBaseEvent;
import com.qbao.middleware.cache.listerner.CacheListener;

/**
 * @author Yate
 * @date Dec 29, 2015
 * @description 所有Handle都进行处理
 * @version 1.0
 */
public class AllHandleStrategy implements IConditionStrategy {

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.strategy.IConditionStrategy#exceute(com.qbao
     * .middleware.cache.listerner.CacheListener[])
     */
    @Override
    public boolean exceute(RedisBaseEvent event, CacheListener... ls) {
        event.handle(ls);
        return true;
    }

}
