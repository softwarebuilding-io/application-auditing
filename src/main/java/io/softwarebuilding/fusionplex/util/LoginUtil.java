package io.softwarebuilding.fusionplex.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public enum LoginUtil {
    ;

    public static final String ANONYMOUS_USER = "ANONYMOUS";

    public static String getAuthenticatedUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if ( authentication == null || !authentication.isAuthenticated() ) {
            return ANONYMOUS_USER;
        }

        final Object principal = authentication.getPrincipal();

        if ( principal instanceof final UserDetails userDetails ) {

            return userDetails.getUsername().toUpperCase();
        } else if (principal instanceof String) {
            return ( (String) principal ).toUpperCase();
        }

        return ANONYMOUS_USER;
    }
}
