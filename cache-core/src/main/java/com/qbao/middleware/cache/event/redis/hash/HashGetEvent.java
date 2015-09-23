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
public class HashGetEvent<T> extends RedisBaseEvent {

    public final String field;
    public final Class<T> targetClass;
    public T result;

    /**
     * @param source
     */
    public HashGetEvent(String key, String field, Class<T> targetClass,
            Object source) {
        super(key, source);
        this.field = field;
        this.targetClass = targetClass;
    }

}
