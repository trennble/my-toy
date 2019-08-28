package com.trennble.web.validation;

import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClasssName Son
 * @Description TODO
 * @Author sycamore
 * @Date 2019-3-7 10:36
 * @Version 1.0
 **/
@Data
@JsonTypeName(value = "son")
public class Son extends Father {
    @NotNull
    @ApiModelProperty("名称")
    private String name;
}
