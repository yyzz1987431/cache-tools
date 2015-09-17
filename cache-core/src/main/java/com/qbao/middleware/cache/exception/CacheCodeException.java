/**
 * CacheCodeException.java
 */
package com.qbao.middleware.cache.exception;

/**
 * @author Yate
 * @date Sep 17, 2015
 * @description TODO
 * @version 1.0
 */
public class CacheCodeException extends Exception {

    public final String msg;
    public final CacheExceptionEnum code;
    public final Throwable originException;

    public CacheCodeException(CacheExceptionEnum e) {
        this(e, null);
    }

    public CacheCodeException(CacheExceptionEnum e, Throwable ex) {
        this(e, ex, e.name());
    }

    public CacheCodeException(CacheExceptionEnum e, Throwable ex, String msgs) {
        this.code = e;
        this.msg = e.name();
        this.originException = ex;
    }
}
