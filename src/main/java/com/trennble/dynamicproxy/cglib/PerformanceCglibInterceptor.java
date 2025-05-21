package com.trennble.dynamicproxy.cglib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 性能监控CGLIB拦截器
 * 用于监控方法执行时间
 */
public class PerformanceCglibInterceptor implements CglibInterceptor {

    private static final Logger log = LoggerFactory.getLogger(PerformanceCglibInterceptor.class);
    
    // 性能警告阈值（毫秒）
    private final long thresholdMillis;
    
    public PerformanceCglibInterceptor() {
        this(1000); // 默认阈值为1秒
    }
    
    public PerformanceCglibInterceptor(long thresholdMillis) {
        this.thresholdMillis = thresholdMillis;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        long startTime = System.currentTimeMillis();
        log.info("【CGLIB性能监控】开始执行方法: {}", method.getName());

        try {
            // 调用原始方法
            Object result = proxy.invokeSuper(obj, args);

            // 记录执行时间
            long executionTime = System.currentTimeMillis() - startTime;
            log.info("【CGLIB性能监控】方法执行完成: {}, 耗时: {}ms", method.getName(), executionTime);

            // 如果执行时间超过阈值，记录警告
            if (executionTime > thresholdMillis) {
                log.warn("【CGLIB性能警告】方法执行时间过长: {}, 耗时: {}ms, 超过阈值: {}ms", 
                        method.getName(), executionTime, thresholdMillis);
            }

            return result;
        } catch (Exception e) {
            log.error("【CGLIB性能监控】方法执行异常: {}, 异常: {}", method.getName(), e.getMessage(), e);
            throw e;
        }
    }
} 