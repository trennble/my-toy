package com.trennble.common.objectpool;

import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.Assert.assertEquals;

/**
 * @ClasssName ObjectPoolTest
 * @Description TODO
 * @Author sycamore
 * @Date 2020-1-9 10:01
 * @Version 1.0
 **/
public class ObjectPoolTest {

    private Logger logger = LoggerFactory.getLogger(StringBufferFactory.class);

    ObjectPool<StringBuffer> pool;

    List<StringBuffer> borrowedList = new ArrayList<>();

    @Before
    public void setup(){

        // 创建对象池配置
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        // 创建对象工厂
        PooledObjectFactory factory = new StringBufferFactory();
        // 创建对象池
        pool = new GenericObjectPool<>(factory, config);
    }

    /**
     * StringBufferFactory#makeObject
     * StringBufferFactory#create
     * StringBufferFactory#wrap
     * StringBufferFactory#activateObject
     * NumActive:1,NumIdle:0
     * StringBufferFactory#makeObject
     * StringBufferFactory#create
     * StringBufferFactory#wrap
     * StringBufferFactory#activateObject
     * NumActive:2,NumIdle:0
     * StringBufferFactory#makeObject
     * StringBufferFactory#create
     * StringBufferFactory#wrap
     * StringBufferFactory#passivateObject
     * NumActive:2,NumIdle:1
     * StringBufferFactory#passivateObject
     * NumActive:1,NumIdle:2
     * StringBufferFactory#destroyObject
     * NumActive:0,NumIdle:2
     * StringBufferFactory#destroyObject
     * StringBufferFactory#destroyObject
     * NumActive:0,NumIdle:0
     * @throws Exception
     */
    @Test
    public void testBorrow() throws Exception {
        StringBuffer objectA = pool.borrowObject();
        assertEquals(1,pool.getNumActive());
        assertEquals(0,pool.getNumIdle());
        logger.info(String.format("NumActive:%s,NumIdle:%s", pool.getNumActive(), pool.getNumIdle()));

        StringBuffer objectB = pool.borrowObject();
        assertEquals(2,pool.getNumActive());
        assertEquals(0,pool.getNumIdle());
        logger.info(String.format("NumActive:%s,NumIdle:%s", pool.getNumActive(), pool.getNumIdle()));

        pool.addObject();
        assertEquals(2,pool.getNumActive());
        assertEquals(1,pool.getNumIdle());
        logger.info(String.format("NumActive:%s,NumIdle:%s", pool.getNumActive(), pool.getNumIdle()));

        pool.returnObject(objectA);
        assertEquals(1,pool.getNumActive());
        assertEquals(2,pool.getNumIdle());
        logger.info(String.format("NumActive:%s,NumIdle:%s", pool.getNumActive(), pool.getNumIdle()));

        pool.invalidateObject(objectB);
        assertEquals(0,pool.getNumActive());
        assertEquals(2,pool.getNumIdle());
        logger.info(String.format("NumActive:%s,NumIdle:%s", pool.getNumActive(), pool.getNumIdle()));

        pool.clear();
        assertEquals(0,pool.getNumActive());
        assertEquals(0,pool.getNumIdle());
        logger.info(String.format("NumActive:%s,NumIdle:%s", pool.getNumActive(), pool.getNumIdle()));
    }


    // @Test
    public void testObjectPool() throws Exception {
        test();
    }

    public String test() throws Exception {
        // 创建对象池配置
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        // 创建对象工厂
        PooledObjectFactory factory = new StringBufferFactory();
        // 创建对象池
        ObjectPool<StringBuffer> pool = new GenericObjectPool<>(factory, config);
        StringReader in = new StringReader("abcdefg");
        StringBuffer buf = null;
        try {
            // 从池中获取对象
            buf = pool.borrowObject();

            // 使用对象
            for (int c = in.read(); c != -1; c = in.read()) {
                buf.append((char) c);
            }
            return buf.toString();
        } catch (Exception e) {
            try {
                // 出现错误将对象置为失效
                pool.invalidateObject(buf);
                // 避免 invalidate 之后再 return 抛异常
                buf = null;
            } catch (Exception ex) {
                // ignored
            }

            throw e;
        } finally {
            try {
                in.close();
            } catch (Exception e) {
                // ignored
            }

            try {
                if (null != buf) {
                    // 使用完后必须 returnObject
                    pool.returnObject(buf);
                }
            } catch (Exception e) {
                // ignored
            }
        }
    }


}
