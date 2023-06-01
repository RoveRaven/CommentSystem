package com.github.roveraven.service;

import com.github.roveraven.repository.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;
public interface UserService extends UserDetailsService {
    boolean save(User user);
    User findByUsername(String username);
    User save(String username);
    Optional<User> findById(Long userId);
    List<User> findAll();
    List<User> usergtList(Long idMin);
    boolean deleteUser(Long userId);
}
