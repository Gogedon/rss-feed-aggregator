package com.gogedon.rss_feed_aggregator.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.time.Instant;

@Value
@Builder
@AllArgsConstructor
public class FeedResponse {

    private Long id;
    private String feedName;
    private String feedUrl;
    private Instant createdAt;
    private Instant updatedAt;
    private String creatorUserId;

}
