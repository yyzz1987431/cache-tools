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

}
