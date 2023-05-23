package com.github.roveraven.repository;

import com.github.roveraven.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public User save(String userName);
    public Optional<User> findByName(String userName);
}
