/**
 * RedisUtils.java
 */
package com.qbao.middleware.cache.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qbao.middleware.cache.core.support.redis.IListCommand;
import com.qbao.middleware.cache.event.redis.list.ListBlockLPopEvent;
import com.qbao.middleware.cache.event.redis.list.ListBlockRPopEvent;
import com.qbao.middleware.cache.event.redis.list.ListBlockRPopLPushEvent;
import com.qbao.middleware.cache.event.redis.list.ListIndexEvent;
import com.qbao.middleware.cache.event.redis.list.ListLenEvent;
import com.qbao.middleware.cache.event.redis.list.ListPopEvent;
import com.qbao.middleware.cache.event.redis.list.ListPushEvent;
import com.qbao.middleware.cache.event.redis.list.ListRPopEvent;
import com.qbao.middleware.cache.event.redis.list.ListRPopLPushEvent;
import com.qbao.middleware.cache.event.redis.list.ListRPushEvent;
import com.qbao.middleware.cache.exception.CacheCodeException;
import com.qbao.middleware.cache.exception.CacheExceptionEnum;
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
    public List<String> bLPop(String key, int timeOut)
            throws CacheCodeException {
        if (key == null || key.trim().equalsIgnoreCase("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        ListBlockLPopEvent e = new ListBlockLPopEvent(timeOut, this, key);
        for (ListListener l : handles.values()) {
            if (l.handleEvent(e))
                break;
        }
        return e.result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.redis.IListCommand#bLPop(java.
     * lang.String[], int)
     */
    @Override
    public List<String> bLPop(String[] keys, int timeOut)
            throws CacheCodeException {
        if (keys == null || keys.length == 0) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        ListBlockLPopEvent e = new ListBlockLPopEvent(timeOut, this, keys);
        for (ListListener l : handles.values()) {
            if (l.handleEvent(e))
                break;
        }
        return e.result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.redis.IListCommand#bRPop(java.
     * lang.String, int)
     */
    @Override
    public List<String> bRPop(String key, int timeOut)
            throws CacheCodeException {
        if (key == null || key.trim().equalsIgnoreCase("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        ListBlockRPopEvent e = new ListBlockRPopEvent(timeOut, this, key);
        for (ListListener l : handles.values()) {
            if (l.handleEvent(e))
                break;
        }
        return e.result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.redis.IListCommand#bRPop(java.
     * lang.String[], int)
     */
    @Override
    public List<String> bRPop(String[] keys, int timeOut)
            throws CacheCodeException {
        if (keys == null || keys.length == 0) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        ListBlockRPopEvent e = new ListBlockRPopEvent(timeOut, this, keys);
        for (ListListener l : handles.values()) {
            if (l.handleEvent(e))
                break;
        }
        return e.result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.redis.IListCommand#bRPopLpush()
     */
    @Override
    public String bRPopLpush(String key, String srcKey, String destKey,
            int timeout) throws CacheCodeException {
        if (key == null || key.trim().equals("") || srcKey == null
                || srcKey.trim().equals("") || destKey == null
                || destKey.trim().equals("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        ListBlockRPopLPushEvent e = new ListBlockRPopLPushEvent(key, srcKey,
                destKey, timeout, this);
        for (ListListener l : handles.values()) {
            if (l.handleEvent(e))
                break;
        }
        return e.result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.qbao.middleware.cache.core.support.redis.IListCommand#lindex()
     */
    @Override
    public String lindex(String key, long index) throws CacheCodeException {
        if (key == null || key.trim().equals("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        ListIndexEvent e = new ListIndexEvent(key, index, this);
        for (ListListener l : handles.values()) {
            if (l.handleEvent(e))
                break;
        }
        return e.result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.qbao.middleware.cache.core.support.redis.IListCommand#lLen()
     */
    @Override
    public Long lLen(String key) throws CacheCodeException {
        if (key == null || key.trim().equals("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        ListLenEvent e = new ListLenEvent(key, this);
        for (ListListener l : handles.values()) {
            if (l.handleEvent(e))
                break;
        }
        return e.result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.qbao.middleware.cache.core.support.redis.IListCommand#lPop()
     */
    @Override
    public String lPop(String key) throws CacheCodeException {
        if (key == null || key.trim().equals("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        ListPopEvent e = new ListPopEvent(key, this);
        for (ListListener l : handles.values()) {
            if (l.handleEvent(e))
                break;
        }
        return e.result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.qbao.middleware.cache.core.support.redis.IListCommand#rPop()
     */
    @Override
    public String rPop(String key) throws CacheCodeException {
        if (key == null || key.trim().equals("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        ListRPopEvent e = new ListRPopEvent(key, this);
        for (ListListener l : handles.values()) {
            if (l.handleEvent(e))
                break;
        }
        return e.result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.qbao.middleware.cache.core.support.redis.IListCommand#lPush()
     */
    @Override
    public Long lPush(String key, String... values) throws CacheCodeException {
        if (key == null || key.trim().equals("") || values == null
                || values.length > 0) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        ListPushEvent e = new ListPushEvent(key, values, this);
        for (ListListener l : handles.values()) {
            if (l.handleEvent(e))
                break;
        }
        return e.result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.qbao.middleware.cache.core.support.redis.IListCommand#rPush()
     */
    @Override
    public Long rPush(String key, String... values) throws CacheCodeException {
        if (key == null || key.trim().equals("") || values == null
                || values.length > 0) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        ListRPushEvent e = new ListRPushEvent(key, values, this);
        for (ListListener l : handles.values()) {
            if (l.handleEvent(e))
                break;
        }
        return e.result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.qbao.middleware.cache.core.support.redis.IListCommand#lInsert()
     */
    @Override
    public void lInsert() throws CacheCodeException {
        throw new CacheCodeException(CacheExceptionEnum.不支持的操作类型);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.redis.IListCommand#rPopLPush()
     */
    @Override
    public String rPopLPush(String key, String srcKey, String destKey)
            throws CacheCodeException {
        if (key == null || srcKey.trim().equals("") || srcKey == null
                || srcKey.trim().equals("") || destKey == null
                || destKey.trim().equals("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        ListRPopLPushEvent e = new ListRPopLPushEvent(key, srcKey, destKey,
                this);
        for (ListListener l : handles.values()) {
            if (l.handleEvent(e))
                break;
        }
        return e.result;
    }

}
