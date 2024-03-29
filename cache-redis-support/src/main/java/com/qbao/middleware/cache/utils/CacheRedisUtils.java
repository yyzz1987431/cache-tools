/**
 * CacheRedisUtils.java
 */
package com.qbao.middleware.cache.utils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.qbao.middleware.cache.core.support.redis.IHashCommand;
import com.qbao.middleware.cache.core.support.redis.IKeyCommand;
import com.qbao.middleware.cache.core.support.redis.IListCommand;
import com.qbao.middleware.cache.core.support.redis.IStringCommand;
import com.qbao.middleware.cache.event.redis.hash.HashDelEvent;
import com.qbao.middleware.cache.event.redis.hash.HashExistsEvent;
import com.qbao.middleware.cache.event.redis.hash.HashGetALLEvent;
import com.qbao.middleware.cache.event.redis.hash.HashGetEvent;
import com.qbao.middleware.cache.event.redis.hash.HashIncrByEvent;
import com.qbao.middleware.cache.event.redis.hash.HashIncrByFloatEvent;
import com.qbao.middleware.cache.event.redis.hash.HashLenEvent;
import com.qbao.middleware.cache.event.redis.hash.HashSetEvent;
import com.qbao.middleware.cache.event.redis.key.KeyDelEvent;
import com.qbao.middleware.cache.event.redis.key.KeyExistsEvent;
import com.qbao.middleware.cache.event.redis.key.KeyExpireAtEvent;
import com.qbao.middleware.cache.event.redis.key.KeyExpirtEvent;
import com.qbao.middleware.cache.event.redis.key.KeyTtlEvent;
import com.qbao.middleware.cache.event.redis.key.KeyTypeEvent;
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
import com.qbao.middleware.cache.event.redis.string.StringAppendEvent;
import com.qbao.middleware.cache.event.redis.string.StringDecrEvent;
import com.qbao.middleware.cache.event.redis.string.StringGetEvent;
import com.qbao.middleware.cache.event.redis.string.StringIncrEvent;
import com.qbao.middleware.cache.event.redis.string.StringSetEvent;
import com.qbao.middleware.cache.exception.CacheCodeException;
import com.qbao.middleware.cache.exception.CacheExceptionEnum;
import com.qbao.middleware.cache.listener.HashListener;
import com.qbao.middleware.cache.listener.KeyListener;
import com.qbao.middleware.cache.listener.ListListener;
import com.qbao.middleware.cache.listener.StringListener;
import com.qbao.middleware.cache.listerner.CacheListener;
import com.qbao.middleware.cache.strategy.IConditionStrategy;

/**
 * @author Yate
 * @date Sep 23, 2015
 * @description TODO
 * @version 1.0
 */
