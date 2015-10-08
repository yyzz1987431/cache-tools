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
public class HashLenEvent extends RedisBaseEvent {

    public Long result;

    /**
     * @param source
     */
    public HashLenEvent(String key, Object source) {
        super(key, source);
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
