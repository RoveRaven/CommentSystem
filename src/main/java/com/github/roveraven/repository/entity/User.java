package com.github.roveraven.repository.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_name")
    private String username;

    @Column(name = "password")
    private String password;

    @Transient
    private String passwordConfirm;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns =
    @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    @Fetch(FetchMode.SUBSELECT)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Role> roles;

    @OneToMany(mappedBy = "user",
    fetch = FetchType.EAGER)
    @Column(name = "comments")
    @Fetch(FetchMode.SUBSELECT)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Comment> comments;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    /*
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;            TODO
    @Column(name = "registration_date")
    private LocalDate registrationDate;     TODO
    @Column(name = "avatar")
    private Image avatar;       TODO
    private boolean isBanned;    TODO
     */
}
