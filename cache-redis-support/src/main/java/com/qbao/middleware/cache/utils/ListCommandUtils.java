/**
 * RedisUtils.java
 */
package com.qbao.middleware.cache.utils;

import java.util.HashMap;
import java.util.Map;

import com.qbao.middleware.cache.core.support.redis.IListCommand;
import com.qbao.middleware.cache.event.redis.key.KeyDelEvent;
import com.qbao.middleware.cache.event.redis.list.ListBlockLPopEvent;
import com.qbao.middleware.cache.exception.CacheCodeException;
import com.qbao.middleware.cache.exception.CacheExceptionEnum;
import com.qbao.middleware.cache.listener.KeyListener;
import com.qbao.middleware.cache.listener.ListListener;

/**
 * @author Yate
 * @date Sep 17, 2015
 * @description TODO
 * @version 1.0
 */
public class ListCommandUtils implements IListCommand {

    protected final Map<String, ListListener> handles = new HashMap<String, ListListener>();

    public void registerListener(String key, ListListener l)
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
     * com.qbao.middleware.cache.core.support.redis.IListCommand#bLPop(java.
     * lang.String, int)
     */
    @Override
    public void bLPop(String key, int timeOut) throws CacheCodeException {
        if (key == null || key.trim().equalsIgnoreCase("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        ListBlockLPopEvent e = new ListBlockLPopEvent(timeOut, this, key);
        for (ListListener l : handles.values()) {
            if (l.handleEvent(e))
                break;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.redis.IListCommand#bLPop(java.
     * lang.String[], int)
     */
    @Override
    public void bLPop(String[] keys, int timeOut) throws CacheCodeException {
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.redis.IListCommand#bRPop(java.
     * lang.String, int)
     */
    @Override
    public void bRPop(String key, int timeOut) throws CacheCodeException {
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.redis.IListCommand#bRPop(java.
     * lang.String[], int)
     */
    @Override
    public void bRPop(String[] keys, int timeOut) throws CacheCodeException {
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.redis.IListCommand#bRPopLpush()
     */
    @Override
    public void bRPopLpush() throws CacheCodeException {
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.qbao.middleware.cache.core.support.redis.IListCommand#lindex()
     */
    @Override
    public void lindex() throws CacheCodeException {
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.qbao.middleware.cache.core.support.redis.IListCommand#lLen()
     */
    @Override
    public void lLen() throws CacheCodeException {
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.qbao.middleware.cache.core.support.redis.IListCommand#lPop()
     */
    @Override
    public void lPop() throws CacheCodeException {
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.qbao.middleware.cache.core.support.redis.IListCommand#rPop()
     */
    @Override
    public void rPop() throws CacheCodeException {
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.qbao.middleware.cache.core.support.redis.IListCommand#lPush()
     */
    @Override
    public void lPush() throws CacheCodeException {
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.qbao.middleware.cache.core.support.redis.IListCommand#rPush()
     */
    @Override
    public void rPush() throws CacheCodeException {
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.qbao.middleware.cache.core.support.redis.IListCommand#lInsert()
     */
    @Override
    public void lInsert() throws CacheCodeException {
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.redis.IListCommand#rPopLPush()
     */
    @Override
    public void rPopLPush() throws CacheCodeException {
    }

}
