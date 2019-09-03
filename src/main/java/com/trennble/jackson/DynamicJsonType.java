package com.trennble.jackson;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trennble.web.validation.Father;
import com.trennble.web.validation.Son;
import org.junit.Test;

import java.io.IOException;


/**
 * @ClasssName DynamicJsonType
 * @Description TODO
 * @Author sycamore
 * @Date 2019-9-2 18:13
 * @Version 1.0
 **/
public class DynamicJsonType {

    @Test
    public void test() throws IOException {
        Son son = new Son();
        son.setName("boring");
        ObjectMapper objectMapper=new ObjectMapper();
        String sonJson = objectMapper.writeValueAsString(son);
        Father father = objectMapper.readValue(sonJson, Father.class);
        assert father instanceof Son;
        String s = JSON.toJSONString(son);
        // InvalidTypeIdException: missing type id property '@class'
        Father fastFather = objectMapper.readValue(s, Father.class);
        assert !(fastFather instanceof Son);
    }

    /**
     * todo 没有找到不使用@class直接反序列化子类
     * 直接反序列化指定的子类
     * @throws IOException
     */
    @Test
    public void deserializeWithoutId() throws IOException {

        ObjectMapper objectMapper=new ObjectMapper();
        Son son = objectMapper.readValue("{\"name\":\"hello\"}", Son.class);

    }
}
