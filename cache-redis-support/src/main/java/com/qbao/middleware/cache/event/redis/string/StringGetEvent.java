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
public class StringGetEvent<T> extends RedisBaseEvent {

    public Class<T> targetClass;
    public T result;

    /**
     * @param source
     */
    public StringGetEvent(String key, Class<T> clazz, Object source) {
        super(key, source);
        this.targetClass = clazz;
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
