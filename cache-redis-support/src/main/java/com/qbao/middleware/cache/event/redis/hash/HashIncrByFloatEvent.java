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
public class HashIncrByFloatEvent extends RedisBaseEvent {

    public final String field;
    public final double v;
    public Double result;

    /**
     * @param source
     */
    public HashIncrByFloatEvent(String key, String f, double v, Object source) {
        super(key, source);
        this.field = f;
        this.v = v;
    }

    public void handle(CacheListener... ls) {
        for (CacheListener l : ls) {
            if (l instanceof HashListener) {
                if (((HashListener) l).handleEvent(this))
                    break;
            }
        }
    }
}
