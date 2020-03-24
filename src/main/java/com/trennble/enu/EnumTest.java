package com.trennble.enu;

import org.junit.Test;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toSet;

/**
 * @ClasssName EnumTest
 * @Description TODO
 * @Author sycamore
 * @Date 2020-1-22 14:46
 * @Version 1.0
 **/
public class EnumTest {

    @Test
    public void test(){
        Plant[] garden=new Plant[1];
        Map<Plant.LifeCycle, List<Plant>> collect = Arrays.stream(garden)
                .collect(groupingBy(p -> p.lifeCycle));
        EnumMap<Plant.LifeCycle, Set<Plant>> collect1 = Arrays.stream(garden)
                .collect(groupingBy(p -> p.lifeCycle,
                        () -> new EnumMap<>(Plant.LifeCycle.class), toSet()));
        System.out.println(collect1);
        System.out.println(collect);
    }

    private enum Plant{
        ;

        public LifeCycle lifeCycle;

        public enum LifeCycle{

        }
    }
}
