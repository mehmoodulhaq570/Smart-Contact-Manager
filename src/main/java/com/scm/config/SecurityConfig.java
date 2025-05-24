package com.scm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.config.Customizer;

import com.scm.services.impl.SecurityCustomUserDetailServices;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class SecurityConfig {


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
                httpSecurity.formLogin(formLogin -> {

                    // Set our own login page url
                    formLogin.loginPage("/login");
                    formLogin.loginProcessingUrl("/authenticate");
                    formLogin.successForwardUrl("/user/dashboard");
                    //formLogin.failureForwardUrl("/login?error=true");
                    formLogin.usernameParameter("email");
                    formLogin.passwordParameter("password");
                    //formLogin.defaultSuccessUrl("/home", true);

                    // formLogin.failureHandler(new AuthenticationFailureHandler() {
                    //     @Override
                    //     public void onAuthenticationFailure(HttpServletRequest request,
                    //                                         HttpServletResponse response,
                    //                                         AuthenticationException exception) throws java.io.IOException, jakarta.servlet.ServletException {
                    //         // Custom logic for handling authentication failure
                    //         throw new UnsupportedOperationException("Unimplemented method 'onAuthenticationFailure'");
                    //     }
                    // });

                    // formLogin.successHandler(new AuthenticationSuccessHandler() {
                    //     @Override
                    //     public void onAuthenticationSuccess(HttpServletRequest request,
                    //                                         HttpServletResponse response,
                    //                                         org.springframework.security.core.Authentication authentication) throws java.io.IOException, jakarta.servlet.ServletException {
                    //         // Custom logic for handling authentication success
                    //         throw new UnsupportedOperationException("Unimplemented method 'onAuthenticationSuccess'");
                    //     }
                    // });

                });
                httpSecurity.csrf(AbstractHttpConfigurer::disable);
                // Disable CSRF protection for simplicity, but it's recommended to enable it in production
                httpSecurity.logout(logout -> {
                    logout.logoutUrl("/do-logout");
                    logout.logoutSuccessUrl("/login?logout=true");
                    logout.invalidateHttpSession(true);
                });

        return httpSecurity.build();
    }

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // daoAuthenticationProvider needs the userDetailsService and passwordEncoder which we have implemented in the user by implementing the User to the userDetailsService
    // We need to override the UserDetailsService method by giving our own implementation in the SecurityCustomUserDetailServices class

}
