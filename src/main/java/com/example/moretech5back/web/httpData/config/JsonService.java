package com.example.moretech5back.web.httpData.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;

@Service
public class JsonService {
    public Object fromJson(String json, Type type) {
        try {
            Class<?> tClass = com.fasterxml.jackson.databind.type.TypeFactory.rawClass(type);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.findAndRegisterModules();
            return objectMapper.readValue(json, tClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
