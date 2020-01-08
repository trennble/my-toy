package com.trennble.web.spring.init;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Init2SequenceBean implements InitializingBean {

    public Init2SequenceBean() {
        System.out.println("Init2SequenceBean: constructor");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("Init2SequenceBean: postConstruct");
    }

    public void initMethod() {
        System.out.println("Init2SequenceBean: init-method");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Init2SequenceBean: afterPropertiesSet");
    }
}