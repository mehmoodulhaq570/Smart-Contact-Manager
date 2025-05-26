package com.scm.helper;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class Helper {

    public static String getEmailOfLoggedInUser(Authentication authentication) {

        // If we have logged in from OAuth2 (Google, GitHub, etc.)
        if (authentication instanceof OAuth2AuthenticationToken aOAuth2AuthenticationToken) {
            String clientId = aOAuth2AuthenticationToken.getAuthorizedClientRegistrationId();
            var oauth2User = (OAuth2User)authentication.getPrincipal();
            String username = "";

            if (clientId.equalsIgnoreCase("google")) {
                System.out.println("Getting email from Google");
                username = oauth2User.getAttribute("email").toString();
                return username;
                
            } else if (clientId.equalsIgnoreCase("github")) {
                System.out.println("Getting email from Github");
                username = oauth2User.getAttribute("email") != null ? oauth2User.getAttribute("email").toString() : oauth2User.getAttribute("login").toString() + "@gmail.com";
                return username;
            }
        }

        // If we logged in with username/password (local)
        System.out.println("Getting data from local storage");
        return authentication.getName();
    }

}
