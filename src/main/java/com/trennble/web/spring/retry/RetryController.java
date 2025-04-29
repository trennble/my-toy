package com.trennble.web.spring.retry;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("retry")
public class RetryController {

    @Resource
    private RetryService retryService;

    @GetMapping("privateRetry")
    public String privateRetry(){
        privateRetryMethod();
        return "hello";
    }

    @GetMapping("publicRetry")
    public String publicRetry(){
        publicRetryMethod();
        return "hello";
    }

    /**
     * 只有这种方式才会成功重试
     */
    @GetMapping("serviceRetry")
    public String serviceRetry(@RequestParam Integer passAt){
        retryService.publicRetryMethod(passAt);
        return "hello";
    }

    @Retryable(value = {RuntimeException.class})
    private void privateRetryMethod(){
        log.info("privateRetryMethod");
        throw new RuntimeException();
    }

    @Retryable(value = {RuntimeException.class})
    public void publicRetryMethod(){
        log.info("publicRetryMethod");
        throw new RuntimeException();
    }


}
