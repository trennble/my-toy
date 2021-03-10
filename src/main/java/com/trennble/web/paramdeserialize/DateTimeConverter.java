package com.trennble.web.paramdeserialize;

import org.springframework.core.convert.converter.Converter;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


// @ControllerAdvice
public class DateTimeConverter implements Converter<String, LocalDateTime> {

    @Override
    public LocalDateTime convert(String source) {
        long ts = Long.parseLong(source);
        if (source.length() > 10) {
            ts = ts / 1000;
        }
        return LocalDateTime.ofEpochSecond(ts, 0, ZoneOffset.ofHours(8));
    }

    // @InitBinder
    // public void initBinder(final WebDataBinder webdataBinder) {
    //     webdataBinder.registerCustomEditor(LocalDateTime.class, new PropertyEditorSupport() {
    //         @Override
    //         public void setAsText(String text) throws IllegalArgumentException {
    //             Long ts = Long.valueOf(text);
    //             setValue(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
    //         }
    //     });
    // }
}