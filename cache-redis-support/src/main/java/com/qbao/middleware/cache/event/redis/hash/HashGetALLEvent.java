/**
 * ListGetALLEvent.java
 */
package com.qbao.middleware.cache.event.redis.hash;

import java.util.HashMap;
import java.util.Map;

import com.qbao.middleware.cache.event.redis.RedisBaseEvent;
import com.qbao.middleware.cache.listener.HashListener;
import com.qbao.middleware.cache.listerner.CacheListener;

/**
 * @author Yate
 * @date Mar 31, 2016
 * @description TODO
 * @version 1.0
 */
public class HashGetALLEvent extends RedisBaseEvent {

    public Map<String, Object> result = new HashMap<String, Object>();
    public final Class<?> clazz;

    /**
     * @param key
     * @param source
     */
    public HashGetALLEvent(String key, Object source, Class<?> valClass) {
        super(key, source);
        this.clazz = valClass;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.event.redis.RedisBaseEvent#handle(com.qbao.
     * middleware.cache.listerner.CacheListener[])
     */
    @Override
    public void handle(CacheListener... ls) {
        for (CacheListener l : ls) {
            if (l instanceof HashListener) {
                if (((HashListener) l).handleEvent(this))
                    break;
            }
        }
    }

}
