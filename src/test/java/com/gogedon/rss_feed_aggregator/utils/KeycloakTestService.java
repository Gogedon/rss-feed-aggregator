package com.gogedon.rss_feed_aggregator.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

import static java.util.Collections.singletonList;

@Service
public class KeycloakTestService {

    static final String GRANT_TYPE = "password";
    static final String CLIENT_ID = "rss-feed-client";
    static final String USERNAME = "gogedon";
    static final String PASSWORD = "123";

    @Autowired
    OAuth2ResourceServerProperties oAuth2ResourceServerProperties;


    public String getToken() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.put("grant_type", singletonList(GRANT_TYPE));
        map.put("client_id", singletonList(CLIENT_ID));
        map.put("username", singletonList(USERNAME));
        map.put("password", singletonList(PASSWORD));

        String authServerUrl =
                oAuth2ResourceServerProperties.getJwt().getIssuerUri() +
                        "/protocol/openid-connect/token";

        var request = new HttpEntity<>(map, httpHeaders);
        ResponseEntity<KeyCloakToken> token = restTemplate.exchange(
                authServerUrl,
                HttpMethod.POST,
                request,
                KeyCloakToken.class
        );

        assert token != null;
        return Objects.requireNonNull(token.getBody()).accessToken();
    }

    record KeyCloakToken(@JsonProperty("access_token") String accessToken) {}

}
