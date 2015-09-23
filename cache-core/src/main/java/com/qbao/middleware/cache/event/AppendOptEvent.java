/**
 * GetOptEvent.java
 */
package com.qbao.middleware.cache.event;

import java.util.EventObject;

/**
 * @author Yate
 * @date Sep 17, 2015
 * @description TODO
 * @version 1.0
 */
public class AppendOptEvent extends EventObject {

    public final String key;
    public final String appendValue;

    /**
     * @param source
     */
    public AppendOptEvent(String key, String appendValue, Object source) {
        super(source);
        this.key = key;
        this.appendValue = appendValue;
    }

}
