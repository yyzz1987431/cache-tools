/**
 * ListBlockLPopEvent.java
 */
package com.qbao.middleware.cache.event.redis.list;

import java.util.List;

import com.qbao.middleware.cache.event.redis.RedisBaseEvent;
import com.qbao.middleware.cache.listener.ListListener;
import com.qbao.middleware.cache.listerner.CacheListener;

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

    public void handle(CacheListener... ls) {
        for (CacheListener l : ls) {
            if (l instanceof ListListener) {
                if (((ListListener) l).handleEvent(this))
                    break;
            }
        }
    }
}
