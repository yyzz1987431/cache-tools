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
public class KeyDelEvent extends RedisBaseEvent {

    public Long result;
    /**
     * @param key
     * @param source
     */
    public KeyDelEvent(String key, Object source) {
        super(key, source);
    }

}
