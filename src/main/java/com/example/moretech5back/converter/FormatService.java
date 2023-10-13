package com.example.moretech5back.converter;

import org.apache.commons.lang3.time.DurationFormatUtils;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Service
public class FormatService {
    public String toString(Duration duration) {
        return DurationFormatUtils.formatDuration(duration.toMillis(), "H:mm:ss", false);
    }

    public Duration toDuration(String duration) {
        int[] vals = Arrays.stream(duration.split(":")).mapToInt(Integer::parseInt).toArray();
        return Duration.ofHours(vals[0]).plusMinutes(vals[1]).plusSeconds(vals[2]);
    }

    public LocalDate toLocalDate(String date) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(date, dtf);
    }

    public String toString(LocalDate date) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dtf.format(date);
    }

    public LocalTime toLocalTime(String time) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        return LocalTime.parse(time, dtf);
    }

    public String toString(LocalTime time) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        return dtf.format(time);
    }

}
