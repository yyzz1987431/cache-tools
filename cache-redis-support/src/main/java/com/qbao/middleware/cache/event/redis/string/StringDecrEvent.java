/**
 * IncrOptEvent.java
 */
package com.qbao.middleware.cache.event.redis.string;

import com.qbao.middleware.cache.event.redis.RedisBaseEvent;
import com.qbao.middleware.cache.listener.StringListener;
import com.qbao.middleware.cache.listerner.CacheListener;

/**
 * @author Yate
 * @date Sep 23, 2015
 * @description TODO
 * @version 1.0
 */
public class StringDecrEvent extends RedisBaseEvent {

    public final long value;

    /**
     * @param source
     */
    public StringDecrEvent(String key, Object source) {
        this(key, -1l, source);
    }

    public StringDecrEvent(String key, long value, Object source) {
        super(key, source);
        this.value = value;
    }
    
    public void handle(CacheListener... ls) {
        for (CacheListener l : ls) {
            if (l instanceof StringListener) {
                if (((StringListener) l).handleEvent(this))
                    break;
            }
        }
    }
}
