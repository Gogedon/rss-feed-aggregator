package com.gogedon.rss_feed_aggregator.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenUtil {

    @Autowired
    private JwtDecoder jwtDecoder;

    public String getUsernameFromToken(String token) {
        return jwtDecoder.decode(token).getClaimAsString("sub");
    }
}
