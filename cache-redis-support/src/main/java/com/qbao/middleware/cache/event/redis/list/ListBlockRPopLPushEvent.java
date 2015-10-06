/**
 * ListBlockLPopEvent.java
 */
package com.qbao.middleware.cache.event.redis.list;

import com.qbao.middleware.cache.event.redis.RedisBaseEvent;

/**
 * @author Yate
 * @date Sep 27, 2015
 * @description TODO
 * @version 1.0
 */
public class ListBlockRPopLPushEvent extends RedisBaseEvent {

    public final String sourceKey;
    public final String destinationKey;
    public final int timeout;
    public String result;
    /**
     * @param key
     * @param source
     */
    public ListBlockRPopLPushEvent(String key, String skey,String dkey,int timeout,Object source) {
        super(key, source);
        this.sourceKey = skey;
        this.destinationKey = dkey;
        this.timeout = timeout;
    }

}
