/**
 * HashListener.java
 */
package com.qbao.middleware.cache.listener;

import com.qbao.middleware.cache.event.redis.list.ListBlockLPopEvent;
import com.qbao.middleware.cache.event.redis.list.ListBlockRPopEvent;
import com.qbao.middleware.cache.event.redis.list.ListBlockRPopLPushEvent;
import com.qbao.middleware.cache.event.redis.list.ListIndexEvent;
import com.qbao.middleware.cache.event.redis.list.ListInsertEvent;
import com.qbao.middleware.cache.event.redis.list.ListLenEvent;
import com.qbao.middleware.cache.event.redis.list.ListPopEvent;
import com.qbao.middleware.cache.event.redis.list.ListPushEvent;
import com.qbao.middleware.cache.event.redis.list.ListRPopEvent;
import com.qbao.middleware.cache.event.redis.list.ListRPopLPushEvent;
import com.qbao.middleware.cache.event.redis.list.ListRPushEvent;
import com.qbao.middleware.cache.event.redis.list.ListRangeEvent;
import com.qbao.middleware.cache.event.redis.list.ListRemEvent;
import com.qbao.middleware.cache.event.redis.list.ListSetEvent;
import com.qbao.middleware.cache.event.redis.list.ListTrimEvent;
import com.qbao.middleware.cache.listerner.CacheListener;

/**
 * @author Yate
 * @date Sep 23, 2015
 * @description TODO
 * @version 1.0
 */
public interface ListListener extends CacheListener {

    boolean handleEvent(ListBlockLPopEvent e);

    boolean handleEvent(ListBlockRPopEvent e);

    boolean handleEvent(ListBlockRPopLPushEvent e);

    boolean handleEvent(ListIndexEvent e);

    boolean handleEvent(ListInsertEvent e);

    boolean handleEvent(ListLenEvent e);

    boolean handleEvent(ListPopEvent e);

    boolean handleEvent(ListPushEvent e);

    boolean handleEvent(ListRangeEvent e);

    boolean handleEvent(ListRemEvent e);

    boolean handleEvent(ListRPopEvent e);

    boolean handleEvent(ListRPopLPushEvent e);

    boolean handleEvent(ListRPushEvent e);

    boolean handleEvent(ListSetEvent e);

    boolean handleEvent(ListTrimEvent e);

}
