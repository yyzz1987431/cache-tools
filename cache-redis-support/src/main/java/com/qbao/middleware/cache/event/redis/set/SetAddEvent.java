/**
 * SetAddEvent.java
 */
package com.qbao.middleware.cache.event.redis.set;

import com.qbao.middleware.cache.event.redis.RedisBaseEvent;

/**
 * @author Yate
 * @date Oct 8, 2015
 * @description TODO
 * @version 1.0
 */
public class SetAddEvent extends RedisBaseEvent {

    /**
     * @param key
     * @param source
     */
    public SetAddEvent(String key, Object source) {
        super(key, source);
    }

}
