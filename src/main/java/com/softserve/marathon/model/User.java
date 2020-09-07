package com.softserve.marathon.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.LinkedHashSet;
import java.util.Set;

import static javax.persistence.CascadeType.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"progresses", "marathons"})
@EqualsAndHashCode(exclude = {"progresses", "marathons"})
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    @Email(message = "Email is not valid")
    private String email;

    @NotEmpty(message = "First name can't be empty")
    private String firstName;

    @NotEmpty(message = "Last name can't be empty")
    private String lastName;

    @NotEmpty(message = "Password can't be empty")
    private String password;

    @ManyToMany(cascade = MERGE)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> role = new LinkedHashSet<>();

    @OneToMany(cascade = {REFRESH, REMOVE}, mappedBy = "user")
    private Set<Progress> progresses = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "user_marathon",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "marathon_id"))
    private Set<Marathon> marathons = new LinkedHashSet<>();
}
