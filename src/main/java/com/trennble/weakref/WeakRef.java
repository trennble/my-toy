package com.trennble.weakref;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.lang.ref.WeakReference;

/**
 * @ClasssName WeakRef
 * @Description TODO
 * @Author sycamore
 * @Date 2019-9-9 10:15
 * @Version 1.0
 **/
public class WeakRef {

    /**
     * 当垃圾收集器工作时，无论当前内存是否足够，都会回收掉**只被弱引用关联的对象**
     * @param args
     */
    public static void main(String[] args) {

        User user = new User("hello", "123");
        WeakReference<User> userWeakReference = new WeakReference<>(user);
        System.out.println(userWeakReference.get());
        System.gc(); //强制执行GC
        System.runFinalization();
        System.out.println(userWeakReference.get());
        user = null; //断开引用
        System.gc(); //强制执行GC
        System.runFinalization();
        System.out.println(userWeakReference.get());
    }

    @AllArgsConstructor
    @ToString
    private static class User {
        private String userName;
        private String userPwd;
    }
}
