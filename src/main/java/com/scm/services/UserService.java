package com.scm.services;

import java.util.List;
import java.util.Optional;

import com.scm.entities.User;

public interface UserService {
    User saveUser(User user);
    Optional<User> getUserById(String userId); // Optional provides a way to handle null values gracefully
    Optional<User> updateUser(User user);
    void deleteUser(String userId);
    boolean isUserExists(String userId);
    boolean isUserExistByUserEmail(String email);
    List<User> getAllUsers();
    // Add more methods here based on user service logic
    




}
