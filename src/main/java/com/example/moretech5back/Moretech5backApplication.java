package com.example.moretech5back;

import com.example.moretech5back.model.Branch;
import com.example.moretech5back.model.DaySchedule;
import com.example.moretech5back.service.BranchService;
import com.example.moretech5back.service.JsonParserService;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;

import java.io.File;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.time.DayOfWeek;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
@Slf4j
public class Moretech5backApplication extends SpringBootServletInitializer {
    @Value("classpath:data/offices.txt")
    private Resource officesFile;
    private List<DayOfWeek> dayOfWeekList = new ArrayList<>() {
        {
            add(DayOfWeek.MONDAY);
            add(DayOfWeek.TUESDAY);
            add(DayOfWeek.WEDNESDAY);
            add(DayOfWeek.THURSDAY);
            add(DayOfWeek.FRIDAY);
            add(DayOfWeek.SATURDAY);
            add(DayOfWeek.SUNDAY);
        }
    };

    public static void main(String[] args) {
        try {
            log.info(InetAddress.getLocalHost().toString());
        } catch (UnknownHostException e) {
            log.info("unknown_host");
        }
        SpringApplication.run(Moretech5backApplication.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(JsonParserService jsonParserService,
                                               BranchService branchService) {
        return args -> {
            JsonArray array = jsonParserService.getRoot(officesFile.getInputStream());
            parseOffices(branchService, array);

        };
    }

    private void parseOffices(BranchService branchService, JsonArray array) {
        for (JsonElement jsonElement : array) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            String name = jsonObject.get("salePointName").getAsString();
            String address = jsonObject.get("address").getAsString();
            SortedMap<DayOfWeek, DaySchedule> dailyLoad = new TreeMap<>(this::compare);
            for (DayOfWeek dayOfWeek : dayOfWeekList) {
                String hours;
                boolean works;
                Long load = ThreadLocalRandom.current().nextLong(1, 11);
                if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
                    hours = "выходной";
                    works = false;
                } else {
                    hours = "09:00-18:00";
                    works = true;
                }
                DaySchedule daySchedule = DaySchedule.builder()
                        .hours(hours)
                        .load(load)
                        .works(works)
                        .build();
                dailyLoad.put(dayOfWeek, daySchedule);
            }
            Long load = ThreadLocalRandom.current().nextLong(1, 11);
            double x = jsonObject.get("latitude").getAsDouble();
            double y = jsonObject.get("longitude").getAsDouble();

            Branch branch = Branch.builder()
                    .name(name)
                    .address(address)
                    .dailyLoad(dailyLoad)
                    .load(load)
                    .x(x)
                    .y(y)
                    .build();
            branchService.save(branch);
        }
    }

    private void parseAtms(BranchService branchService, JsonArray array) {
        for (JsonElement jsonElement : array) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            String name = jsonObject.get("salePointName").getAsString();
            String address = jsonObject.get("address").getAsString();
            SortedMap<DayOfWeek, DaySchedule> dailyLoad = new TreeMap<>(this::compare);
            for (DayOfWeek dayOfWeek : dayOfWeekList) {
                String hours;
                boolean works;
                Long load = ThreadLocalRandom.current().nextLong(1, 11);
                if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
                    hours = "выходной";
                    works = false;
                } else {
                    hours = "09:00-18:00";
                    works = true;
                }
                DaySchedule daySchedule = DaySchedule.builder()
                        .hours(hours)
                        .load(load)
                        .works(works)
                        .build();
                dailyLoad.put(dayOfWeek, daySchedule);
            }
            Long load = ThreadLocalRandom.current().nextLong(1, 11);
            double x = jsonObject.get("latitude").getAsDouble();
            double y = jsonObject.get("longitude").getAsDouble();

            Branch branch = Branch.builder()
                    .name(name)
                    .address(address)
                    .dailyLoad(dailyLoad)
                    .load(load)
                    .x(x)
                    .y(y)
                    .build();
            branchService.save(branch);
        }
    }

    private int compare(DayOfWeek d1, DayOfWeek d2) {
        return Integer.compare(dayOfWeekList.indexOf(d1), dayOfWeekList.indexOf(d2));
    }

}

