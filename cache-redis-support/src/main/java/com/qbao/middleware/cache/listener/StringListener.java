/**
 * SetListener.java
 */
package com.qbao.middleware.cache.listener;

import com.qbao.middleware.cache.event.redis.string.StringAppendEvent;
import com.qbao.middleware.cache.event.redis.string.StringDecrEvent;
import com.qbao.middleware.cache.event.redis.string.StringGetEvent;
import com.qbao.middleware.cache.event.redis.string.StringIncrEvent;
import com.qbao.middleware.cache.event.redis.string.StringSetEvent;
import com.qbao.middleware.cache.listerner.CacheListener;

/**
 * @author Yate
 * @date Sep 17, 2015
 * @description TODO
 * @version 1.0
 */
public interface StringListener extends CacheListener {

    boolean handleEvent(StringSetEvent e);

    <T> boolean handleEvent(StringGetEvent<T> e);
    
    boolean handleEvent(StringAppendEvent e);
    
    boolean handleEvent(StringIncrEvent e);
    
    boolean handleEvent(StringDecrEvent e);
}
