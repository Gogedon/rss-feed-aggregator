package com.gogedon.rss_feed_aggregator.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class SparseAccountResponse {

    private Long id;
    private String username;
}
