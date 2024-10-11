package com.trennble.web.spring.retry;

import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RetryService {

    int count = 0;

    @Retryable(value = {RuntimeException.class}, interceptor = "retryInterceptor")
    public void publicRetryMethod(String a){
        count++;
        // if (count%4 == 0){
        //     return;
        // }
        log.info("publicRetryMethod");
        throw new RuntimeException();
    }

    @Recover
    public void recover(RuntimeException e){
        log.info("recover");
        throw e;
    }

    /**
     * 重试失败后按照参数匹配Recover方法
     */
    @Recover
    public void recover(RuntimeException e, String a){
        log.info("recover a " + a);
        throw e;
    }
}
