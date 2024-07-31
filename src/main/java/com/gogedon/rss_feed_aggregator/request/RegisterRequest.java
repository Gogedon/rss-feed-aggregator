package com.gogedon.rss_feed_aggregator.request;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class RegisterRequest {

    private String username;
    private String password;


}
