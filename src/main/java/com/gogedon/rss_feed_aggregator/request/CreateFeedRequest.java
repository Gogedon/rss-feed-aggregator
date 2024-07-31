package com.gogedon.rss_feed_aggregator.request;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class CreateFeedRequest {

    private String name;
    private String url;
}
