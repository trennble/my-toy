package com.trennble.collect;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClasssName MapTest
 * @Description TODO
 * @Author sycamore
 * @Date 2019-10-8 14:57
 * @Version 1.0
 **/
public class MapTest {

    @Test
    public void testNullKey(){
        Map<String,String> map=new HashMap<>();
        map.put("b","2");
        final String str = map.get("a");
        System.out.println(str);

    }
}
