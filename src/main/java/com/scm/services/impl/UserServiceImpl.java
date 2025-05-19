package com.scm.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.entities.User;
import com.scm.helper.ResourceNotFoundException;
import com.scm.repositories.UserRepositories;
import com.scm.services.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepositories userRepositories;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        // user id have to be generated 
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        // encode password
        // user.setPassword(userId)
        return userRepositories.save(user);
    }

    @Override
    public Optional<User> getUserById(String userId) {
        Optional<User> user = userRepositories.findById(userId);
        if (user.isPresent()) {
            logger.info("User found with ID: {}", userId);
        } else {
            logger.warn("User not found with ID: {}", userId);
        }
        return user;
    }

    @Override
    public Optional<User> updateUser(User user) {
        User user2 = userRepositories.findById(user.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user2.setName(user.getName());
        user2.setPassword(user.getPassword()); 
        user2.setEmail(user.getEmail());
        user2.setPhoneNumber(user.getPhoneNumber());
        user2.setAbout(user.getAbout());
        user2.setProfilePicture(user.getProfilePicture());
        user2.setEnabled(user.isEnabled());
        user2.setEmailVerified(user.isEmailVerified());
        user2.setPhoneVerified(user.isPhoneVerified());
        user2.setProvider(user.getProvider());
        user2.setProviderId(user.getProviderId());
        
        // save user in database
        User save = userRepositories.save(user2);
        return Optional.ofNullable(save);
    }

    @Override
    public void deleteUser(String userId) {
        User user2 = userRepositories.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userRepositories.delete(user2);
    }

    @Override
    public boolean isUserExists(String userId) {
        User user2 = userRepositories.findById(userId).orElse(null);
        return user2 != null ? true : false;
        
    }

    @Override
    public boolean isUserExistByUserEmail(String email) {
        User user2 = userRepositories.findByEmail(email).orElse(null);
        return user2 != null ? true : false;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepositories.findAll();
    }

}
