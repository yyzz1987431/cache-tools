/**
 * KeyDelEvent.java
 */
package com.qbao.middleware.cache.event.redis.key;

import com.qbao.middleware.cache.event.redis.RedisBaseEvent;
import com.qbao.middleware.cache.listener.KeyListener;
import com.qbao.middleware.cache.listerner.CacheListener;

/**
 * @author Yate
 * @date Sep 23, 2015
 * @description TODO
 * @version 1.0
 */
public class KeyScanEvent extends RedisBaseEvent {

    /**
     * @param key
     * @param source
     */
    public KeyScanEvent(String key, Object source) {
        super(key, source);
    }

    public void handle(CacheListener... ls) {
        for (CacheListener l : ls) {
            if (l instanceof KeyListener) {
                if (((KeyListener) l).handleEvent(this))
                    break;
            }
        }
    }
}
