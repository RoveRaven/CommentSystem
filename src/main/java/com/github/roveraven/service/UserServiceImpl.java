package com.github.roveraven.service;

import com.github.roveraven.repository.UserRepository;
import com.github.roveraven.repository.entity.Role;
import com.github.roveraven.repository.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @PersistenceContext
    private EntityManager em;
    UserRepository userRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder,
                           EntityManager em){
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.em = em;
    }
    @Override
    public boolean save(User user) {
        Optional<User> userFromDB = userRepository.findByUsername(user.getUsername());
        if (userFromDB.isPresent()) return false;
        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));  //temporary mock
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(new User());
    }

    @Override
    public User save(String username) {
        return null;
    }

    @Override
    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> usergtList(Long idMin) {
        return em.createQuery("SELECT u FROM User u WHERE u.id > :paramId", User.class)
                .setParameter("paramId", idMin).getResultList();
    }

    @Override
    public boolean deleteUser(Long userId) {
        if(userRepository.findById(userId).isPresent()){            //TODO CASCADE in DB - users with comments impossible to delete now
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).
                orElseThrow(()-> new UsernameNotFoundException(String.format("User with name #%s# not found", username)));
    }
}
