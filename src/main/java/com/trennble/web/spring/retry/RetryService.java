package com.trennble.web.spring.retry;

import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RetryService {

    @Retryable(value = {RuntimeException.class})
    public void publicRetryMethod(){
        log.info("publicRetryMethod");
        throw new RuntimeException();
    }
}
