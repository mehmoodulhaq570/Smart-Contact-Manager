package com.scm.config;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.scm.entities.Providers;
import com.scm.entities.User;
import com.scm.helper.AppConstants;
import com.scm.repositories.UserRepositories;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuthAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserRepositories userRepositories;

    Logger logger = LoggerFactory.getLogger(OAuthAuthenticationSuccessHandler.class);
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
                logger.info("AuthenticationSuccessHandler");


                    logger.info("OAuthAuthenicationSuccessHandler");

                // identify the provider

                var oauth2AuthenicationToken = (OAuth2AuthenticationToken) authentication;

                String authorizedClientRegistrationId = oauth2AuthenicationToken.getAuthorizedClientRegistrationId();

                logger.info(authorizedClientRegistrationId);

                var oauthUser = (DefaultOAuth2User) authentication.getPrincipal();

                oauthUser.getAttributes().forEach((key, value) -> {
                    logger.info(key + " : " + value);
                });

                

                // // Before redirecting fetch the user details and save it in database
                // DefaultOAuth2User oauth2User = (DefaultOAuth2User) authentication.getPrincipal();
                
                // String email = oauth2User.getAttribute("email").toString();
                // String name = oauth2User.getAttribute("name").toString();
                // String picture = oauth2User.getAttribute("picture").toString();

                // logger.info("User Details: Email: {}, Name: {}, Picture: {}", email, name, picture);

                // // Create user and save in database

                // User user = new User();
                // user.setEmail(email);
                // user.setName(name);
                // user.setProfilePicture(picture);
                // user.setPassword("oauth2password"); 
                // user.setUserId(UUID.randomUUID().toString());
                // user.setProvider(Providers.GOOGLE);
                // user.setEnabled(true);
                // user.setEmailVerified(true);
                // user.setProviderId(user.getName());
                // user.setRolesList(List.of(AppConstants.ROLE_USER)); // Set default role
                // user.setAbout("This is an account created using Google");
                // user.setPhoneNumber("000-000-0000"); // Default phone number
                // user.setPhoneVerified(false);
                

                // User user2 = userRepositories.findByEmail(email).orElse(null);
                // if (user2 == null) {
                //     userRepositories.save(user);
                //     logger.info("New user created with email: {}" + email);
                // }

                new DefaultRedirectStrategy().sendRedirect(request, response, "/user/profile");
    }


}
