package com.trennble.web.spring.retry;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.interceptor.RetryInterceptorBuilder;
import org.springframework.retry.interceptor.RetryOperationsInterceptor;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

@Configuration
@EnableRetry
public class RetryConfig {

    @Value("${retry.backOff.period:1000}")
    private int retryBackOffPeriod;

    @Value("${retry.max.attempts:3}")
    private int retryMaxAttempts;
    
    @Bean
    public RetryOperationsInterceptor retryInterceptor() {
        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(retryMaxAttempts); // 设置最大重试次数
        
        FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();
        backOffPolicy.setBackOffPeriod(retryBackOffPeriod); // 设置重试间隔时间

        RetryTemplate retryTemplate = new RetryTemplate ();
        retryTemplate.setRetryPolicy(retryPolicy);
        retryTemplate.setBackOffPolicy(backOffPolicy);
        
        return RetryInterceptorBuilder.stateless()
                .retryOperations(retryTemplate)
                .build();
    }
}