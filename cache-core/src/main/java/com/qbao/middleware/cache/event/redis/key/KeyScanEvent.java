/**
 * KeyDelEvent.java
 */
package com.qbao.middleware.cache.event.redis.key;

import com.qbao.middleware.cache.event.redis.RedisBaseEvent;

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

}
