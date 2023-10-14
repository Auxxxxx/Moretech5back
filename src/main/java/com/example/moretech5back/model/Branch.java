package com.example.moretech5back.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.geo.Point;

import java.io.Serializable;
import java.sql.Time;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(onlyExplicitlyIncluded = true)
@Table(name = "branch")
public class Branch implements Serializable {
    @Id
    @GeneratedValue
    @JsonIgnore
    @ToString.Include
    private Long id;
    private String name;
    private String address;
    @ManyToMany(cascade = CascadeType.ALL)
    private SortedMap<DayOfWeek, DaySchedule> dailyLoad;
    private Double grade;
    private Long amountOfReviews;
    private Long load;
    private Double x;
    private Double y;
}
