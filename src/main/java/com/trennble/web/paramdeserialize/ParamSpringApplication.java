package com.trennble.web.paramdeserialize;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@SpringBootApplication
public class ParamSpringApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ParamSpringApplication.class).run(args);
    }

    @PostMapping("test")
    public DateHolder test(DateHolder dateHolder){
        System.out.println(JSON.toJSONString(dateHolder));
        dateHolder.setLocalDateTime(LocalDateTime.now());
        return dateHolder;
    }

    /**
     * @author: jiangbo
     * @create: 2020-09-28
     **/
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DateHolder {

        LocalDateTime localDateTime;

        // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ")
        // public LocalDateTime getLocalDateTime(){
        //     return localDateTime;
        // }

    }
}
