package com.trennble.web.autowire;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author: jiangbo
 * @create: 2020-10-26
 **/
@Service
public class ListValue {

    @Value("${web.list}")
    private List<String> test;

    @PostConstruct
    public void test(){
        System.out.println(test);
        System.out.println(test.contains("2"));
    }
}
