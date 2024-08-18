package com.gogedon.rss_feed_aggregator.mockbuilders;

import com.gogedon.rss_feed_aggregator.api.response.FeedFollowResponse;

public class FeedFollowResponseBuilder {

    public static FeedFollowResponse generate(
            String feedId,
            String followerUserId,
            String feedName,
            String feedUrl
    ) {
        return FeedFollowResponse.builder()
                .feedId(feedId)
                .followerUserId(followerUserId)
                .feedName(feedName)
                .feedUrl(feedUrl)
                .build();
    }

}
