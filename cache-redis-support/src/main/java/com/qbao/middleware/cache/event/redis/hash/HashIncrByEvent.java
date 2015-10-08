/**
 * HashExistsEvent.java
 */
package com.qbao.middleware.cache.event.redis.hash;

import com.qbao.middleware.cache.event.redis.RedisBaseEvent;
import com.qbao.middleware.cache.listener.HashListener;
import com.qbao.middleware.cache.listerner.CacheListener;

/**
 * @author Yate
 * @date Sep 23, 2015
 * @description TODO
 * @version 1.0
 */
public class HashIncrByEvent extends RedisBaseEvent {

    public final String field;
    public final long v;
    public Long result;

    /**
     * @param source
     */
    public HashIncrByEvent(String key, String f, long v, Object source) {
        super(key, source);
        this.field = f;
        this.v = v;
    }

    /* 
     * (non-Javadoc)
     * @see com.qbao.middleware.cache.event.redis.RedisBaseEvent#handle(com.qbao.middleware.cache.listerner.CacheListener[])
     */
    @Override
    public void handle(CacheListener... ls) {
        for (CacheListener l : ls) {
            if (l instanceof HashListener) {
                if (((HashListener) l).handleEvent(this))
                    break;
            }
        }
    }

}
