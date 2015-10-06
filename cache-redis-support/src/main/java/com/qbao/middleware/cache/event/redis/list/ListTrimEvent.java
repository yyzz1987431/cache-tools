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
public class ListTrimEvent extends RedisBaseEvent {
    public final long start, end;
    public String result;

    /**
     * @param key
     * @param source
     */
    public ListTrimEvent(String key, long start, long end, Object source) {
        super(key, source);
        this.start = start;
        this.end = end;
    }

}
