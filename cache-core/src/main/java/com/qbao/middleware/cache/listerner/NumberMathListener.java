/**
 * NumberMathListener.java
 */
package com.qbao.middleware.cache.listerner;

import java.util.EventListener;

import com.qbao.middleware.cache.event.DecrOptEvent;
import com.qbao.middleware.cache.event.IncrOptEvent;

/**
 * @author Yate
 * @date Sep 23, 2015
 * @description TODO
 * @version 1.0
 */
public interface NumberMathListener extends EventListener {

    boolean handleEvent(IncrOptEvent e);
    
    boolean handleEvent(DecrOptEvent e);
}
