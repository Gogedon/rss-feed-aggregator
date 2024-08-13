package com.gogedon.rss_feed_aggregator.api.response;

import com.rometools.rome.feed.synd.SyndEntry;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.time.Instant;
import java.util.Set;

@Value
@Builder
@AllArgsConstructor
public class FeedEntryResponse {
    private String title;
    private FeedContentResponse description;
    private String link;
    private Instant publishedDate;
    private Instant updatedDate;
    private Set<String> authors;
    private Set<String> contributors;
    private Set<String> comments;
    private Set<String> categories;
}
