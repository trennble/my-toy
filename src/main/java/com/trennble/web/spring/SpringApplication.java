package com.trennble.web.spring;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * @ClasssName SpringApplication
 * @Description TODO
 * @Author sycamore
 * @Date 2020-1-3 9:26
 * @Version 1.0
 **/
@RestController
@RequestMapping
@SpringBootApplication
public class SpringApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringApplication.class).run(args);
    }

    @GetMapping("arrayGetTest")
    public ArrayList<String> validationFieldInParamField(@RequestParam("arr") ArrayList<String> strings){
        return strings;
    }
}
