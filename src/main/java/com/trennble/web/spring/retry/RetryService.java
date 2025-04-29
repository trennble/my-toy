package com.trennble.web.spring.retry;

import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.RetryContext;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class RetryService {

    ThreadLocal<AtomicInteger> count=new ThreadLocal<>();

    @Retryable(value = {RuntimeException.class})
    public void publicRetryMethod(Integer a){
        AtomicInteger atomicInteger = count.get();
        if (atomicInteger == null) {
            atomicInteger = new AtomicInteger();
            count.set(atomicInteger);
        }
        int count = atomicInteger.incrementAndGet();
        if (count == a){
            log.info("success at {}", count);
            return;
        }
        log.info("fail at {}", count);
        throw new NoAvailableSecretPhoneException("测试异常");
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
