package com.trennble.spi;

public class MysqlDataBaseSPIImpl implements DataBaseSPI {
 
    @Override
    public void dataBaseOperation() {
        System.out.println("Operate Mysql database!!!");
    }
}