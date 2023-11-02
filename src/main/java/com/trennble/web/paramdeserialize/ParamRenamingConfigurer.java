package com.trennble.web.paramdeserialize;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

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