package com.trennble.generic;

import java.util.Collection;

/**
 * @ClasssName Generic
 * @Description TODO
 * @Author sycamore
 * @Date 2019-9-6 16:14
 * @Version 1.0
 **/
public class Generic {

    public void test(){

    }

    public void testSub(Collection<? extends Base> para){
        // 编译不通过
        // para.add(new Sub());
        // para.add(new Base());
    }

    public <T> void test(Collection<T> collection){
        collection.add((T)new Integer(12));
        collection.add((T)"123");
    }

    public void testSuper(Collection<? super Sub> para){
        para.add(new Sub());// 编译通过
        // para.add(new Base());// 编译不通过
    }


    private static class Base{ }

    private static class Sub extends Base{ }
}
