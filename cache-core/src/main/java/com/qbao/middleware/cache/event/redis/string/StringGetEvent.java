/**
 * GetOptEvent.java
 */
package com.qbao.middleware.cache.event.redis.string;

import com.qbao.middleware.cache.event.redis.RedisBaseEvent;

/**
 * @author Yate
 * @date Sep 17, 2015
 * @description TODO
 * @version 1.0
 */
public class StringGetEvent<T> extends RedisBaseEvent {

    public Class<T> targetClass;
    public T result;

    /**
     * @param source
     */
    public StringGetEvent(String key, Class<T> clazz, Object source) {
        super(key, source);
        this.targetClass = clazz;
    }

}
