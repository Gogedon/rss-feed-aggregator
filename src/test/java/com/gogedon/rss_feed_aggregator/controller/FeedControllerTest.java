package com.gogedon.rss_feed_aggregator.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gogedon.rss_feed_aggregator.config.ContainersConfig;
import com.gogedon.rss_feed_aggregator.mockbuilders.FeedFollowResponseBuilder;
import com.gogedon.rss_feed_aggregator.mockbuilders.FeedResponseBuilder;
import com.gogedon.rss_feed_aggregator.request.CreateFeedRequest;
import com.gogedon.rss_feed_aggregator.request.FeedFollowRequest;
import com.gogedon.rss_feed_aggregator.service.FeedService;
import com.gogedon.rss_feed_aggregator.utils.KeycloakTestService;
import com.gogedon.rss_feed_aggregator.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Import(ContainersConfig.class)
public class FeedControllerTest {

    @Autowired
    private KeycloakTestService keycloak;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FeedService service;

    @Test
    void shouldGetAllFeeds() throws Exception {
        // When
        when(service.getAllFeeds()).thenReturn(
                List.of(
                        FeedResponseBuilder.generate("userId1", "feedName1", "feedUrl1"),
                        FeedResponseBuilder.generate("userId2", "feedName2", "feedUrl2")
                )
        );

        // Then
        String expected = TestUtils.getStringValueOf("src/test/resources/expected-get-all-feeds-response.json");
        mockMvc.perform(get("/api/feed")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(expected));
    }

    @Test
    void shouldCreateFeed() throws Exception {
        // When
        when(service.saveFeed(any(), any())).thenReturn(
                FeedResponseBuilder.generate("userId", "feedName", "feedUrl")
        );

        // Then
        String expected = TestUtils.getStringValueOf("src/test/resources/expected-create-feed-response.json");
        mockMvc.perform(post("/api/feed")
                        .header("Authorization", "Bearer " + keycloak.getToken())
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(new ObjectMapper().writeValueAsBytes(new CreateFeedRequest("name", "url"))))
                .andExpect(content().string(expected));
    }

    @Test
    void shouldGetUserFollowFeeds() throws Exception {
        // When
        when(service.getUserFollowFeeds(any())).thenReturn(
                List.of(
                        FeedFollowResponseBuilder.generate(1L, "userId1", "feedName1", "feedUrl1"),
                        FeedFollowResponseBuilder.generate(2L, "userId1", "feedName2", "feedUrl2")
                )
        );

        // Then
        String expected = TestUtils.getStringValueOf("src/test/resources/expected-get-feed-follow-response.json");
        mockMvc.perform(get("/api/feed/follow")
                        .header("Authorization", "Bearer " + keycloak.getToken()))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(expected));
    }

    @Test
    void shouldFollowFeed() throws Exception {
        // When
        when(service.followFeed(any(), any())).thenReturn(
                FeedFollowResponseBuilder.generate(1L, "userId", "feedName", "feedUrl")
        );

        // Then
        String expected = TestUtils.getStringValueOf("src/test/resources/expected-follow-feed-response.json");
        mockMvc.perform(post("/api/feed/follow")
                        .header("Authorization", "Bearer " + keycloak.getToken())
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(new ObjectMapper().writeValueAsBytes(new FeedFollowRequest("1L"))))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(expected));
    }

}
