package com.trennble.dynamicproxy.cglib.ann;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * CGLIB代理注解
 * 用于标记需要被CGLIB动态代理的类
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CglibProxyAnnotation {
    
    /**
     * 拦截器类型
     */
    InterceptorType type() default InterceptorType.PERFORMANCE;
    
    /**
     * 拦截器类型枚举
     */
    enum InterceptorType {
        /**
         * 性能监控拦截器
         */
        PERFORMANCE,
        
        /**
         * 事务拦截器
         */
        TRANSACTION
    }
} 