package com.trennble.dynamicproxy.jdk;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 动态代理示例类
 * 演示如何使用JDK动态代理
 */
@Slf4j
@Component
public class DynamicProxyExample {
    
    // private static final Logger log = LoggerFactory.getLogger(DynamicProxyExample.class);
    
    /**
     * 示例接口
     */
    public interface UserService {
        void addUser(String username);
        String getUserInfo(Long userId);
        boolean deleteUser(Long userId);
    }
    
    /**
     * 接口实现类
     */
    public static class UserServiceImpl implements UserService {
        @Override
        public void addUser(String username) {
            log.info("添加用户: {}", username);
            // 实际业务逻辑...
        }
        
        @Override
        public String getUserInfo(Long userId) {
            log.info("获取用户信息, userId: {}", userId);
            // 实际业务逻辑...
            return "User: " + userId;
        }
        
        @Override
        public boolean deleteUser(Long userId) {
            log.info("删除用户, userId: {}", userId);
            // 实际业务逻辑...
            return true;
        }
    }
    
    /**
     * 自定义方法拦截器
     */
    @Slf4j
    public static class PerformanceInterceptor implements MethodInterceptor {
        @Override
        public Object intercept(Object target, java.lang.reflect.Method method, Object[] args) throws Throwable {
            long startTime = System.currentTimeMillis();
            log.info("【性能拦截器】调用开始 - 方法: {}", method.getName());
            
            try {
                Object result = method.invoke(target, args);
                
                long endTime = System.currentTimeMillis();
                log.info("【性能拦截器】调用结束 - 方法: {}, 耗时: {}ms", method.getName(), (endTime - startTime));
                
                return result;
            } catch (Exception e) {
                log.error("【性能拦截器】调用异常 - 方法: {}, 异常: {}", method.getName(), e.getMessage(), e);
                throw e;
            }
        }
    }
    
    /**
     * 演示使用动态代理
     */
    public void demonstrateProxy() {
        // 创建原始服务实例
        UserService userServiceImpl = new UserServiceImpl();
        
        // 使用默认代理
        UserService defaultProxy = DynamicProxyFactory.createProxy(UserService.class, userServiceImpl);
        
        // 使用自定义拦截器的代理
        UserService customProxy = DynamicProxyFactory.createProxy(
                UserService.class, 
                userServiceImpl, 
                new PerformanceInterceptor()
        );
        
        // 调用默认代理
        log.info("===== 使用默认代理 =====");
        defaultProxy.addUser("张三");
        String userInfo = defaultProxy.getUserInfo(1001L);
        boolean deleted = defaultProxy.deleteUser(1001L);
        
        // 调用自定义拦截器的代理
        log.info("===== 使用自定义拦截器的代理 =====");
        customProxy.addUser("李四");
        userInfo = customProxy.getUserInfo(1002L);
        deleted = customProxy.deleteUser(1002L);
    }

    public static void main(String[] args) {
        new DynamicProxyExample().demonstrateProxy();
    }
} 