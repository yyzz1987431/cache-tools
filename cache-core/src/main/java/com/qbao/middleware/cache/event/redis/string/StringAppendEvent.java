/**
 * GetOptEvent.java
 */
package com.qbao.middleware.cache.event.redis.string;

import com.qbao.middleware.cache.event.redis.RedisBaseEvent;

/**
 * @author Yate
 * @date Sep 17, 2015
 * @description TODO
 * @version 1.0
 */
public class StringAppendEvent extends RedisBaseEvent {

    public final String appendValue;

    /**
     * @param source
     */
    public StringAppendEvent(String key, String appendValue, Object source) {
        super(key, source);
        this.appendValue = appendValue;
    }

}
