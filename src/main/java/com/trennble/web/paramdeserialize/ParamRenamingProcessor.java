package com.trennble.web.paramdeserialize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.ServletModelAttributeMethodProcessor;

/**
 * @author bosong
 * @date 2019-06-20
 */

public class ParamRenamingProcessor extends ServletModelAttributeMethodProcessor {

    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    public ParamRenamingProcessor(boolean annotationNotRequired) {
        super(annotationNotRequired);
    }

    @Override
    protected void bindRequestParameters(WebDataBinder binder, NativeWebRequest nativeWebRequest) {
        addConverter();
        super.bindRequestParameters(binder, nativeWebRequest);
    }


    private void addConverter() {
        ConfigurableWebBindingInitializer webBindingInitializer = (ConfigurableWebBindingInitializer)requestMappingHandlerAdapter.getWebBindingInitializer();
        GenericConversionService conversionService = (GenericConversionService) webBindingInitializer.getConversionService();
        conversionService.addConverter(new DateTimeConverter());
    }
}
