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

    参数异常(1),连接枯竭(2)
    
    ;
    public final int flag;
    
    private CacheExceptionEnum(int i){
        this.flag = i;
    }
}
