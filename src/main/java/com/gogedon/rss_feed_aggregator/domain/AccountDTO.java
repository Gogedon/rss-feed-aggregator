package com.gogedon.rss_feed_aggregator.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {

    private Long id;
    private String username;
    private String password;
    private Set<Authorities> authorities;
    private Set<Long> feedIds;

}
