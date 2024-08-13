package com.gogedon.rss_feed_aggregator.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class FeedContentResponse {
    private String type;
    private String value;
}
