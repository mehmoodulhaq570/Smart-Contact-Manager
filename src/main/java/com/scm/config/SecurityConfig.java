package com.scm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.Customizer;

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
    // Configuration of authentication provider for spring security

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        // Get the object of user details service
        daoAuthenticationProvider.setUserDetailsService(userDetailServices);
        // Set the object of password encoder
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }


    // Configure urls which one is public and which one is private
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // configurations
        httpSecurity.authorizeHttpRequests(authorize -> {
                        //authorize.requestMatchers("/home", "/signup", "/services", "/about").permitAll();
                        authorize.requestMatchers("/user/**").authenticated();
                        authorize.anyRequest().permitAll();

                });

                // form default login
                // If we need to change anything related to from login we just come here
                httpSecurity.formLogin(Customizer.withDefaults());

        return httpSecurity.build();
    }

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // daoAuthenticationProvider needs the userDetailsService and passwordEncoder which we have implemented in the user by implementing the User to the userDetailsService
    // We need to override the UserDetailsService method by giving our own implementation in the SecurityCustomUserDetailServices class

}
