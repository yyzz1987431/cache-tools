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
public class ListRPopLPushEvent extends RedisBaseEvent {
    public final String srckey, dstkey;
    public String result;

    /**
     * @param key
     * @param source
     */
    public ListRPopLPushEvent(String key, String srckey, String dstkey,
            Object source) {
        super(key, source);
        this.srckey = srckey;
        this.dstkey = dstkey;
    }

}
