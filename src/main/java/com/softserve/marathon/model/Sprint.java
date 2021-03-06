package com.softserve.marathon.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

import static javax.persistence.CascadeType.REFRESH;
import static javax.persistence.CascadeType.REMOVE;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"tasks"})
@EqualsAndHashCode(exclude = {"tasks"})
public class Sprint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "Field title can't be empty")
    private String title;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate finishDate;

    @OneToMany(cascade = {REFRESH, REMOVE}, mappedBy = "sprint")
    private Set<Task> tasks = new LinkedHashSet<>();

    @ManyToOne
    @JoinColumn(name = "marathon_id")
    private Marathon marathon;
}
