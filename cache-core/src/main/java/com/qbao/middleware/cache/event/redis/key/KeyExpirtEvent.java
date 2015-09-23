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
public class KeyExpirtEvent extends RedisBaseEvent {

    public final int seconds;
    public Long result;
    /**
     * @param key
     * @param source
     */
    public KeyExpirtEvent(String key, int seconds,Object source) {
        super(key, source);
        this.seconds = seconds;
    }

}
