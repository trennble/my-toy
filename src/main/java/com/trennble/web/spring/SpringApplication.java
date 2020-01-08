package com.trennble.web.spring;

import com.trennble.web.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @ClasssName SpringApplication
 * @Description TODO
 * @Author sycamore
 * @Date 2020-1-3 9:26
 * @Version 1.0
 **/
@SpringBootApplication
public class SpringApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class).run(args);
    }

}
