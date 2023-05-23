package com.github.roveraven.service;

import com.github.roveraven.repository.UserRepository;
import com.github.roveraven.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public User save(User user) {
        Optional<User> userFromDB = userRepository.findByName(user.getName());
        return userFromDB.map(value -> userRepository.save(value)).orElseGet(() -> userRepository.save(user));
    }

    @Override
    public User save(String userName) {
        return null;
    }

    @Override
    public Optional<User> findById(Integer userId) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return null;
    }
}
