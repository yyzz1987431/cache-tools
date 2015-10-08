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
public class ListBlockLPopEvent extends RedisBaseEvent {

    public final String[] keys;
    public final int timeOut;
    public List<String> result;

    /**
     * @param key
     * @param source
     */
    public ListBlockLPopEvent(int timeOut, Object source, String... keys) {
        super(keys[0],source);
        this.keys = keys;
        this.timeOut = timeOut;
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
