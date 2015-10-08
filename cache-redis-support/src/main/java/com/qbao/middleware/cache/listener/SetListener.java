/**
 * SetListener.java
 */
package com.qbao.middleware.cache.listener;

import com.qbao.middleware.cache.event.redis.set.SetAddEvent;
import com.qbao.middleware.cache.event.redis.set.SetCardEvent;
import com.qbao.middleware.cache.event.redis.set.SetDiffEvent;
import com.qbao.middleware.cache.event.redis.set.SetDiffStoreEvent;
import com.qbao.middleware.cache.listerner.CacheListener;

/**
 * @author Yate
 * @date Dec 29, 2015
 * @description TODO
 * @version 1.0
 */
public interface SetListener extends CacheListener {

    boolean handleEvent(SetAddEvent e);
    
    boolean handleEvent(SetCardEvent e);
    
    boolean handleEvent(SetDiffEvent e);
    
    boolean handleEvent(SetDiffStoreEvent e);
}
