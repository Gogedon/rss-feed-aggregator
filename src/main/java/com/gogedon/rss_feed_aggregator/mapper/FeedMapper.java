package com.gogedon.rss_feed_aggregator.mapper;

import com.gogedon.rss_feed_aggregator.domain.Feed;
import com.gogedon.rss_feed_aggregator.request.CreateFeedRequest;
import com.gogedon.rss_feed_aggregator.response.FeedFollowResponse;
import com.gogedon.rss_feed_aggregator.response.FeedResponse;

import java.util.List;
import java.util.Set;

public class FeedMapper {

    public static List<FeedFollowResponse> mapToFeedFollowResponses(Set<Feed> feeds, String followerUserId) {
        return feeds.stream().map(feed -> mapToFeedFollowResponse(feed, followerUserId)).toList();
    }

    public static FeedFollowResponse mapToFeedFollowResponse(Feed feed, String followerUserId) {
        return FeedFollowResponse.builder()
                .feedId(feed.getId())
                .feedName(feed.getName())
                .feedUrl(feed.getUrl())
                .followerUserId(followerUserId)
                .build();
    }


    public static List<FeedResponse> mapToFeedResponses(List<Feed> feeds) {
        return feeds.stream().map(FeedMapper::mapToFeedResponse).toList();
    }

    public static FeedResponse mapToFeedResponse(Feed feed) {
        return FeedResponse.builder()
                .id(feed.getId())
                .feedName(feed.getName())
                .feedUrl(feed.getUrl())
                .updatedAt(feed.getUpdatedAt())
                .createdAt(feed.getCreatedAt())
                .creatorUserId(feed.getCreatorUserId())
                .build();
    }

    public static Feed mapToFeed(CreateFeedRequest request, String userId) {
        return Feed.builder()
                .name(request.getName())
                .url(request.getUrl())
                .creatorUserId(userId)
                .build();
    }
}
