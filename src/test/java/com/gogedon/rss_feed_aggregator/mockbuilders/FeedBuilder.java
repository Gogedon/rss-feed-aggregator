package com.gogedon.rss_feed_aggregator.mockbuilders;

import com.gogedon.rss_feed_aggregator.domain.Feed;

import java.time.Instant;
import java.util.HashSet;

public class FeedBuilder {

    public static Feed generateNewFeed(String creatorUserId, String feedName, String url) {
        return Feed.builder()
                .id(1L)
                .url(url)
                .creatorUserId(creatorUserId)
                .createdAt(Instant.MIN)
                .updatedAt(Instant.MIN)
                .followerUserIds(new HashSet<>())
                .name(feedName)
                .build();
    }

}
