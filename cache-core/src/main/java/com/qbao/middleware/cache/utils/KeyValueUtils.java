/**
 * RedisUtils.java
 */
package com.qbao.middleware.cache.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.qbao.middleware.cache.core.support.IKeyValueClient;
import com.qbao.middleware.cache.event.AppendOptEvent;
import com.qbao.middleware.cache.event.GetOptEvent;
import com.qbao.middleware.cache.event.SetOptEvent;
import com.qbao.middleware.cache.exception.CacheCodeException;
import com.qbao.middleware.cache.exception.CacheExceptionEnum;
import com.qbao.middleware.cache.listerner.KeyValueListener;

/**
 * @author Yate
 * @date Sep 17, 2015
 * @description TODO
 * @version 1.0
 */
public class KeyValueUtils implements IKeyValueClient {

    protected final Map<String, KeyValueListener> handles = new HashMap<String, KeyValueListener>();

    public void registerListener(String key, KeyValueListener l)
            throws CacheCodeException {
        if (key == null || l == null || key.trim().equals(""))
            throw new CacheCodeException(CacheExceptionEnum.参数异常);

        if (!handles.containsKey(key)) {
            synchronized (this) {
                if (!handles.containsKey(key)) {
                    handles.put(key, l);
                }
            }
        }
    }

    public void unregisterListener(String key) throws CacheCodeException {
        if (key == null || key.trim().equals(""))
            throw new CacheCodeException(CacheExceptionEnum.参数异常);

        if (handles.containsKey(key)) {
            handles.remove(key);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.qbao.middleware.cache.core.IKeyValueCache#set(java.lang.String,
     * java.lang.Object)
     */
    public <T extends Serializable> void set(String key, T data)
            throws CacheCodeException {
        this.set(key, data, null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.qbao.middleware.cache.core.IKeyValueCache#set(java.lang.String,
     * java.lang.Object, long)
     */
    public <T extends Serializable> void set(String key, T data, Integer time)
            throws CacheCodeException {
        if (key == null || data == null || key.trim().equalsIgnoreCase("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        SetOptEvent e = new SetOptEvent(key, data, time, this);
        for (KeyValueListener l : handles.values()) {
            if (l.handleEvent(e))
                break;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.qbao.middleware.cache.core.IKeyValueCache#get(java.lang.String,
     * java.lang.Class)
     */
    public <T extends Serializable> T get(String key, Class<T> clazz)
            throws CacheCodeException {
        if (key == null || clazz == null || key.trim().equalsIgnoreCase("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        GetOptEvent<T> e = new GetOptEvent<T>(key, clazz, this);
        for (KeyValueListener l : handles.values()) {
            if (l.handleEvent(e))
                break;
        }
        return e.result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.IKeyValueClient#append(java.lang
     * .String, java.lang.String)
     */
    @Override
    public void append(String key, String appendStr) throws CacheCodeException {
        if (key == null || appendStr == null || key.trim().equalsIgnoreCase("")
                || appendStr.trim().equalsIgnoreCase(""))
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        AppendOptEvent e = new AppendOptEvent(key, appendStr, this);
        for (KeyValueListener l : handles.values()) {
            if (l.handleEvent(e))
                break;
        }
    }

}
