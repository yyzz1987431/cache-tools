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
public class HashSetEvent extends RedisBaseEvent {

    public final String field;
    public final Object data;
    public Long result;

    /**
     * @param source
     */
    public HashSetEvent(String key, String field, Object d, Object source) {
        super(key, source);
        this.field = field;
        this.data = d;
    }

}
