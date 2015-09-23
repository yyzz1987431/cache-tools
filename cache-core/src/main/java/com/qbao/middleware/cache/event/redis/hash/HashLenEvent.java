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
public class HashLenEvent extends RedisBaseEvent {

    public Long result;

    /**
     * @param source
     */
    public HashLenEvent(String key, Object source) {
        super(key, source);
    }

}
