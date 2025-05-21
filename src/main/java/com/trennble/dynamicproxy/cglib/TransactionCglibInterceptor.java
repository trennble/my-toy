package com.trennble.dynamicproxy.cglib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 事务CGLIB拦截器
 * 模拟事务处理逻辑
 */
public class TransactionCglibInterceptor implements CglibInterceptor {

    private static final Logger log = LoggerFactory.getLogger(TransactionCglibInterceptor.class);

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        // 检查方法是否需要事务（这里简单演示，实际应该基于注解或配置）
        boolean needTransaction = method.getName().startsWith("save") || 
                                  method.getName().startsWith("update") || 
                                  method.getName().startsWith("delete") || 
                                  method.getName().startsWith("insert");
        
        if (!needTransaction) {
            // 如果不需要事务，直接调用原始方法
            return proxy.invokeSuper(obj, args);
        }
        
        // 模拟开始事务
        log.info("【CGLIB事务】开始事务 - 方法: {}", method.getName());
        boolean transactionCompleted = false;
        
        try {
            // 执行目标方法
            Object result = proxy.invokeSuper(obj, args);
            
            // 模拟提交事务
            log.info("【CGLIB事务】提交事务 - 方法: {}", method.getName());
            transactionCompleted = true;
            
            return result;
        } catch (Exception e) {
            // 模拟回滚事务
            log.error("【CGLIB事务】回滚事务 - 方法: {}, 异常: {}", method.getName(), e.getMessage());
            throw e;
        } finally {
            if (!transactionCompleted) {
                log.warn("【CGLIB事务】事务未正常完成 - 方法: {}", method.getName());
            }
            log.info("【CGLIB事务】事务结束 - 方法: {}", method.getName());
        }
    }
} 