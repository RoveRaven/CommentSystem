package com.github.roveraven.repository.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.ZonedDateTime;
@Data
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Integer id;
    /*
    @Column(name = "parent_id")
    private Integer parentId;       TODO
     */
    //@JoinColumn (name = "user")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "comment_time")
    private ZonedDateTime commentTime;
    @Column(name = "text")
    private String text;
}
