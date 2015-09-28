/**
 * ListBlockLPopEvent.java
 */
package com.qbao.middleware.cache.event.redis.list;

import com.qbao.middleware.cache.event.redis.RedisBaseEvent;

/**
 * @author Yate
 * @date Sep 27, 2015
 * @description TODO
 * @version 1.0
 */
public class ListPushEvent extends RedisBaseEvent {

    /**
     * @param key
     * @param source
     */
    public ListPushEvent(String key, Object source) {
        super(key, source);
    }

}