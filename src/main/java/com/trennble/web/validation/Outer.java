package com.trennble.web.validation;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
public class Outer {
    @NotNull
    @Valid
    private Father handsome;
    @NotNull
    private Father beautiful;

}