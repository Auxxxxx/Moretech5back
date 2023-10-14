package com.example.moretech5back.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
@AllArgsConstructor
public class JsonParserService {
    public JsonElement getRoot(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder builder = new StringBuilder();
        while (reader.ready()) {
            builder.append(reader.readLine()).append("\n");
        }
        return JsonParser.parseString(builder.toString());
    }
}
