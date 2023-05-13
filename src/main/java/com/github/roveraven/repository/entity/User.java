package com.github.roveraven.repository.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;
    @Column(name = "name")
    private String userName;
    /*
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;            TODO
    @Column(name = "registration_date")
    private LocalDate registrationDate;     TODO
    @Column(name = "avatar")
    private Image avatar;       TODO
     */
    @OneToMany(mappedBy = "user")
    @Column(name = "comments")
    private List<Comment> comments;
}
