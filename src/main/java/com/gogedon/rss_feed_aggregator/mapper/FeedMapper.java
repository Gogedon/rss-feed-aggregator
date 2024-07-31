package com.gogedon.rss_feed_aggregator.mapper;

import com.gogedon.rss_feed_aggregator.domain.Account;
import com.gogedon.rss_feed_aggregator.domain.Feed;
import com.gogedon.rss_feed_aggregator.request.CreateFeedRequest;
import com.gogedon.rss_feed_aggregator.response.FeedFollowResponse;
import com.gogedon.rss_feed_aggregator.response.FeedResponse;
import com.gogedon.rss_feed_aggregator.response.SparseAccountResponse;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import static java.util.Objects.isNull;

public class FeedMapper {

    public static List<FeedFollowResponse> mapToFeedFollowResponses(Set<Feed> feeds, String username) {
        return feeds.stream().map(feed -> mapToFeedFollowResponse(feed, username)).toList();
    }

    public static FeedFollowResponse mapToFeedFollowResponse(Feed feed, String username) {
        return FeedFollowResponse.builder()
                .feedId(feed.getId())
                .feedName(feed.getName())
                .feedUrl(feed.getUrl())
                .followerUsername(username)
                .build();
    }

    public static List<FeedResponse> mapToFeedResponses(List<Feed> feeds) {
        return feeds.stream().map(FeedMapper::mapToFeedResponse).toList();
    }

    public static FeedResponse mapToFeedResponse(Feed feed) {
        return FeedResponse.builder()
                .id(feed.getId())
                .name(feed.getName())
                .feedUrl(feed.getUrl())
                .updatedAt(feed.getUpdatedAt())
                .createdAt(feed.getCreatedAt())
                .creator(mapToSparsedAccountResponse(feed.getCreator()))
                .followers(mapToSparseAccountResponses(feed.getFollowerAccounts()))
                .build();
    }

    private static List<SparseAccountResponse> mapToSparseAccountResponses(Collection<Account> accounts) {
        if (isNull(accounts)) {
            return List.of();
        }
        return accounts.stream().map(FeedMapper::mapToSparsedAccountResponse).toList();
    }

    private static SparseAccountResponse mapToSparsedAccountResponse(Account account) {
        return SparseAccountResponse.builder()
                .id(account.getId())
                .username(account.getUsername())
                .build();
    }

    public static Feed mapToFeed(CreateFeedRequest request, Account account) {
        return Feed.builder()
                .name(request.getName())
                .url(request.getUrl())
                .creator(account)
                .build();
    }
}
