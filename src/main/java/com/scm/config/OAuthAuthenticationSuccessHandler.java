package com.scm.config;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
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


                    logger.info("OAuthAuthenticationSuccessHandler");

                // identify the provider

                var oauth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;

                String authorizedClientRegistrationId = oauth2AuthenticationToken.getAuthorizedClientRegistrationId();

                logger.info(authorizedClientRegistrationId);

                var oauthUser = (DefaultOAuth2User) authentication.getPrincipal();

                String email = oauthUser.getAttribute("email");
                Optional<User> existingUser = userRepositories.findByEmail(email);

                oauthUser.getAttributes().forEach((key, value) -> {
                    logger.info(key + " : " + value);
                });

                User user = new User();

                // These are the user details that we need to set to default
                user.setUserId(UUID.randomUUID().toString());
                user.setRolesList(List.of(AppConstants.ROLE_USER)); // Set default role
                user.setEmailVerified(true);
                user.setEnabled(true);


                    if (existingUser.isPresent()) {
        user = existingUser.get();
        logger.info("Existing user logged in: " + user.getEmail());

        
            } else {
            user = new User();
            user.setUserId(UUID.randomUUID().toString());
            user.setRolesList(List.of(AppConstants.ROLE_USER));
            user.setEmailVerified(true);
            user.setEnabled(true);
            user.setPassword("oauthpassword"); // Or null/secure

            if (authorizedClientRegistrationId.equals("google")) {
                user.setEmail(email);
                user.setName(oauthUser.getAttribute("name").toString());
                user.setProfilePicture(oauthUser.getAttribute("picture").toString());
                user.setProviderId(oauthUser.getName());
                user.setProvider(Providers.GOOGLE);
                user.setAbout("This is an account created using Google");
            } 
            else if (authorizedClientRegistrationId.equals("github")) {

                String githubEmail = email != null ? email : oauthUser.getAttribute("login").toString() + "@gmail.com";
                user.setEmail(githubEmail);
                user.setName(oauthUser.getAttribute("login").toString());
                user.setProfilePicture(oauthUser.getAttribute("avatar_url").toString());
                user.setProviderId(oauthUser.getName());
                user.setProvider(Providers.GITHUB);
                user.setAbout("This is an account created using GitHub");
            }
            userRepositories.save(user);
            logger.info("New user created with email: " + user.getEmail());
        }

        response.sendRedirect("/user/profile");

}
}


