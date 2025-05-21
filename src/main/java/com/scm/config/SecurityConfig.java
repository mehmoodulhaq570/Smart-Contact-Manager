package com.scm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class SecurityConfig {
    private InMemoryUserDetailsManager inMemoryUserDetailsManager;

    // user create and login using java code with memory service
    public UserDetailsService userDetailsService() {
        // Create an in-memory user details manager
        UserDetails user1 = User
        .withDefaultPasswordEncoder()
        .username("admin123")
        .password("admin123")
        .roles("ADMIN","USER")
        .build();

        UserDetails user2 = User
        .withDefaultPasswordEncoder()
        .username("user123")
        .password("user123")
        //.roles("ADMIN","USER")
        .build();

        var inMemoryUserDetailsManager = new InMemoryUserDetailsManager(user1, user2);  
        return inMemoryUserDetailsManager;
    }

}
