/**
 * IncrOptEvent.java
 */
package com.qbao.middleware.cache.event;

import java.util.EventObject;

/**
 * @author Yate
 * @date Sep 23, 2015
 * @description TODO
 * @version 1.0
 */
public class DecrOptEvent extends EventObject {

    public final String key;
    public final Integer value;

    /**
     * @param source
     */
    public DecrOptEvent(String key, Object source) {
        super(source);
        this.key = key;
        this.value = -1;
    }

    public DecrOptEvent(String key, Integer value, Object source) {
        super(source);
        this.key = key;
        this.value = value;
    }
}