public class CacheRedisUtils implements IHashCommand, IKeyCommand,
        IStringCommand, IListCommand {

    protected final Map<String, CacheListener> handles;

    protected ThreadLocal<IConditionStrategy> currentStrategy = new ThreadLocal<IConditionStrategy>();

    public CacheRedisUtils(Map<String, CacheListener> handles) {
        this.handles = handles;
    }

    public void setCurrentStrategy(IConditionStrategy currentStrategy) {
        this.currentStrategy.set(currentStrategy);
    }

    public void registerListener(String key, CacheListener l)
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

    public <T extends Serializable> void set(String key, T data)
            throws CacheCodeException {
        this.set(key, data, null);
    }

    public <T extends Serializable> void set(String key, T data, Integer time)
            throws CacheCodeException {
        if (key == null || data == null || key.trim().equalsIgnoreCase("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        StringSetEvent e = new StringSetEvent(key, data, time, this);

        for (CacheListener l : handles.values()) {
            if (l instanceof StringListener) {
                if (((StringListener) l).handleEvent(e))
                    break;
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.redis.IStringCommand#get(java.
     * lang.String, java.lang.Class)
     */
    @Override
    public <T extends Serializable> T get(String key, Class<T> clazz)
            throws CacheCodeException {
        if (key == null || clazz == null || key.trim().equalsIgnoreCase("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        StringGetEvent<T> e = new StringGetEvent<T>(key, clazz, this);

        for (CacheListener l : handles.values()) {
            if (l instanceof StringListener) {
                if (((StringListener) l).handleEvent(e))
                    break;
            }
        }
        return e.result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.redis.IStringCommand#append(java
     * .lang.String, java.lang.String)
     */
    @Override
    public void append(String key, String appendStr) throws CacheCodeException {
        if (key == null || appendStr == null || key.trim().equalsIgnoreCase("")
                || appendStr.trim().equalsIgnoreCase(""))
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        StringAppendEvent e = new StringAppendEvent(key, appendStr, this);
        for (CacheListener l : handles.values()) {
            if (l instanceof StringListener) {
                if (((StringListener) l).handleEvent(e))
                    break;
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.redis.IStringCommand#decr(java
     * .lang.String)
     */
    @Override
    public void decr(String key) throws CacheCodeException {
        if (key == null || key.trim().equalsIgnoreCase("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        StringDecrEvent e = new StringDecrEvent(key, this);
        for (CacheListener l : handles.values()) {
            if (l instanceof StringListener) {
                if (((StringListener) l).handleEvent(e))
                    break;
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.redis.IStringCommand#decrBy(java
     * .lang.String, java.lang.Integer)
     */
    @Override
    public void decrBy(String key, Integer v) throws CacheCodeException {
        if (key == null || key.trim().equalsIgnoreCase("") || v == null
                || v.intValue() >= 0) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        StringDecrEvent e = new StringDecrEvent(key, v, this);
        for (CacheListener l : handles.values()) {
            if (l instanceof StringListener) {
                if (((StringListener) l).handleEvent(e))
                    break;
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.redis.IStringCommand#incr(java
     * .lang.String)
     */
    @Override
    public void incr(String key) throws CacheCodeException {
        if (key == null || key.trim().equalsIgnoreCase("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        StringIncrEvent e = new StringIncrEvent(key, this);
        for (CacheListener l : handles.values()) {
            if (l instanceof StringListener) {
                if (((StringListener) l).handleEvent(e))
                    break;
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.redis.IStringCommand#incrBy(java
     * .lang.String, java.lang.Integer)
     */
    @Override
    public void incrBy(String key, Integer v) throws CacheCodeException {
        if (key == null || key.trim().equalsIgnoreCase("") || v == null
                || v.intValue() <= 0) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        StringIncrEvent e = new StringIncrEvent(key, v, this);
        for (CacheListener l : handles.values()) {
            if (l instanceof StringListener) {
                if (((StringListener) l).handleEvent(e))
                    break;
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.redis.IKeyCommand#del(java.lang
     * .String)
     */
    @Override
    public Long del(String key) throws CacheCodeException {
        if (key == null || key.trim().equalsIgnoreCase("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        KeyDelEvent e = new KeyDelEvent(key, this);
        for (CacheListener l : handles.values()) {
            if (l instanceof KeyListener) {
                if (((KeyListener) l).handleEvent(e))
                    break;
            }
        }
        return e.result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.redis.IKeyCommand#exists(java.
     * lang.String)
     */
    @Override
    public Boolean exists(String key) throws CacheCodeException {
        if (key == null || key.trim().equalsIgnoreCase("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        KeyExistsEvent e = new KeyExistsEvent(key, this);
        for (CacheListener l : handles.values()) {
            if (l instanceof KeyListener) {
                if (((KeyListener) l).handleEvent(e))
                    break;
            }
        }
        return e.result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.redis.IKeyCommand#ttl(java.lang
     * .String)
     */
    @Override
    public Long ttl(String key) throws CacheCodeException {
        if (key == null || key.trim().equalsIgnoreCase("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        KeyTtlEvent e = new KeyTtlEvent(key, this);
        for (CacheListener l : handles.values()) {
            if (l instanceof KeyListener) {
                if (((KeyListener) l).handleEvent(e))
                    break;
            }
        }
        return e.result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.redis.IKeyCommand#expire(java.
     * lang.String, int)
     */
    @Override
    public Long expire(String key, int seconds) throws CacheCodeException {
        if (key == null || key.trim().equalsIgnoreCase("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        KeyExpirtEvent e = new KeyExpirtEvent(key, seconds, this);
        for (CacheListener l : handles.values()) {
            if (l instanceof KeyListener) {
                if (((KeyListener) l).handleEvent(e))
                    break;
            }
        }
        return e.result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.redis.IKeyCommand#expireAt(java
     * .lang.String, long)
     */
    @Override
    public Long expireAt(String key, long unixTime) throws CacheCodeException {
        if (key == null || key.trim().equalsIgnoreCase("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        KeyExpireAtEvent e = new KeyExpireAtEvent(key, unixTime, this);
        for (CacheListener l : handles.values()) {
            if (l instanceof KeyListener) {
                if (((KeyListener) l).handleEvent(e))
                    break;
            }
        }
        return e.result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.redis.IKeyCommand#type(java.lang
     * .String)
     */
    @Override
    public String type(String key) throws CacheCodeException {
        if (key == null || key.trim().equalsIgnoreCase("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        KeyTypeEvent e = new KeyTypeEvent(key, this);
        for (CacheListener l : handles.values()) {
            if (l instanceof KeyListener) {
                if (((KeyListener) l).handleEvent(e))
                    break;
            }
        }
        return e.result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.redis.IKeyCommand#scan(java.lang
     * .String)
     */
    @Override
    public void scan(String pettey) throws CacheCodeException {
        throw new CacheCodeException(CacheExceptionEnum.不支持的操作类型);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.redis.IHashCommand#exists(java
     * .lang.String, java.lang.String)
     */
    @Override
    public Boolean hexists(String key, String field) throws CacheCodeException {
        if (key == null || field == null || key.trim().equalsIgnoreCase("")
                || field.trim().equalsIgnoreCase("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        HashExistsEvent e = new HashExistsEvent(key, field, this);
        for (CacheListener l : handles.values()) {
            if (l instanceof HashListener) {
                if (((HashListener) l).handleEvent(e))
                    break;
            }
        }
        return e.result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.redis.IHashCommand#get(java.lang
     * .String, java.lang.String, java.lang.Class)
     */
    @Override
    public <T extends Serializable> T hget(String key, String field,
            Class<T> clazz) throws CacheCodeException {
        if (key == null || field == null || key.trim().equalsIgnoreCase("")
                || field.trim().equalsIgnoreCase("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        HashGetEvent<T> e = new HashGetEvent<T>(key, field, clazz, this);
        for (CacheListener l : handles.values()) {
            if (l instanceof HashListener) {
                if (((HashListener) l).handleEvent(e))
                    break;
            }
        }
        return e.result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.redis.IHashCommand#set(java.lang
     * .String, java.lang.String, java.io.Serializable)
     */
    @Override
    public <T extends Serializable> Long hset(String key, String field, T data)
            throws CacheCodeException {
        if (key == null || data == null || key.trim().equalsIgnoreCase("")
                || field == null || field.trim().equalsIgnoreCase("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        HashSetEvent e = new HashSetEvent(key, field, data, this);
        for (CacheListener l : handles.values()) {
            if (l instanceof HashListener) {
                if (((HashListener) l).handleEvent(e))
                    break;
            }
        }
        return e.result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.redis.IHashCommand#del(java.lang
     * .String, java.lang.String)
     */
    @Override
    public Long hdel(String key, String field) throws CacheCodeException {
        if (key == null || key.trim().equalsIgnoreCase("") || field == null
                || field.trim().equalsIgnoreCase("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        HashDelEvent e = new HashDelEvent(key, field, this);
        for (CacheListener l : handles.values()) {
            if (l instanceof HashListener) {
                if (((HashListener) l).handleEvent(e))
                    break;
            }
        }
        return e.result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.redis.IHashCommand#incrBy(java
     * .lang.String, java.lang.String, long)
     */
    @Override
    public Long hincrBy(String key, String field, long v)
            throws CacheCodeException {
        if (key == null || key.trim().equalsIgnoreCase("") || field == null
                || field.trim().equalsIgnoreCase("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        HashIncrByEvent e = new HashIncrByEvent(key, field, v, this);
        for (CacheListener l : handles.values()) {
            if (l instanceof HashListener) {
                if (((HashListener) l).handleEvent(e))
                    break;
            }
        }
        return e.result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.redis.IHashCommand#incrByFloat
     * (java.lang.String, java.lang.String, double)
     */
    @Override
    public Double hincrByFloat(String key, String field, double v)
            throws CacheCodeException {
        if (key == null || key.trim().equalsIgnoreCase("") || field == null
                || field.trim().equalsIgnoreCase("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        HashIncrByFloatEvent e = new HashIncrByFloatEvent(key, field, v, this);
        for (CacheListener l : handles.values()) {
            if (l instanceof HashListener) {
                if (((HashListener) l).handleEvent(e))
                    break;
            }
        }
        return e.result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.qbao.middleware.cache.core.support.redis.IHashCommand#len(java.lang
     * .String)
     */
    @Override
    public Long hlen(String key) throws CacheCodeException {
        if (key == null || key.trim().equalsIgnoreCase("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        HashLenEvent e = new HashLenEvent(key, this);
        for (CacheListener l : handles.values()) {
            if (l instanceof HashListener) {
                if (((HashListener) l).handleEvent(e))
                    break;
            }
        }
        return e.result;
    }

    public Map<String, Object> hgetall(String key, Class<?> objClass) {
        if (key == null || key.trim().equalsIgnoreCase("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        HashGetALLEvent e = new HashGetALLEvent(key, this, objClass);
        for (CacheListener l : handles.values()) {
            if (l instanceof HashListener) {
                if (((HashListener) l).handleEvent(e))
                    break;
            }
        }
        return e.result;
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
        for (CacheListener l : handles.values()) {
            if (l instanceof ListListener) {
                if (((ListListener) l).handleEvent(e))
                    break;
            }
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
        for (CacheListener l : handles.values()) {
            if (l instanceof ListListener) {
                if (((ListListener) l).handleEvent(e))
                    break;
            }
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
        for (CacheListener l : handles.values()) {
            if (l instanceof ListListener) {
                if (((ListListener) l).handleEvent(e))
                    break;
            }
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
        for (CacheListener l : handles.values()) {
            if (l instanceof ListListener) {
                if (((ListListener) l).handleEvent(e))
                    break;
            }
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
        for (CacheListener l : handles.values()) {
            if (l instanceof ListListener) {
                if (((ListListener) l).handleEvent(e))
                    break;
            }
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
        for (CacheListener l : handles.values()) {
            if (l instanceof ListListener) {
                if (((ListListener) l).handleEvent(e))
                    break;
            }
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
        for (CacheListener l : handles.values()) {
            if (l instanceof ListListener) {
                if (((ListListener) l).handleEvent(e))
                    break;
            }
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
        for (CacheListener l : handles.values()) {
            if (l instanceof ListListener) {
                if (((ListListener) l).handleEvent(e))
                    break;
            }
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
        for (CacheListener l : handles.values()) {
            if (l instanceof ListListener) {
                if (((ListListener) l).handleEvent(e))
                    break;
            }
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
        for (CacheListener l : handles.values()) {
            if (l instanceof ListListener) {
                if (((ListListener) l).handleEvent(e))
                    break;
            }
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
        for (CacheListener l : handles.values()) {
            if (l instanceof ListListener) {
                if (((ListListener) l).handleEvent(e))
                    break;
            }
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
        if (key == null || key.trim().equals("") || srcKey == null
                || srcKey.trim().equals("") || destKey == null
                || destKey.trim().equals("")) {
            throw new CacheCodeException(CacheExceptionEnum.参数异常);
        }

        ListRPopLPushEvent e = new ListRPopLPushEvent(key, srcKey, destKey,
                this);
        for (CacheListener l : handles.values()) {
            if (l instanceof ListListener) {
                if (((ListListener) l).handleEvent(e))
                    break;
            }
        }
        return e.result;
    }
}
