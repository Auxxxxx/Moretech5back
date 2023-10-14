package com.example.moretech5back.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(onlyExplicitlyIncluded = true)
@Table(name = "day_schedule")
public class DaySchedule {
    @Id
    @GeneratedValue
    @JsonIgnore
    @ToString.Include
    private Long id;
    private String hours;
    private Long load;
    private Boolean works;
}
