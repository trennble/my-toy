package com.trennble.pyspark;

import java.util.List;
import java.util.Map;

public class TestPySpark {
    public void test(Map<String, List<String>> data){
        for (Map.Entry<String, List<String>> stringListEntry : data.entrySet()) {
            System.out.println(stringListEntry.getKey());
            for (String s : stringListEntry.getValue()) {
                System.out.println(s);
            }
        }
    }
}