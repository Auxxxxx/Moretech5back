package com.example.moretech5back.web.httpData.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
//@EnableMethodSecurity(securedEnabled = true)
public class WebConfig implements WebMvcConfigurer {
    private final JsonService jsonService;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new RequestDataArgumentResolver(jsonService));
    }
}
