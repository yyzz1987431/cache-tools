/**
 * NumberMathUtils.java
 */
package com.qbao.middleware.cache.utils;

import java.util.HashMap;
import java.util.Map;

import com.qbao.middleware.cache.core.support.INumberMathClient;
import com.qbao.middleware.cache.event.DecrOptEvent;
import com.qbao.middleware.cache.event.IncrOptEvent;
import com.qbao.middleware.cache.exception.CacheCodeException;
import com.qbao.middleware.cache.exception.CacheExceptionEnum;
import com.qbao.middleware.cache.listerner.NumberMathListener;

/**
 * @author Yate
 * @date Sep 23, 2015
 * @description TODO
 * @version 1.0
 */
public class NumberMathUtils implements INumberMathClient {

    protected final Map<String, NumberMathListener> handles = new HashMap<String, NumberMathListener>();

    public void registerListener(String key, NumberMathListener l)
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
     * @see
     * com.qbao.middleware.cache.core.support.INumberMathClient#decr(java.lang
     * .String)
     */
    @Override
    public void decr(String key) throws CacheCodeException {
        if (key == null || key.trim().equalsIgnoreCase("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        DecrOptEvent e = new DecrOptEvent(key, this);
        for (NumberMathListener l : handles.values()) {
            if (l.handleEvent(e))
                break;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.INumberMathClient#decrBy(java.
     * lang.String, java.lang.Integer)
     */
    @Override
    public void decrBy(String key, Integer v) throws CacheCodeException {
        if (key == null || key.trim().equalsIgnoreCase("") || v == null
                || v.intValue() >= 0) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        DecrOptEvent e = new DecrOptEvent(key, v, this);
        for (NumberMathListener l : handles.values()) {
            if (l.handleEvent(e))
                break;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.INumberMathClient#incr(java.lang
     * .String)
     */
    @Override
    public void incr(String key) throws CacheCodeException {
        if (key == null || key.trim().equalsIgnoreCase("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        IncrOptEvent e = new IncrOptEvent(key, this);
        for (NumberMathListener l : handles.values()) {
            if (l.handleEvent(e))
                break;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.INumberMathClient#incrBy(java.
     * lang.String, java.lang.Integer)
     */
    @Override
    public void incrBy(String key, Integer v) throws CacheCodeException {
        if (key == null || key.trim().equalsIgnoreCase("") || v == null
                || v.intValue() <= 0) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        IncrOptEvent e = new IncrOptEvent(key, v, this);
        for (NumberMathListener l : handles.values()) {
            if (l.handleEvent(e))
                break;
        }
    }

}
