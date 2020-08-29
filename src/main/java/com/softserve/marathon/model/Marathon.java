package com.softserve.marathon.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import java.util.LinkedHashSet;
import java.util.Set;

import static javax.persistence.CascadeType.REFRESH;
import static javax.persistence.CascadeType.REMOVE;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Marathon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "Field title can't be empty")
    private String title;

    @OneToMany(cascade = {REFRESH, REMOVE}, mappedBy = "marathon")
    private Set<Sprint> sprints = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "marathons")
    private Set<User> users = new LinkedHashSet<>();

}
