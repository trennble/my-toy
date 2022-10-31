package com.trennble.spi;

import java.util.ServiceLoader;

public class JdkSpiTest {
 
    public static void main(String args[]){
        // 加载jdk.spi.DataBaseSPI文件中DataBaseSPI的实现类(懒加载)
        ServiceLoader<DataBaseSPI> dataBaseSpis = ServiceLoader.load(DataBaseSPI.class);
        // ServiceLoader实现了Iterable，故此处可以使用for循环遍历加载到的实现类
        for(DataBaseSPI spi : dataBaseSpis){
            spi.dataBaseOperation();
        }
    }
}