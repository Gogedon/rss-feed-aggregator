package com.gogedon.rss_feed_aggregator.service;

import com.gogedon.rss_feed_aggregator.domain.Feed;
import com.gogedon.rss_feed_aggregator.request.CreateFeedRequest;
import com.gogedon.rss_feed_aggregator.request.FeedFollowRequest;
import com.gogedon.rss_feed_aggregator.response.FeedFollowResponse;
import com.gogedon.rss_feed_aggregator.response.FeedResponse;

import java.util.List;

public interface FeedService {
    FeedResponse saveFeed(CreateFeedRequest request, String accountUsername);
    FeedFollowResponse followFeed(FeedFollowRequest request, String accountUsername);
    List<FeedFollowResponse> getUserFollowFeeds(String accountUsername);
    List<FeedResponse> getAllFeeds();
}
