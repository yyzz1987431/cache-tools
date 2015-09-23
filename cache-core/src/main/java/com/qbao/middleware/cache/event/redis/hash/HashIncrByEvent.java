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
public class HashIncrByEvent extends RedisBaseEvent {

    public final String field;
    public final long v;
    public Long result;

    /**
     * @param source
     */
    public HashIncrByEvent(String key, String f, long v, Object source) {
        super(key, source);
        this.field = f;
        this.v = v;
    }

}
