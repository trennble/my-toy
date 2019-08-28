package com.trennble.web.validation;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.validation.Valid;

/**
 * @ClasssName Father
 * @Description TODO
 * @Author sycamore
 * @Date 2019-3-7 10:36
 * @Version 1.0
 **/

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class")
@JsonSubTypes({
        @JsonSubTypes.Type(name = "son", value = Son.class)
})
public class Father {
}
