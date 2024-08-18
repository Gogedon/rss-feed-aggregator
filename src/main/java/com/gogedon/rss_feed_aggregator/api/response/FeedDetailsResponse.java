package com.gogedon.rss_feed_aggregator.api.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.time.Instant;
import java.util.Set;

@Value
@Builder
@AllArgsConstructor
public class FeedDetailsResponse {
    private String title;
    private String description;
    private String link;
    private Instant publishedDate;
    private Set<String> authors;
    private Set<String> contributors;
    private Set<FeedEntryResponse> entries;
}
