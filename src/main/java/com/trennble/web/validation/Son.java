package com.trennble.web.validation;

import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @ClasssName Son
 * @Description TODO
 * @Author sycamore
 * @Date 2019-3-7 10:36
 * @Version 1.0
 **/
@JsonTypeName(value = "son")
public class Son extends Father {
    @NotNull
    @ApiModelProperty("名称")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
