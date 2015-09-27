/**
 * IncrOptEvent.java
 */
package com.qbao.middleware.cache.event.redis.string;

import com.qbao.middleware.cache.event.redis.RedisBaseEvent;

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
}
