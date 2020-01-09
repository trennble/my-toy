package com.trennble.web.spring.condition;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

/**
 * @ClasssName BeanB
 * @Description TODO
 * @Author sycamore
 * @Date 2020-1-8 10:40
 * @Version 1.0
 **/
@Component
public class BeanB {

    /**
     * 不会被调用
     */
    @ConditionalOnBean(BeanA.class)
    public void condition(){
        System.out.println("b.condition");
    }
}
