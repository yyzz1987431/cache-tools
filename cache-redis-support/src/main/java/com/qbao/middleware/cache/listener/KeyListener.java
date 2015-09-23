/**
 * KeyListener.java
 */
package com.qbao.middleware.cache.listener;

import com.qbao.middleware.cache.event.redis.key.KeyDelEvent;
import com.qbao.middleware.cache.event.redis.key.KeyExistsEvent;
import com.qbao.middleware.cache.event.redis.key.KeyExpireAtEvent;
import com.qbao.middleware.cache.event.redis.key.KeyExpirtEvent;
import com.qbao.middleware.cache.event.redis.key.KeyScanEvent;
import com.qbao.middleware.cache.event.redis.key.KeyTtlEvent;
import com.qbao.middleware.cache.event.redis.key.KeyTypeEvent;
import com.qbao.middleware.cache.listerner.CacheListener;

/**
 * @author Yate
 * @date Sep 23, 2015
 * @description TODO
 * @version 1.0
 */
public interface KeyListener extends CacheListener {

    boolean handleEvent(KeyDelEvent e);

    boolean handleEvent(KeyExistsEvent e);

    boolean handleEvent(KeyTtlEvent e);

    boolean handleEvent(KeyExpirtEvent e);

    boolean handleEvent(KeyExpireAtEvent e);

    boolean handleEvent(KeyTypeEvent e);

    boolean handleEvent(KeyScanEvent e);
}
