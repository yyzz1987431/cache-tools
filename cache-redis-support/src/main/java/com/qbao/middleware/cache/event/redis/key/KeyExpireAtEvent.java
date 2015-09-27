/**
 * KeyDelEvent.java
 */
package com.qbao.middleware.cache.event.redis.key;

import com.qbao.middleware.cache.event.redis.RedisBaseEvent;

/**
 * @author Yate
 * @date Sep 23, 2015
 * @description TODO
 * @version 1.0
 */
public class KeyExpireAtEvent extends RedisBaseEvent {

    public final long unixTime;
    public Long result;
    
    /**
     * @param key
     * @param source
     */
    public KeyExpireAtEvent(String key,long unixTime, Object source) {
        super(key, source);
        this.unixTime = unixTime;
    }

}
