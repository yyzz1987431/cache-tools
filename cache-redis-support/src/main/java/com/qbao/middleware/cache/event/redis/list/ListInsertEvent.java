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
public class ListInsertEvent extends RedisBaseEvent {

    /**
     * @param key
     * @param source
     */
    public ListInsertEvent(String key, Object source) {
        super(key, source);
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
