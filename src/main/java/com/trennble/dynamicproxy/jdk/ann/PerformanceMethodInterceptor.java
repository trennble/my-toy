package com.trennble.dynamicproxy.jdk.ann;

import com.trennble.dynamicproxy.jdk.MethodInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * 性能监控拦截器
 * 用于监控方法执行时间
 */
public class PerformanceMethodInterceptor implements MethodInterceptor {

    private static final Logger log = LoggerFactory.getLogger(PerformanceMethodInterceptor.class);

    @Override
    public Object intercept(Object target, Method method, Object[] args) throws Throwable {
        long startTime = System.currentTimeMillis();
        log.info("【性能监控】开始执行方法: {}.{}", target.getClass().getSimpleName(), method.getName());

        try {
            // 调用目标方法
            Object result = method.invoke(target, args);

            // 记录执行时间
            long executionTime = System.currentTimeMillis() - startTime;
            log.info("【性能监控】方法执行完成: {}.{}, 耗时: {}ms", 
                    target.getClass().getSimpleName(), method.getName(), executionTime);

            // 如果执行时间超过阈值，记录警告
            if (executionTime > 1000) {
                log.warn("【性能警告】方法执行时间过长: {}.{}, 耗时: {}ms", 
                        target.getClass().getSimpleName(), method.getName(), executionTime);
            }

            return result;
        } catch (Exception e) {
            log.error("【性能监控】方法执行异常: {}.{}, 异常: {}", 
                    target.getClass().getSimpleName(), method.getName(), e.getMessage(), e);
            throw e.getCause();
        }
    }
} 