/**
 * HashListener.java
 */
package com.qbao.middleware.cache.listener;

import com.qbao.middleware.cache.event.redis.hash.HashDelEvent;
import com.qbao.middleware.cache.event.redis.hash.HashExistsEvent;
import com.qbao.middleware.cache.event.redis.hash.HashGetEvent;
import com.qbao.middleware.cache.event.redis.hash.HashIncrByEvent;
import com.qbao.middleware.cache.event.redis.hash.HashIncrByFloatEvent;
import com.qbao.middleware.cache.event.redis.hash.HashLenEvent;
import com.qbao.middleware.cache.event.redis.hash.HashSetEvent;
import com.qbao.middleware.cache.listerner.CacheListener;

/**
 * @author Yate
 * @date Sep 23, 2015
 * @description TODO
 * @version 1.0
 */
public interface HashListener extends CacheListener {

    boolean handleEvent(HashExistsEvent e);

    <T> boolean handleEvent(HashGetEvent<T> e);

    boolean handleEvent(HashSetEvent e);

    boolean handleEvent(HashDelEvent e);

    boolean handleEvent(HashIncrByEvent e);

    boolean handleEvent(HashIncrByFloatEvent e);

    boolean handleEvent(HashLenEvent e);
}
