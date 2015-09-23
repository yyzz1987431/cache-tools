/**
 * SetListener.java
 */
package com.qbao.middleware.cache.listerner;

import java.util.EventListener;

import com.qbao.middleware.cache.event.redis.string.StringAppendEvent;
import com.qbao.middleware.cache.event.redis.string.StringDecrEvent;
import com.qbao.middleware.cache.event.redis.string.StringGetEvent;
import com.qbao.middleware.cache.event.redis.string.StringIncrEvent;
import com.qbao.middleware.cache.event.redis.string.StringSetEvent;

/**
 * @author Yate
 * @date Sep 17, 2015
 * @description TODO
 * @version 1.0
 */
public interface StringListener extends EventListener {

    boolean handleEvent(StringSetEvent e);

    <T> boolean handleEvent(StringGetEvent<T> e);
    
    boolean handleEvent(StringAppendEvent e);
    
    boolean handleEvent(StringIncrEvent e);
    
    boolean handleEvent(StringDecrEvent e);
}
