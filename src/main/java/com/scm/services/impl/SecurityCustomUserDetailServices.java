package com.scm.services.impl;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.scm.repositories.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class SecurityCustomUserDetailServices implements UserDetailsService {

    @Autowired
    private UserRepositories userRepositories;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Here we can fetch the user from the database using the username
            return userRepositories.findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        
        }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
