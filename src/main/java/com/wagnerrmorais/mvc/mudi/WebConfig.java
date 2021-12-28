package com.wagnerrmorais.mvc.mudi;

import com.wagnerrmorais.mvc.mudi.interceptor.InterceptadorAcessos;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new InterceptadorAcessos()).addPathPatterns("/**");
    }
}
