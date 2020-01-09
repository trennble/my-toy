package com.trennble.web.spring.condition;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

/**
 * @ClasssName BeanA
 * @Description TODO
 * @Author sycamore
 * @Date 2020-1-8 10:40
 * @Version 1.0
 **/
@Component
public class BeanA {

    /**
     * 不会被调用
     */
    @ConditionalOnBean(BeanB.class)
    public void condition(){
        System.out.println("a.condition");
    }
}
