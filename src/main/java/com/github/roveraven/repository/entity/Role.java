package com.github.roveraven.repository.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;


@Entity(name = "roles")
@Data
public class Role implements GrantedAuthority {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch= FetchType.LAZY)
    @JoinTable(name = "users_roles", joinColumns =
    @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<User> roleUsers;

    public Role(Long id, String name) {
        this.id=id;
        this.name=name;
    }

    public Role() {
    }
    @Override
    public String getAuthority() {
        return getName();
    }
}
