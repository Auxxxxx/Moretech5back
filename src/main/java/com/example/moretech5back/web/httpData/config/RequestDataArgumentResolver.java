package com.example.moretech5back.web.httpData.config;

import com.example.moretech5back.web.util.RequestData;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
public class RequestDataArgumentResolver implements HandlerMethodArgumentResolver {
    private final JsonService jsonService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(RequestData.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        HttpInputMessage inputMessage = new ServletServerHttpRequest(servletRequest);
        String requestBodyContent = StreamUtils.copyToString(
                inputMessage.getBody(), StandardCharsets.UTF_8);
        Type genericType = parameter.getGenericParameterType();
        return jsonService.fromJson(requestBodyContent, genericType);
    }
}
