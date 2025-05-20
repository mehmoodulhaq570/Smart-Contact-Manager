package com.scm.helper;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpSession;

@Component // When you declare something as component, it becomes a Spring bean and can be injected into other components
// By this the object like sessionHelper will be created by the Spring container
public class SessionHelper {
    public static void removeMessage() {
        try {
            System.out.println("Removing message from session");
            HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
            session.removeAttribute("message");
        } catch (Exception e) {
            // Optionally log the exception or handle it as needed
            System.out.println("Error removing message from session: " + e.getMessage());
        }
    }

}
