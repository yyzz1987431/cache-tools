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
public class HashIncrByFloatEvent extends RedisBaseEvent {

    public final String field;
    public final double v;
    public Double result;

    /**
     * @param source
     */
    public HashIncrByFloatEvent(String key, String f, double v, Object source) {
        super(key, source);
        this.field = f;
        this.v = v;
    }

}
