package com.scm.repositories;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.scm.entities.User;

// User for database interaction, it will have all methods that can be used to interact with the database

@Repository
public interface UserRepositories extends JpaRepository<User, String> {
    // Custom query methods can be defined here if needed
    // extra method db related operations   
    // Custom finder methods

    Optional<User> findByEmail(String email); // Custom query to find user by email


    
} 

