package com.trennble.dynamicproxy.cglib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * CGLIB代理工厂
 * 用于创建类的代理实例（不需要目标类实现接口）
 */
public class CglibProxyFactory {
    
    private static final Logger log = LoggerFactory.getLogger(CglibProxyFactory.class);
    
    /**
     * 创建CGLIB代理
     * 
     * @param targetClass 目标类
     * @param <T> 类型
     * @return 代理对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T createProxy(Class<T> targetClass) {
        // 创建Enhancer对象
        Enhancer enhancer = new Enhancer();
        // 设置目标类为父类
        enhancer.setSuperclass(targetClass);
        // 设置回调（方法拦截器）
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                long startTime = System.currentTimeMillis();
                log.info("CGLIB调用开始 - 方法: {}, 参数: {}", method.getName(), Arrays.toString(args));
                
                try {
                    // 调用父类方法（目标方法）
                    Object result = proxy.invokeSuper(obj, args);
                    
                    long endTime = System.currentTimeMillis();
                    log.info("CGLIB调用结束 - 方法: {}, 耗时: {}ms, 返回值: {}", 
                            method.getName(), (endTime - startTime), result);
                    
                    return result;
                } catch (Exception e) {
                    log.error("CGLIB调用异常 - 方法: {}, 异常: {}", method.getName(), e.getMessage(), e);
                    throw e;
                }
            }
        });
        
        // 创建代理对象
        return (T) enhancer.create();
    }
    
    /**
     * 创建CGLIB代理，使用自定义拦截器
     * 
     * @param targetClass 目标类
     * @param interceptor 自定义拦截器
     * @param <T> 类型
     * @return 代理对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T createProxy(Class<T> targetClass, CglibInterceptor interceptor) {
        if (interceptor == null) {
            throw new IllegalArgumentException("拦截器不能为空");
        }
        
        // 创建Enhancer对象
        Enhancer enhancer = new Enhancer();
        // 设置目标类为父类
        enhancer.setSuperclass(targetClass);
        // 设置回调（方法拦截器）
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                return interceptor.intercept(obj, method, args, proxy);
            }
        });
        
        // 创建代理对象
        return (T) enhancer.create();
    }
} 