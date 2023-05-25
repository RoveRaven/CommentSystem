package com.github.roveraven.service;

import com.github.roveraven.repository.entity.User;

import java.util.List;
import java.util.Optional;
public interface UserService {
    User save(User user);
    User save(String username);
    Optional<User> findById(Long userId);
    List<User> findAll();
}
