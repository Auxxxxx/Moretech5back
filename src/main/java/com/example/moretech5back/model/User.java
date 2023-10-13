package com.example.moretech5back.web.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(onlyExplicitlyIncluded = true)
@Table(name = "_user")
public class User implements Serializable {
    @Id
    @GeneratedValue
    @JsonIgnore
    @ToString.Include
    private Long id;
    private String login;
    private String name;
    private String password;
}
