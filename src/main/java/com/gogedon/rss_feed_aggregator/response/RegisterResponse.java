package com.gogedon.rss_feed_aggregator.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.time.Instant;

@Value
@Builder
@AllArgsConstructor
public class RegisterResponse {

    private String username;
    private Instant createdAt;

}
