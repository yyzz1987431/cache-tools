/**
 * SetEvent.java
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
public class StringSetEvent extends RedisBaseEvent {

    public final Object data;
    public final Integer expriedSec;
    public String result;

    /**
     * @param source
     */
    public StringSetEvent(String key, Object data, Integer expriedSec,
            Object source) {
        super(key, source);
        this.data = data;
        this.expriedSec = expriedSec;
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
