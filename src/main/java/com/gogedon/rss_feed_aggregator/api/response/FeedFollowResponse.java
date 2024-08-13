package com.gogedon.rss_feed_aggregator.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class FeedFollowResponse {

    private String followerUserId;
    private String feedName;
    private String feedId;
    private String feedUrl;

}
