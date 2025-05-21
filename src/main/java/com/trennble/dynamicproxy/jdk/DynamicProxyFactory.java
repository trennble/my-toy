package com.trennble.dynamicproxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JDK动态代理工厂类
 * 用于创建接口的代理实例
 */
public class DynamicProxyFactory {
    
    private static final Logger log = LoggerFactory.getLogger(DynamicProxyFactory.class);
    
    /**
     * 创建接口的代理实例
     * 
     * @param targetInterface 目标接口类
     * @param targetObject 被代理的实现类实例
     * @param <T> 接口类型
     * @return 代理实例
     */
    @SuppressWarnings("unchecked")
    public static <T> T createProxy(Class<T> targetInterface, T targetObject) {
        if (!targetInterface.isInterface()) {
            throw new IllegalArgumentException("targetInterface必须是接口类型");
        }
        
        if (targetObject == null) {
            throw new IllegalArgumentException("targetObject不能为空");
        }
        
        if (!targetInterface.isAssignableFrom(targetObject.getClass())) {
            throw new IllegalArgumentException("targetObject必须实现targetInterface接口");
        }
        
        // 创建InvocationHandler
        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                long startTime = System.currentTimeMillis();
                log.info("调用开始 - 方法: {}, 参数: {}", method.getName(), Arrays.toString(args));
                
                try {
                    // 调用目标对象的方法
                    Object result = method.invoke(targetObject, args);
                    
                    long endTime = System.currentTimeMillis();
                    log.info("调用结束 - 方法: {}, 耗时: {}ms, 返回值: {}", method.getName(), (endTime - startTime), result);
                    
                    return result;
                } catch (Exception e) {
                    log.error("调用异常 - 方法: {}, 异常: {}", method.getName(), e.getMessage(), e);
                    throw e;
                }
            }
        };
        
        // 创建代理实例
        return (T) Proxy.newProxyInstance(
                targetInterface.getClassLoader(),
                new Class<?>[] { targetInterface },
                invocationHandler
        );
    }
    
    /**
     * 创建带有自定义拦截逻辑的代理实例
     * 
     * @param targetInterface 目标接口类
     * @param targetObject 被代理的实现类实例
     * @param interceptor 自定义拦截器
     * @param <T> 接口类型
     * @return 代理实例
     */
    @SuppressWarnings("unchecked")
    public static <T> T createProxy(Class<T> targetInterface, T targetObject, MethodInterceptor interceptor) {
        if (!targetInterface.isInterface()) {
            throw new IllegalArgumentException("targetInterface必须是接口类型");
        }
        
        if (targetObject == null) {
            throw new IllegalArgumentException("targetObject不能为空");
        }
        
        if (!targetInterface.isAssignableFrom(targetObject.getClass())) {
            throw new IllegalArgumentException("targetObject必须实现targetInterface接口");
        }
        
        if (interceptor == null) {
            throw new IllegalArgumentException("interceptor不能为空");
        }
        
        // 创建InvocationHandler
        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return interceptor.intercept(targetObject, method, args);
            }
        };
        
        // 创建代理实例
        return (T) Proxy.newProxyInstance(
                targetInterface.getClassLoader(),
                new Class<?>[] { targetInterface },
                invocationHandler
        );
    }
} 