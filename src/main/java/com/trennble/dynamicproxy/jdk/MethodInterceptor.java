package com.trennble.dynamicproxy.jdk;

import java.lang.reflect.Method;

/**
 * 方法拦截器接口
 * 用于自定义代理的拦截行为
 */
public interface MethodInterceptor {
    
    /**
     * 拦截目标方法的调用
     * 
     * @param target 目标对象
     * @param method 被调用的方法
     * @param args 方法参数
     * @return 方法执行结果
     * @throws Throwable 执行过程中可能抛出的异常
     */
    Object intercept(Object target, Method method, Object[] args) throws Throwable;
} 