/**
 * RedisBaseEvent.java
 */
package com.qbao.middleware.cache.event.redis;

import java.util.EventObject;

import com.qbao.middleware.cache.listerner.CacheListener;

/**
 * @author Yate
 * @date Sep 23, 2015
 * @description TODO
 * @version 1.0
 */
public abstract class RedisBaseEvent extends EventObject {

    public final String key;

    /**
     * @param source
     */
    public RedisBaseEvent(String key, Object source) {
        super(source);
        this.key = key;
    }

    public abstract void handle(CacheListener... ls);

}
