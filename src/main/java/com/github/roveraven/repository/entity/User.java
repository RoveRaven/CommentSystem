package com.github.roveraven.repository.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    /*
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;            TODO
    @Column(name = "registration_date")
    private LocalDate registrationDate;     TODO
    @Column(name = "avatar")
    private Image avatar;       TODO
     */
    @OneToMany(mappedBy = "user",
    fetch = FetchType.EAGER)
    //@JoinColumn(name = "id")
    @Column(name = "comments")
    @ToString.Exclude
    private List<Comment> comments;
}
