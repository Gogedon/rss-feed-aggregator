package com.gogedon.rss_feed_aggregator.util;

import com.gogedon.rss_feed_aggregator.domain.Account;
import com.gogedon.rss_feed_aggregator.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.stream.Collectors;

@Component
public class JwtTokenUtil {

    @Autowired
    private JwtDecoder jwtDecoder;

    @Autowired
    private JwtEncoder jwtEncoder;

    public String getUsernameFromToken(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            return jwtDecoder.decode(token).getClaimAsString("sub");
        }
        throw new RuntimeException("JWT Token is missing or invalid");
    }

    public String createToken(Account account) {
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(60 * 30))
                .subject(account.getUsername())
                .claim("scope", createScope(account))
                .build();
        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    private String createScope(Account account) {
        return account.getAuthorities().stream()
                .map(auth -> auth.getAuthority())
                .collect(Collectors.joining(" "));
    }
}
