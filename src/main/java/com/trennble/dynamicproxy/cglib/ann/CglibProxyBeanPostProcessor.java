package com.trennble.dynamicproxy.cglib.ann;

import com.trennble.dynamicproxy.cglib.CglibInterceptor;
import com.trennble.dynamicproxy.cglib.CglibProxyFactory;
import com.trennble.dynamicproxy.cglib.PerformanceCglibInterceptor;
import com.trennble.dynamicproxy.cglib.TransactionCglibInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * CGLIB代理Bean后置处理器
 * 用于处理带有CglibProxyAnnotation注解的Bean
 */
@Component
public class CglibProxyBeanPostProcessor implements BeanPostProcessor {

    private static final Logger log = LoggerFactory.getLogger(CglibProxyBeanPostProcessor.class);

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> targetClass = bean.getClass();
        
        // 检查类是否带有CglibProxyAnnotation注解
        if (targetClass.isAnnotationPresent(CglibProxyAnnotation.class)) {
            log.info("发现带有@CglibProxyAnnotation注解的Bean: {}", beanName);
            
            // 获取注解
            CglibProxyAnnotation annotation = targetClass.getAnnotation(CglibProxyAnnotation.class);
            CglibProxyAnnotation.InterceptorType interceptorType = annotation.type();
            
            // 根据拦截器类型创建拦截器
            CglibInterceptor interceptor;
            switch (interceptorType) {
                case TRANSACTION:
                    log.info("为Bean [{}] 使用事务拦截器", beanName);
                    interceptor = new TransactionCglibInterceptor();
                    break;
                case PERFORMANCE:
                default:
                    log.info("为Bean [{}] 使用性能监控拦截器", beanName);
                    interceptor = new PerformanceCglibInterceptor();
                    break;
            }
            
            // 创建代理
            Object proxy = CglibProxyFactory.createProxy(targetClass, interceptor);
            log.info("成功为Bean [{}] 创建CGLIB代理", beanName);
            
            return proxy;
        }
        
        return bean;
    }
} 