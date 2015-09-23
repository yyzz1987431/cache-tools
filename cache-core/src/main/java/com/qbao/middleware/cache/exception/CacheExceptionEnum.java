/**
 * CacheExceptionEnum.java
 */
package com.qbao.middleware.cache.exception;

/**
 * @author Yate
 * @date Sep 17, 2015
 * @description TODO
 * @version 1.0
 */
public enum CacheExceptionEnum {

    参数异常(1),不支持的操作类型(2),
    
    ;
    public final int flag;
    
    private CacheExceptionEnum(int i){
        this.flag = i;
    }
}
