package com.gogedon.rss_feed_aggregator.mapper;

import com.gogedon.rss_feed_aggregator.domain.Account;
import com.gogedon.rss_feed_aggregator.response.RegisterResponse;

public class AccountMapper {

    public static RegisterResponse mapToRegisterResponse(Account account) {
        return RegisterResponse.builder()
                .username(account.getUsername())
                .createdAt(account.getCreatedAt())
                .build();
    }

}
