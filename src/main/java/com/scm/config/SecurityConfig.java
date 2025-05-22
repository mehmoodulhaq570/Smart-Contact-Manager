package com.scm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.scm.services.impl.SecurityCustomUserDetailServices;

@Configuration
public class SecurityConfig {

    //private InMemoryUserDetailsManager inMemoryUserDetailsManager;

    // // user create and login using java code with memory service
    // @Bean
    // public UserDetailsService userDetailsService() {
    //     // Create an in-memory user details manager
    //     UserDetails user1 = User
    //     .withDefaultPasswordEncoder()
    //     .username("admin123")
    //     .password("admin123")
    //     .roles("ADMIN","USER")
    //     .build();

    //     UserDetails user2 = User
    //     .withDefaultPasswordEncoder()
    //     .username("user123")
    //     .password("user123")
    //     //.roles("ADMIN","USER")
    //     .build();

    //     var inMemoryUserDetailsManager = new InMemoryUserDetailsManager(user1, user2);  
    //     return inMemoryUserDetailsManager;
    // }

    @Autowired
    private SecurityCustomUserDetailServices userDetailServices;

    // It has all methods available by which we can register to our service
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        // Get the object of user details service
        daoAuthenticationProvider.setUserDetailsService(userDetailServices);
        // Set the object of password encoder
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // daoAuthenticationProvider need the userDetailsService and passwordEncoder which we have implemented in the user by implementing the User to the userDetailsService
    // We need to override the UserDetailsService method by giving our own implementation in the SecurityCustomUserDetailServices class

}
