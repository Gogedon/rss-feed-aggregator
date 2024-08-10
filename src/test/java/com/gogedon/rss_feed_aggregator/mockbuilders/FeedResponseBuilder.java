package com.gogedon.rss_feed_aggregator.mockbuilders;

import com.gogedon.rss_feed_aggregator.response.FeedResponse;

import java.time.Instant;

public class FeedResponseBuilder {

    public static FeedResponse generateNewFeedResponse(String creatorUserId, String feedName, String feedUrl) {
        return FeedResponse.builder()
                .id(1L)
                .creatorUserId(creatorUserId)
                .feedUrl(feedUrl)
                .feedName(feedName)
                .createdAt(Instant.MIN)
                .updatedAt(Instant.MIN)
                .build();
    }

}
