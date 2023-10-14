package com.example.moretech5back.service;

import com.example.moretech5back.exception.BranchNotFoundException;
import com.example.moretech5back.model.Branch;
import com.example.moretech5back.model.DaySchedule;
import com.example.moretech5back.repository.BranchRepository;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.MappingJsonFactory;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class JsonParserService {
    public JsonArray getRoot(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder builder = new StringBuilder();
        while (reader.ready()) {
            builder.append(reader.readLine()).append("\n");
        }
        return JsonParser.parseString(builder.toString()).getAsJsonArray();
    }
}
