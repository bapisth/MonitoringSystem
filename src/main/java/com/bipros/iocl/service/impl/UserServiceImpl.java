package com.bipros.iocl.service.impl;

import com.bipros.iocl.entities.Role;
import com.bipros.iocl.entities.User;
import com.bipros.iocl.repository.UserRepository;
import com.bipros.iocl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void createUser(User user) {
        createUserType(user, "USER");
    }

    @Override
    public void createAdmin(User user) {
        createUserType(user, "ADMIN");
    }

    private void createUserType(User user, String userType) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        Role userRole = new Role(userType);
        List<Role> roles = new ArrayList<>();
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public User findOne(String email) {
        return userRepository.getOne(email);
    }

    @Override
    public boolean isUserPresent(String email) {
        User u = userRepository.getOne(email);
        if (u != null)
            return true;

        return false;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findByName(String name) {
        return userRepository.findByNameLike(name);
    }
}
