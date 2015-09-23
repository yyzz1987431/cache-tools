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
public class IncrOptEvent extends EventObject {

    public final String key;
    public final long value;

    /**
     * @param source
     */
    public IncrOptEvent(String key, Object source) {
        super(source);
        this.key = key;
        this.value = 1;
    }

    public IncrOptEvent(String key, long value, Object source) {
        super(source);
        this.key = key;
        this.value = value;
    }
}
