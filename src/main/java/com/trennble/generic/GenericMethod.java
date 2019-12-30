package com.trennble.generic;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClasssName GenericMethod
 * @Description TODO
 * @Author sycamore
 * @Date 2019-12-4 17:38
 * @Version 1.0
 **/
public class GenericMethod {

    @Test
    public void testGenericMethod(){
        print("sss");

        final ArrayList<String> objects = Lists.newArrayList();
        objects.add("a");
        objects.add("b");
        printList(objects);
    }

    public <T> void print(T t){
        System.out.println(t);
    }

    public <T> void printList(List<T> t){
        System.out.println(t);
    }


}
