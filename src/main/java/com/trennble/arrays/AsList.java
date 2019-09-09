package com.trennble.arrays;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @ClasssName AsList
 * @Description TODO
 * @Author sycamore
 * @Date 2019-9-9 10:25
 * @Version 1.0
 **/
public class AsList {

    /**
     * java.util.Arrays#asList(java.lang.Object[]) 返回的是 java.util.Arrays.ArrayList
     * 而不是java.util.ArrayList
     */
    @Test
    public void errExample(){
        int[] intArr={1,2,3};

        // 基本类型是无法泛型化，所以返回类型是List<int[]>
        List<int[]> intArrList = Arrays.asList(intArr);
        assert intArrList.size() == 1;
        System.out.println(intArrList);
        System.out.println("#######");

        // asList产生的集合元素是直接引用作为参数的数组，所以当外部数组或集合改变时，数组和集合会同步变化
        String[] strArr={"hello","world"};
        List<String> strList = Arrays.asList(strArr);
        strArr[0] = "你好";
        assert strList.get(0).equals("你好");
        strList.set(1,"世界");
        assert strArr[1].equals("世界");
        System.out.println(Arrays.toString(strArr));
        System.out.println(strList);
        System.out.println("#######");

        // asList产生的集合并没有重写add,remove等方法
        try{
            strList.add("?");
        }catch (Exception e){
            System.out.println(e.getClass());
            assert e instanceof UnsupportedOperationException;
        }

        System.out.println("###correct####");
        // stream转换
        List<Integer> intList = Arrays.stream(intArr)
                .boxed()
                .collect(toList());
        System.out.println(intList);

        // ArrayList构造函数转换
        ArrayList<String> strings = new ArrayList<>(Arrays.asList(strArr));
        System.out.println(strings);

    }
}
