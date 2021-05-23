package com.trennble.dynamicproxy;

public class TargetImpl implements Target, ITarget {

    public TargetImpl(String arg){
        System.out.println("TargetImpl constructor "+ arg);
    }

    @Override
    public int test(int i) {
        System.out.println("test has been called");
        System.out.println(this.getClass());
        return i + 1;
    }

    @Override
    public String test2() {
        System.out.println("test2 has been called");
        return "test2";
    }
}