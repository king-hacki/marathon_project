package com.softserve.marathon.model;

import com.softserve.marathon.model.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
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
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "Field title can't be empty")
    private String title;

    @NotEmpty(message = "Task can't be empty")
    @Column(name = "task_description")
    private String taskDescription;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate created;

    @UpdateTimestamp
    private LocalDate updated;

    @OneToMany(cascade = {REFRESH, REMOVE}, mappedBy = "task")
    private Set<Progress> progresses = new LinkedHashSet<>();

    @ManyToOne
    @JoinColumn(name = "sprint_id")
    private Sprint sprint;

}
