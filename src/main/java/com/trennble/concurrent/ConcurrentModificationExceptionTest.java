package com.trennble.concurrent;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
        myList.removeIf(s -> s.equals("2"));

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

    /**
     * arrayList1 只有一次循环，第一个数据被删除的时候，size会变成1，cursor也是1，所以会跳过
     * public boolean hasNext() {
     *    return cursor != size;
     * }
     *
     * arraylist2 在第二次循环的时候，数据被删除，size变为1，cursor变为2，所以会进入下一次的next()操作
     */
    @Test
    public void testModify(){
        ArrayList<String> arrayList1 = new ArrayList<String>();
        arrayList1.add("1");
        arrayList1.add("2");
        try {
            Iterator<String> iterator = arrayList1.iterator();
            while (iterator.hasNext()) {
                System.out.println("list1 ready to read");
                String s = iterator.next();
                System.out.println("list1 read "+s);
                if ("1".equals(s)) {
                    arrayList1.remove(s);
                }
                System.out.println("list1 read complete");
            }
        } catch (Exception e) {
            System.out.println("list1 throws a exception");
            e.printStackTrace();
        }
        ArrayList<String> arrayList2 = new ArrayList<String>();
        arrayList2.add("2");
        arrayList2.add("1");
        try {
            Iterator<String> iterator = arrayList2.iterator();
            while (iterator.hasNext()) {
                System.out.println("list2 ready to read");
                String s = iterator.next();
                System.out.println("list2 read "+s);
                if ("1".equals(s)) {
                    arrayList2.remove(s);
                }
                System.out.println("list2 read complete");
            }
        } catch (Exception e) {
            System.out.println("list2 throws a exception");
            e.printStackTrace();
        }
    }
}
