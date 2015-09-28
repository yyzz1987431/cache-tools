/**
 * ListBlockLPopEvent.java
 */
package com.qbao.middleware.cache.event.redis.list;

import java.util.EventObject;
import java.util.List;

/**
 * @author Yate
 * @date Sep 27, 2015
 * @description TODO
 * @version 1.0
 */
public class ListBlockLPopEvent extends EventObject {

    public final String[] keys;
    public final int timeOut;
    public List<String> result;

    /**
     * @param key
     * @param source
     */
    public ListBlockLPopEvent(int timeOut, Object source, String... keys) {
        super(source);
        this.keys = keys;
        this.timeOut = timeOut;
    }

}
