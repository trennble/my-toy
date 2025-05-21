package com.trennble.dynamicproxy.jdk.ann;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 演示服务接口
 */
interface DemoService {
    void doSomething(String input);
    String processData(String data);
    int calculate(int a, int b);
}

/**
 * 演示服务实现
 * 使用ProxyAnnotation注解，将自动被代理
 */
@Service
@ProxyAnnotation
public class DemoServiceImpl implements DemoService {
    
    private static final Logger log = LoggerFactory.getLogger(DemoServiceImpl.class);
    
    @Override
    public void doSomething(String input) {
        log.info("执行doSomething方法，参数: {}", input);
        try {
            // 模拟耗时操作
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    @Override
    public String processData(String data) {
        log.info("处理数据，输入: {}", data);
        try {
            // 模拟耗时操作
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "Processed: " + data;
    }
    
    @Override
    public int calculate(int a, int b) {
        log.info("执行计算，参数: a={}, b={}", a, b);
        try {
            // 模拟耗时操作
            Thread.sleep(1200); // 超过1秒，应该触发性能警告
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return a + b;
    }
} 