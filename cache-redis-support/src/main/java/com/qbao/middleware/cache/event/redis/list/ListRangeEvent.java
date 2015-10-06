/**
 * ListBlockLPopEvent.java
 */
package com.qbao.middleware.cache.event.redis.list;

import java.util.List;

import com.qbao.middleware.cache.event.redis.RedisBaseEvent;

/**
 * @author Yate
 * @date Sep 27, 2015
 * @description TODO
 * @version 1.0
 */
public class ListRangeEvent extends RedisBaseEvent {

    public final long start, end;
    public  List<String> result;

    /**
     * @param key
     * @param source
     */
    public ListRangeEvent(String key, long start, long end, Object source) {
        super(key, source);
        this.start = start;
        this.end = end;
    }

}
