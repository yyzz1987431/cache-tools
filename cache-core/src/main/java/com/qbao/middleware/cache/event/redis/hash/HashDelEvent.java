/**
 * HashExistsEvent.java
 */
package com.qbao.middleware.cache.event.redis.hash;

import com.qbao.middleware.cache.event.redis.RedisBaseEvent;

/**
 * @author Yate
 * @date Sep 23, 2015
 * @description TODO
 * @version 1.0
 */
public class HashDelEvent extends RedisBaseEvent {

    public final String field;
    public Long result;

    /**
     * @param source
     */
    public HashDelEvent(String key, String field, Object source) {
        super(key, source);
        this.field = field;
    }

}
