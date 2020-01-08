package com.trennble.web.spring.init;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitSequenceBean implements InitializingBean {

    public InitSequenceBean() {
        System.out.println("InitSequenceBean: constructor");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("InitSequenceBean: postConstruct");
    }

    public void initMethod() {
        System.out.println("InitSequenceBean: init-method");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitSequenceBean: afterPropertiesSet");
    }
}