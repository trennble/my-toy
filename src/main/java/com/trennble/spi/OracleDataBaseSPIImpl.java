package com.trennble.spi;

public class OracleDataBaseSPIImpl implements DataBaseSPI {
 
    @Override
    public void dataBaseOperation() {
        System.out.println("Operate Oracle database!!!");
    }
}