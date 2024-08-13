package com.gogedon.rss_feed_aggregator.api.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class FeedFollowRequest {

    private String feedId;

    @JsonCreator
    public FeedFollowRequest(@JsonProperty("feedId") String feedId) {
        this.feedId = feedId;
    }

}
