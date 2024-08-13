package com.gogedon.rss_feed_aggregator.mockbuilders;

import com.gogedon.rss_feed_aggregator.domain.Feed;

import java.util.HashSet;

import static com.gogedon.rss_feed_aggregator.utils.TestUtils.STATIC_INSTANT;

public class FeedBuilder {

    public static Feed generate(String creatorUserId, String feedName, String url) {
        return Feed.builder()
                .id(1L)
                .url(url)
                .creatorUserId(creatorUserId)
                .createdAt(STATIC_INSTANT)
                .updatedAt(STATIC_INSTANT)
                .followerUserIds(new HashSet<>())
                .name(feedName)
                .build();
    }

}
