/**
 * GetOptEvent.java
 */
package com.qbao.middleware.cache.event.redis.string;

import com.qbao.middleware.cache.event.redis.RedisBaseEvent;
import com.qbao.middleware.cache.listener.StringListener;
import com.qbao.middleware.cache.listerner.CacheListener;

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

    public void handle(CacheListener... ls) {
        for (CacheListener l : ls) {
            if (l instanceof StringListener) {
                if (((StringListener) l).handleEvent(this))
                    break;
            }
        }
    }
}
