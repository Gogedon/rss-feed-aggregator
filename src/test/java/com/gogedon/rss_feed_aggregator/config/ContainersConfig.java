package com.gogedon.rss_feed_aggregator.config;

import dasniko.testcontainers.keycloak.KeycloakContainer;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.PostgreSQLContainer;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
@TestConfiguration(proxyBeanMethods = false)
public class ContainersConfig {

    static String POSTGRES_IMAGE = "postgres:latest";
    static String KEYCLOAK_IMAGE = "quay.io/keycloak/keycloak:latest";
    static String realmImportFile = "/keycloak/realm-export.json";
    static String realmName = "RssFeedAggregator";

    @Bean
    @ServiceConnection
    PostgreSQLContainer<?> postgres() {
        return new PostgreSQLContainer<>(POSTGRES_IMAGE);
    }

    @Bean
    KeycloakContainer keycloak(DynamicPropertyRegistry registry) {
        var keycloak = new KeycloakContainer(KEYCLOAK_IMAGE)
                .withRealmImportFile(realmImportFile);
        registry.add(
                "spring.security.oauth2.resourceserver.jwt.issuer-uri",
                () -> keycloak.getAuthServerUrl() + "/realms/" + realmName
        );
        registry.add(
                "spring.security.oauth2.client.provider.keycloak.issuer-uri",
                () -> keycloak.getAuthServerUrl() + "/realms/" + realmName
        );
        return keycloak;
    }

}
