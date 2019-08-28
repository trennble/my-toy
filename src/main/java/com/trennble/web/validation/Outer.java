package com.trennble.web.validation;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class Outer {
    @NotNull
    @Valid
    private Father handsome;
    @NotNull
    private Father beautiful;

    public Father getHandsome() {
        return handsome;
    }

    public void setHandsome(Father handsome) {
        this.handsome = handsome;
    }

    public Father getBeautiful() {
        return beautiful;
    }

    public void setBeautiful(Father beautiful) {
        this.beautiful = beautiful;
    }
}