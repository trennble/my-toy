package com.trennble.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkDynamicProxyTest implements InvocationHandler {

    private TargetImpl target;

    public JdkDynamicProxyTest(TargetImpl target) {
        this.target = target;
    }

    public static Target newProxyInstance(Target target) {
        return null;
        // return (Target) Proxy.newProxyInstance(JdkDynamicProxyTest.class.getClassLoader(),
        //         new Class<?>[]{Target.class},
        //         new JdkDynamicProxyTest(target));

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // System.out.println(proxy.getClass());
        return method.invoke(target, args);
    }
}