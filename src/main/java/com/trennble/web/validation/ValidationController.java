package com.trennble.web.validation;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

/**
 * @ClasssName ValidationController
 * @Description TODO
 * @Author sycamore
 * @Date 2019-3-7 10:31
 * @Version 1.0
 **/
@RestController
@RequestMapping("validation")
public class ValidationController {


    /**
     * 正常请求：{}
     * @param outer
     * @return
     */
    @PostMapping("validationParam")
    public String validationParam(@RequestBody @NotNull Outer outer){
        return "hello";
    }

    /**
     * 正常返回：
     * {
     * 	"beautiful": {
     * 		"@class": "com.trennble.web.validation.Son"
     *        },
     * 	"handsome": {
     * 		"name": "name",
     * 		"@class": "com.trennble.web.validation.Son"
     *    }
     * }
     * 抛出异常:
     * {}
     * 抛出异常：
     * {
     * 	"beautiful": {
     * 		"name": "name",
     * 		"@class": "com.trennble.web.validation.Son"
     *        },
     * 	"handsome": {
     * 		"@class": "com.trennble.web.validation.Son"
     *    }
     * }
     * @param outer
     * @return
     */
    @PostMapping("validationFieldInParamField")
    public String validationFieldInParamField(@RequestBody @Valid Outer outer){
        return "hello";
    }
}
