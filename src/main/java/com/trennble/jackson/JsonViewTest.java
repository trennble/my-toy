package com.trennble.jackson;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.experimental.Accessors;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @ClasssName JsonViewTest
 * @Description TODO
 * @Author sycamore
 * @Date 2019-9-17 11:27
 * @Version 1.0
 **/
public class JsonViewTest {

    interface View{
        interface Information{}
        interface Detail extends Information{}
    }

    @Data
    @Accessors(chain = true)
    private static class User{
        @JsonView(View.Information.class)
        private String id;
        @JsonView(View.Detail.class)
        private String name;
        private String addr;
    }

    @Test
    public void test() throws JsonProcessingException {
        User user = new User();
        user.setId("1").setName("john").setAddr("earth");
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
        String idStr = mapper.writerWithView(View.Information.class).writeValueAsString(user);
        assertEquals(idStr,"{\"id\":\"1\"}");

        String detail = mapper.writerWithView(View.Detail.class).writeValueAsString(user);
        assertEquals(detail,"{\"id\":\"1\",\"name\":\"john\"}");
    }
}
