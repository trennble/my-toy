package com.trennble.dynamicproxy.jdk.ann;

import com.trennble.dynamicproxy.jdk.DynamicProxyFactory;
import com.trennble.dynamicproxy.jdk.MethodInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Modifier;

/**
 * 代理Bean后置处理器
 * 用于处理带有ProxyAnnotation注解的Bean
 */
@Component
public class ProxyBeanPostProcessor implements BeanPostProcessor {

    private static final Logger log = LoggerFactory.getLogger(ProxyBeanPostProcessor.class);

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> targetClass = bean.getClass();
        
        // 检查Bean是否有ProxyAnnotation注解
        if (targetClass.isAnnotationPresent(ProxyAnnotation.class)) {
            log.info("发现带有@ProxyAnnotation注解的Bean: {}", beanName);
            
            // 获取注解中的拦截器类
            ProxyAnnotation annotation = targetClass.getAnnotation(ProxyAnnotation.class);
            Class<? extends MethodInterceptor> interceptorClass = annotation.interceptor();
            
            try {
                // 创建拦截器实例
                MethodInterceptor interceptor = interceptorClass.newInstance();
                
                // 查找Bean实现的所有接口
                Class<?>[] interfaces = ClassUtils.getAllInterfacesForClass(targetClass);
                if (interfaces.length == 0) {
                    log.warn("Bean [{}] 没有实现任何接口，无法创建JDK动态代理", beanName);
                    return bean;
                }
                
                // 选择第一个非内部接口作为代理接口
                Class<?> proxyInterface = null;
                for (Class<?> intf : interfaces) {
                    if (!intf.isInterface() || intf.isAnnotation() || Modifier.isPrivate(intf.getModifiers())) {
                        continue;
                    }
                    proxyInterface = intf;
                    break;
                }
                
                if (proxyInterface == null) {
                    log.warn("Bean [{}] 没有适合的接口用于创建代理", beanName);
                    return bean;
                }
                
                log.info("为Bean [{}] 创建代理，使用接口: {}, 拦截器: {}", 
                        beanName, proxyInterface.getName(), interceptorClass.getName());
                
                // 创建代理
                @SuppressWarnings("unchecked")
                Object proxy = DynamicProxyFactory.createProxy((Class<Object>) proxyInterface, bean, interceptor);
                
                return proxy;
            } catch (Exception e) {
                log.error("为Bean [{}] 创建代理失败", beanName, e);
            }
        }
        
        return bean;
    }
} 