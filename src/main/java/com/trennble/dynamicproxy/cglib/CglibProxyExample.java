package com.trennble.dynamicproxy.cglib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * CGLIB代理示例类
 * 演示如何使用CGLIB动态代理
 */
@Component
public class CglibProxyExample {
    
    private static final Logger log = LoggerFactory.getLogger(CglibProxyExample.class);
    
    /**
     * 用户服务类（没有实现任何接口）
     */
    public static class UserService {
        public void addUser(String username) {
            log.info("添加用户: {}", username);
            // 实际业务逻辑...
        }
        
        public String getUserInfo(Long userId) {
            log.info("获取用户信息, userId: {}", userId);
            // 实际业务逻辑...
            return "User: " + userId;
        }
        
        public boolean deleteUser(Long userId) {
            log.info("删除用户, userId: {}", userId);
            // 实际业务逻辑...
            return true;
        }
    }
    
    /**
     * 订单服务类（没有实现任何接口）
     */
    public static class OrderService {
        public boolean saveOrder(String orderId, String productId, int quantity) {
            log.info("保存订单: orderId={}, productId={}, quantity={}", orderId, productId, quantity);
            // 实际业务逻辑...
            return true;
        }
        
        public String getOrderInfo(String orderId) {
            log.info("获取订单信息: orderId={}", orderId);
            // 实际业务逻辑...
            return "Order: " + orderId;
        }
        
        public boolean updateOrderStatus(String orderId, String status) {
            log.info("更新订单状态: orderId={}, status={}", orderId, status);
            // 实际业务逻辑...
            return true;
        }
        
        public void processOrder(String orderId) {
            log.info("处理订单: orderId={}", orderId);
            // 实际业务逻辑...
            try {
                // 模拟耗时操作
                Thread.sleep(1500); // 超过性能监控的默认阈值
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
    /**
     * 演示使用CGLIB代理（带有异常情况）
     */
    public static class ExceptionService {
        public void normalMethod() {
            log.info("正常方法执行");
        }
        
        public void exceptionMethod() {
            log.info("异常方法执行");
            throw new RuntimeException("模拟异常");
        }
        
        public boolean saveWithException(String data) {
            log.info("保存数据: {}", data);
            throw new RuntimeException("保存时发生异常");
        }
    }
    
    /**
     * 演示使用CGLIB代理
     */
    public void demonstrateCglibProxy() {
        log.info("===== 演示默认CGLIB代理 =====");
        // 创建UserService代理
        UserService userService = CglibProxyFactory.createProxy(UserService.class);
        userService.addUser("张三");
        userService.getUserInfo(1001L);
        userService.deleteUser(1002L);
        
        log.info("===== 演示性能监控CGLIB代理 =====");
        // 创建OrderService代理，使用性能监控拦截器
        OrderService orderService = CglibProxyFactory.createProxy(
                OrderService.class, 
                new PerformanceCglibInterceptor()
        );
        orderService.saveOrder("ORD-001", "PRD-001", 2);
        orderService.getOrderInfo("ORD-001");
        orderService.processOrder("ORD-001"); // 这个方法会超过性能监控阈值
        
        log.info("===== 演示事务CGLIB代理 =====");
        // 创建OrderService代理，使用事务拦截器
        OrderService transOrderService = CglibProxyFactory.createProxy(
                OrderService.class, 
                new TransactionCglibInterceptor()
        );
        transOrderService.saveOrder("ORD-002", "PRD-002", 1); // 需要事务
        transOrderService.getOrderInfo("ORD-002"); // 不需要事务
        transOrderService.updateOrderStatus("ORD-002", "SHIPPED"); // 需要事务
        
        log.info("===== 演示异常处理CGLIB代理 =====");
        // 创建ExceptionService代理
        ExceptionService exceptionService = CglibProxyFactory.createProxy(
                ExceptionService.class, 
                new TransactionCglibInterceptor()
        );
        try {
            exceptionService.normalMethod(); // 正常方法
            exceptionService.exceptionMethod(); // 这个方法会抛出异常
        } catch (Exception e) {
            log.info("捕获到异常: {}", e.getMessage());
        }
        
        try {
            exceptionService.saveWithException("重要数据"); // 这个方法会触发事务回滚
        } catch (Exception e) {
            log.info("捕获到保存异常: {}", e.getMessage());
        }
    }

    public static void main(String[] args) {
        CglibProxyExample cglibProxyExample = new CglibProxyExample();
        cglibProxyExample.demonstrateCglibProxy();
    }
}