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
public class ListRPushEvent extends RedisBaseEvent {

    public final String[] values;
    public Long result;
    /**
     * @param key
     * @param source
     */
    public ListRPushEvent(String key,String[] v, Object source) {
        super(key, source);
        this.values = v;
    }

}
