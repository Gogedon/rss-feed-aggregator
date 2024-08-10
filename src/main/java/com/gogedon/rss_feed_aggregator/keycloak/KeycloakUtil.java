package com.gogedon.rss_feed_aggregator.keycloak;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class KeycloakUtil {

    public static final String KEYCLOAK_USER_ID_KEY = "sub";

    public static String getKeycloakUserId() {
        Object jwtAuthToken = SecurityContextHolder.getContext().getAuthentication();
        if (jwtAuthToken instanceof JwtAuthenticationToken) {
            return (String) ((JwtAuthenticationToken) jwtAuthToken).getToken().getClaims().get(KEYCLOAK_USER_ID_KEY);
        }
        throw new IllegalStateException("Authentication token is not of type JwtAuthenticationToken");
    }
}