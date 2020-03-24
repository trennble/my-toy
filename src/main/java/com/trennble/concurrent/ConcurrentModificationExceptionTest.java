package com.trennble.concurrent;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @ClasssName ConcurrentModificationExceptionTest
 * @Description 测试对在循环中对list进行操作
 * @Author sycamore
 * @Date 2019-3-12 20:32
 * @Version 1.0
 **/
public class ConcurrentModificationExceptionTest {

    @Test
    public void test() {
        ArrayList<String> myList = new ArrayList<>();
        myList.add("1");
        myList.add("2");
        myList.add("3");
        myList.add("4");
        myList.add("5");
        Iterator<String> it = myList.iterator();

        // java.util.ArrayList.Itr 创建的时候就把当前操作数modCount记录到expectedModCount了，并且不会去更新这个值
        // java.util.ArrayList.Itr next操作会检测这个值，如果和创建时记录的不一致则会抛出异常
        // java.util.ArrayList.ListItr.add(java.lang.Object)/remove 方法会调用List中对用的方法，并在调用后把expectedModCount更新
        // java.util.ArrayList.remove(java.lang.Object) 方法会去更新modCount的值
        // 在第二次遍历的Itr.remove操作可以执行后，把expectedModCount更新了，所以第三次遍历就不会有报错
        // 但是第三次遍历使用List.remove方法没去更新，所以在第四次遍历的时候就会出错
        while(it.hasNext()) {
            String value = it.next();
            System.out.println(value);
            if(value.equals("2")) {
                it.remove();
                System.out.println("Itr.remove");
            }else if(value.equals("3")){
                myList.remove(value);
                System.out.println("List.remove");
            }
        }
    }
}
