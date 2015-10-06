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
public class ListRemEvent extends RedisBaseEvent {
    public final long count;
    public final String value;
    public Long result;
    /**
     * @param key
     * @param source
     */
    public ListRemEvent(String key, long count,String v,Object source) {
        super(key, source);
        this.count = count;
        this.value = v;
    }

}
