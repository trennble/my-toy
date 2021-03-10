package com.trennble.web.paramdeserialize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class ParamRenamingConfigurer extends WebMvcConfigurationSupport {

    @Override
    public FormattingConversionService mvcConversionService() {
        DateTimeConverter telephoneConverter = new DateTimeConverter();
        FormattingConversionService formattingConversionService = super.mvcConversionService();
        formattingConversionService.addConverter(telephoneConverter);
        return formattingConversionService;
    }
}