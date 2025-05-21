package com.trennble.dynamicproxy.jdk.ann;

import com.trennble.dynamicproxy.jdk.MethodInterceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 代理注解
 * 用于标记需要被动态代理的服务
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ProxyAnnotation {
    
    /**
     * 拦截器类，默认使用性能监控拦截器
     */
    Class<? extends MethodInterceptor> interceptor() default PerformanceMethodInterceptor.class;
} 