package com.trennble.dynamicproxy.cglib.ann;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 演示服务
 * 使用CglibProxyAnnotation注解，将自动被CGLIB代理
 */
@Service
@CglibProxyAnnotation(type = CglibProxyAnnotation.InterceptorType.PERFORMANCE)
public class AnnotatedDemoService {
    
    private static final Logger log = LoggerFactory.getLogger(AnnotatedDemoService.class);
    
    public void doSomething(String input) {
        log.info("执行doSomething方法，参数: {}", input);
        try {
            // 模拟耗时操作
            Thread.sleep(300);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    public String processData(String data) {
        log.info("处理数据，输入: {}", data);
        try {
            // 模拟耗时操作
            Thread.sleep(700);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "Processed by CGLIB: " + data;
    }
    
    public int calculate(int a, int b) {
        log.info("执行计算，参数: a={}, b={}", a, b);
        try {
            // 模拟耗时操作
            Thread.sleep(1500); // 超过1秒，应该触发性能警告
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return a + b;
    }
}

/**
 * 示例事务服务
 * 使用事务拦截器
 */
@Service
@CglibProxyAnnotation(type = CglibProxyAnnotation.InterceptorType.TRANSACTION)
class AnnotatedTransactionService {
    
    private static final Logger log = LoggerFactory.getLogger(AnnotatedTransactionService.class);
    
    public boolean saveData(String data) {
        log.info("保存数据: {}", data);
        return true;
    }
    
    public String getData(String id) {
        log.info("获取数据: {}", id);
        return "Data: " + id;
    }
    
    public boolean updateData(String id, String value) {
        log.info("更新数据: id={}, value={}", id, value);
        return true;
    }
    
    public boolean deleteData(String id) {
        log.info("删除数据: {}", id);
        return true;
    }
    
    public boolean saveWithException(String data) {
        log.info("尝试保存数据（会抛出异常）: {}", data);
        if (true) {
            throw new RuntimeException("模拟保存时异常");
        }
        return true;
    }
} 