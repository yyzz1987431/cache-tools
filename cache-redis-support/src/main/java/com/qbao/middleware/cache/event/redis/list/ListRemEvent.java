/**
 * ListBlockLPopEvent.java
 */
package com.qbao.middleware.cache.event.redis.list;

import com.qbao.middleware.cache.event.redis.RedisBaseEvent;
import com.qbao.middleware.cache.listener.ListListener;
import com.qbao.middleware.cache.listerner.CacheListener;

/**
 * @author Yate
 * @date Sep 27, 2015
 * @description TODO
 * @version 1.0
 */
public class ListRemEvent extends RedisBaseEvent {
    public final long count;
    public final String value;
    public Long result;

    /**
     * @param key
     * @param source
     */
    public ListRemEvent(String key, long count, String v, Object source) {
        super(key, source);
        this.count = count;
        this.value = v;
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
