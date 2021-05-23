package com.trennble.dynamicproxy;

import org.openjdk.jmh.annotations.*;

import java.lang.reflect.Proxy;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
public class ProxyPerformanceTest {
    private Target nativeTest;
    private Target dynamicProxy;
    private Target cglibProxy;

    public static void main(String[] args) {
        // ProxyPerformanceTest benchmark = new ProxyPerformanceTest();
        // benchmark.setup();
        TargetImpl target = new TargetImpl("123");
        Target targetProxy = (Target) Proxy.newProxyInstance(JdkDynamicProxyTest.class.getClassLoader(),
                new Class<?>[]{Target.class},
                new JdkDynamicProxyTest(target));

        ITarget iTarget = (ITarget) Proxy.newProxyInstance(JdkDynamicProxyTest.class.getClassLoader(),
                new Class<?>[]{ITarget.class},
                new JdkDynamicProxyTest(target));

        targetProxy.test(1);
        iTarget.test2();
        // benchmark.cglibProxy.test(1);
        // benchmark.nativeTest();
        // benchmark.nativeTest();
        // benchmark.nativeTest();
    }

    @Setup
    public void setup() {
        nativeTest = new TargetImpl("hello");
        dynamicProxy = JdkDynamicProxyTest.newProxyInstance(nativeTest);
        cglibProxy = CglibProxyTest.newProxyInstance(TargetImpl.class);
    }


    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Fork(2)
    @Measurement(iterations = 5)
    @Warmup(iterations = 5)
    public void nativeTest() {
        nativeTest.test(1);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Fork(2)
    @Measurement(iterations = 5)
    @Warmup(iterations = 5)
    public void jdkTest() {
        dynamicProxy.test(1);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Fork(2)
    @Measurement(iterations = 5)
    @Warmup(iterations = 5)
    public void cglibTest() {
        cglibProxy.test(1);
    }
}
