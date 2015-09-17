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
public class GetOptEvent<T> extends EventObject {

    public final String key;
    public Class<T> targetClass;
    public T result; 

    /**
     * @param source
     */
    public GetOptEvent(String key, Class<T> clazz, Object source) {
        super(source);
        this.key = key;
        this.targetClass = clazz;
    }

}
