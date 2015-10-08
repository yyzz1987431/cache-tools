/**
 * SetAddEvent.java
 */
package com.qbao.middleware.cache.event.redis.set;

import com.qbao.middleware.cache.event.redis.RedisBaseEvent;
import com.qbao.middleware.cache.listener.SetListener;
import com.qbao.middleware.cache.listerner.CacheListener;

/**
 * @author Yate
 * @date Oct 8, 2015
 * @description TODO
 * @version 1.0
 */
public class SetDiffStoreEvent extends RedisBaseEvent {

    /**
     * @param key
     * @param source
     */
    public SetDiffStoreEvent(String key, Object source) {
        super(key, source);
    }

    public void handle(CacheListener... ls) {
        for (CacheListener l : ls) {
            if (l instanceof SetListener) {
                if (((SetListener) l).handleEvent(this))
                    break;
            }
        }
    }
}
