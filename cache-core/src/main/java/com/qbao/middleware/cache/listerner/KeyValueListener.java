/**
 * SetListener.java
 */
package com.qbao.middleware.cache.listerner;

import java.util.EventListener;

import com.qbao.middleware.cache.event.AppendOptEvent;
import com.qbao.middleware.cache.event.GetOptEvent;
import com.qbao.middleware.cache.event.SetOptEvent;

/**
 * @author Yate
 * @date Sep 17, 2015
 * @description TODO
 * @version 1.0
 */
public interface KeyValueListener extends EventListener {

    boolean handleEvent(SetOptEvent e);

    <T> boolean handleEvent(GetOptEvent<T> e);
    
    boolean handleEvent(AppendOptEvent e);
}
