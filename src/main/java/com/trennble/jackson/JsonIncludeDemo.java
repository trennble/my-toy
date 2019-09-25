package com.trennble.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @ClasssName JsonIncludeDemo
 * @Author sycamore
 * @Date 2019-8-28 16:34
 * @Version 1.0
 **/
public class JsonIncludeDemo {

    /**
     * 测试在类上使用 @JsonInclude
     * @throws JsonProcessingException
     */
    @Test
    public void testJsonIncludeOnType() throws JsonProcessingException {
        IncludeOnType jid= new IncludeOnType();
        String jsonStr = new ObjectMapper().writeValueAsString(jid);
        System.out.println(jsonStr);
        assertEquals("{}",jsonStr);
    }

    @Test
    public void testJsonIncludeOnField() throws JsonProcessingException {
        IncludeOnField jid= new IncludeOnField();
        String jsonStr = new ObjectMapper().writeValueAsString(jid);
        assertEquals("{\"fieldB\":null}",jsonStr);
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private static class IncludeOnType{

        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String fieldA;
        private String fieldB;
    }

    @Data
    private static class IncludeOnField{

        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String fieldA;
        private String fieldB;
    }
}
