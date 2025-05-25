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


                    logger.info("OAuthAuthenticationSuccessHandler");

                // identify the provider

                var oauth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;

                String authorizedClientRegistrationId = oauth2AuthenticationToken.getAuthorizedClientRegistrationId();

                logger.info(authorizedClientRegistrationId);

                var oauthUser = (DefaultOAuth2User) authentication.getPrincipal();

                oauthUser.getAttributes().forEach((key, value) -> {
                    logger.info(key + " : " + value);
                });

                User user = new User();

                // These are the user details that we need to set to default
                user.setUserId(UUID.randomUUID().toString());
                user.setRolesList(List.of(AppConstants.ROLE_USER)); // Set default role
                user.setEmailVerified(true);
                user.setEnabled(true);


                if (authorizedClientRegistrationId.equals("google")) {
                    //google
                    // google attributes
                    user.setEmail(oauthUser.getAttribute("email").toString());
                    user.setName(oauthUser.getAttribute("name").toString());
                    user.setProfilePicture(oauthUser.getAttribute("picture").toString());
                    user.setProviderId(oauthUser.getName());
                    user.setProvider(Providers.GOOGLE);
                    user.setAbout("This is an account created using Google");
                    user.setPhoneNumber("000-000-0000"); // Default phone number
                    user.setPassword("googlepassword"); // Set a default password for OAuth users

                    logger.info("Google OAuth2 authentication successful");


                } else if (authorizedClientRegistrationId.equals("github")) {
                    String email = oauthUser.getAttribute("email") != null ? oauthUser.getAttribute("email").toString() : oauthUser.getAttribute("login").toString() + "@gmail.com";
                    String picture = oauthUser.getAttribute("avatar_url").toString();
                    String name = oauthUser.getAttribute("login").toString();
                    String providerUserId = oauthUser.getName();

                    user.setEmail(email);
                    user.setName(name);
                    user.setProfilePicture(picture);
                    user.setProviderId(providerUserId);
                    user.setProvider(Providers.GITHUB);
                    user.setAbout("This is an account created using GitHub");
                    user.setPhoneNumber("000-000-0000"); // Default phone number
                    user.setPassword("githubpassword"); // Set a default password for OAuth users

                    logger.info("GitHub OAuth2 authentication successful");

                    
                } else {



                    logger.warn("Unknown OAuth2 provider: " + authorizedClientRegistrationId);
                }

        
                
                User user2 = userRepositories.findByEmail(user.getEmail()).orElse(null);
                if (user2 == null) {
                    userRepositories.save(user);
                    logger.info("New user created with Database: " + user.getEmail());

                new DefaultRedirectStrategy().sendRedirect(request, response, "/user/profile");
    }


}
}


