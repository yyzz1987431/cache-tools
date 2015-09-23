/**
 * HashListener.java
 */
package com.qbao.middleware.cache.listener;

import java.util.EventListener;

import com.qbao.middleware.cache.event.redis.hash.HashDelEvent;
import com.qbao.middleware.cache.event.redis.hash.HashExistsEvent;
import com.qbao.middleware.cache.event.redis.hash.HashGetEvent;
import com.qbao.middleware.cache.event.redis.hash.HashIncrByEvent;
import com.qbao.middleware.cache.event.redis.hash.HashIncrByFloatEvent;
import com.qbao.middleware.cache.event.redis.hash.HashLenEvent;
import com.qbao.middleware.cache.event.redis.hash.HashSetEvent;

/**
 * @author Yate
 * @date Sep 23, 2015
 * @description TODO
 * @version 1.0
 */
public interface HashListener extends EventListener {

    boolean handleEvent(HashExistsEvent e);

    <T> boolean handleEvent(HashGetEvent<T> e);

    boolean handleEvent(HashSetEvent e);

    boolean handleEvent(HashDelEvent e);

    boolean handleEvent(HashIncrByEvent e);

    boolean handleEvent(HashIncrByFloatEvent e);

    boolean handleEvent(HashLenEvent e);
}
