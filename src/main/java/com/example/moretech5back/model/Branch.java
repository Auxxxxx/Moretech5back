package com.example.moretech5back.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.data.geo.Point;

import java.io.Serializable;

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
    private Long load;
    private Double x;
    private Double y;
}
