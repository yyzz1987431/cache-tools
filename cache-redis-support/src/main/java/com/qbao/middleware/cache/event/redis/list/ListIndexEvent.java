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
public class ListIndexEvent extends RedisBaseEvent {

    public final long index;
    public String result;
    /**
     * @param key
     * @param source
     */
    public ListIndexEvent(String key, long idx,Object source) {
        super(key, source);
        this.index = idx;
    }

}
