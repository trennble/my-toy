package com.trennble.dynamicproxy;

public class TargetImpl extends Target {

    @Override
    public int test(int i) {
        System.out.println(this.getClass());
        return i + 1;
    }
}