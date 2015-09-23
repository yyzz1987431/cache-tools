/**
 * SetEvent.java
 */
package com.qbao.middleware.cache.event.redis.string;

import com.qbao.middleware.cache.event.redis.RedisBaseEvent;

/**
 * @author Yate
 * @date Sep 17, 2015
 * @description TODO
 * @version 1.0
 */
public class StringSetEvent extends RedisBaseEvent {

    public final Object data;
    public final Integer expriedSec;

    /**
     * @param source
     */
    public StringSetEvent(String key, Object data, Integer expriedSec, Object source) {
        super(key,source);
        this.data = data;
        this.expriedSec = expriedSec;
    }

}
