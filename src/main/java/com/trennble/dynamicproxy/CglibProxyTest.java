package com.trennble.dynamicproxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxyTest implements MethodInterceptor {

    private CglibProxyTest() {
    }

    public static <T extends Target> Target newProxyInstance(Class<T> targetInstanceClazz) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targetInstanceClazz);
        CglibProxyTest callback = new CglibProxyTest();
        System.out.println("new obj" + callback);
        enhancer.setCallback(callback);
        return (Target) enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("invoke obj" + obj);
        System.out.println("before:" + this.getClass());
        System.out.println(obj.getClass());
        System.out.println(args);
        System.out.println(args.length);
        System.out.println(args[0]);
        Object o = proxy.invokeSuper(obj, args);
        System.out.println("after:" + this.getClass());
        return o;
    }

}