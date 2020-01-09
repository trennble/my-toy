package com.trennble.common.objectpool;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringBufferFactory extends BasePooledObjectFactory<StringBuffer> {

    private Logger logger = LoggerFactory.getLogger(StringBufferFactory.class);


    @Override
    public PooledObject<StringBuffer> makeObject() throws Exception {
        logger.info("StringBufferFactory#makeObject");
        return super.makeObject();
    }

    // 创建一个新的对象
    @Override
    public StringBuffer create() {
        logger.info("StringBufferFactory#create");
        return new StringBuffer();
    }

    // 封装为池化对象
    @Override
    public PooledObject<StringBuffer> wrap(StringBuffer buffer) {
        logger.info("StringBufferFactory#wrap");
        return new DefaultPooledObject<>(buffer);
    }

    @Override
    public void activateObject(PooledObject<StringBuffer> p) throws Exception {
        logger.info("StringBufferFactory#activateObject");
        super.activateObject(p);
    }

    // 使用完返还对象时将 StringBuffer 清空
    @Override
    public void passivateObject(PooledObject<StringBuffer> pooledObject) {
        logger.info("StringBufferFactory#passivateObject");
        pooledObject.getObject().setLength(0);
    }

    @Override
    public boolean validateObject(PooledObject<StringBuffer> p) {
        logger.info("StringBufferFactory#validateObject");
        return super.validateObject(p);
    }

    @Override
    public void destroyObject(PooledObject<StringBuffer> p) throws Exception {
        logger.info("StringBufferFactory#destroyObject");
        super.destroyObject(p);
    }

}