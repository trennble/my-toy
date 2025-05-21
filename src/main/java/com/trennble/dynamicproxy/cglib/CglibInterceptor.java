package com.trennble.dynamicproxy.cglib;

import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CGLIB拦截器接口
 * 用于自定义代理的拦截行为
 */
public interface CglibInterceptor {
    
    /**
     * 拦截目标方法的调用
     * 
     * @param obj 代理对象
     * @param method 被调用的方法
     * @param args 方法参数
     * @param proxy 方法代理，可用于调用原始方法
     * @return 方法执行结果
     * @throws Throwable 执行过程中可能抛出的异常
     */
    Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable;
} 