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
public class HashExistsEvent extends RedisBaseEvent {

    public final String field;
    public Boolean result;

    /**
     * @param source
     */
    public HashExistsEvent(String key, String field, Object source) {
        super(key, source);
        this.field = field;
    }

}
