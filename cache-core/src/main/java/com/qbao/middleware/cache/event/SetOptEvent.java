/**
 * SetEvent.java
 */
package com.qbao.middleware.cache.event;

import java.util.EventObject;

/**
 * @author Yate
 * @date Sep 17, 2015
 * @description TODO
 * @version 1.0
 */
public class SetOptEvent extends EventObject {

    public final String key;
    public final Object data;
    public final Integer expriedSec;

    /**
     * @param source
     */
    public SetOptEvent(String key, Object data, Integer expriedSec, Object source) {
        super(source);
        this.key = key;
        this.data = data;
        this.expriedSec = expriedSec;
    }

}
