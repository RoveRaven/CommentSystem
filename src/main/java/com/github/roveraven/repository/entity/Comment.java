package com.github.roveraven.repository.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.awt.Image;
import java.sql.Blob;
import java.sql.Types;
import java.time.ZonedDateTime;
import java.util.Set;

import org.hibernate.annotations.JdbcTypeCode;
@Data
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @JoinColumn(name="parent_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Comment parentComment;
    
    @Transient
    private Long parentId;
    
    @OneToMany(mappedBy = "parentComment", fetch = FetchType.LAZY)
    private Set<Comment> subComments;
    
    @Column(name="level")
    private Integer level;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(name = "time")
    private ZonedDateTime commentTime;

    @Column(name = "text")
    private String text;  
    
    @JdbcTypeCode(Types.BINARY)
    @Column(name="image")
    private Blob image;           
     

}
