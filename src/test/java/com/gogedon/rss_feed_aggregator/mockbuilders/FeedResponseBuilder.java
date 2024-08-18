package com.gogedon.rss_feed_aggregator.mockbuilders;

import com.gogedon.rss_feed_aggregator.api.response.FeedResponse;

import static com.gogedon.rss_feed_aggregator.utils.TestUtils.STATIC_INSTANT;

public class FeedResponseBuilder {

    public static FeedResponse generate(String id, String creatorUserId, String feedName, String feedUrl) {
        return FeedResponse.builder()
                .id(id)
                .creatorUserId(creatorUserId)
                .feedUrl(feedUrl)
                .feedName(feedName)
                .createdAt(STATIC_INSTANT)
                .updatedAt(STATIC_INSTANT)
                .build();
    }

}
