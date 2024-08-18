package com.gogedon.rss_feed_aggregator.mapper;

import com.gogedon.rss_feed_aggregator.api.response.*;
import com.gogedon.rss_feed_aggregator.domain.Feed;
import com.gogedon.rss_feed_aggregator.api.request.CreateFeedRequest;
import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.feed.synd.SyndPerson;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    public static FeedDetailsResponse mapToDetailedFeedResponse(SyndFeed syndFeed) {
        return FeedDetailsResponse.builder()
                .title(syndFeed.getTitle())
                .description(syndFeed.getDescription())
                .publishedDate(syndFeed.getPublishedDate().toInstant())
                .link(syndFeed.getUri())
                .authors(getSyndPersonNames(syndFeed.getAuthors()))
                .contributors(getSyndPersonNames(syndFeed.getContributors()))
                .entries(mapToFeedEntryResponses(syndFeed.getEntries()))
                .build();
    }

    private static Set<FeedEntryResponse> mapToFeedEntryResponses(List<SyndEntry> entries) {
        return entries.stream().map(entry -> mapToFeedEntryResponse(entry)).collect(Collectors.toSet());
    }

    private static FeedEntryResponse mapToFeedEntryResponse(SyndEntry syndEntry) {
        return FeedEntryResponse.builder()
                .title(syndEntry.getTitle())
                .description(mapToFeedContentResponse(syndEntry.getDescription()))
                .publishedDate(syndEntry.getPublishedDate().toInstant())
                .link(syndEntry.getUri())
                .authors(getSyndPersonNames(syndEntry.getAuthors()))
                .contributors(getSyndPersonNames(syndEntry.getContributors()))
                .build();
    }

    private static FeedContentResponse mapToFeedContentResponse(SyndContent syndContent) {
        return FeedContentResponse.builder()
                .type(syndContent.getType())
                .value(syndContent.getValue())
                .build();
    }

    private static Set<String> getSyndPersonNames(List<SyndPerson> syndPeople) {
        return syndPeople.stream().map(SyndPerson::getName).collect(Collectors.toSet());
    }
}
