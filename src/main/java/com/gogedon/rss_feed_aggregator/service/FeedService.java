package com.gogedon.rss_feed_aggregator.service;

import com.gogedon.rss_feed_aggregator.api.request.CreateFeedRequest;
import com.gogedon.rss_feed_aggregator.api.request.FeedFollowRequest;
import com.gogedon.rss_feed_aggregator.api.response.FeedDetailsResponse;
import com.gogedon.rss_feed_aggregator.api.response.FeedFollowResponse;
import com.gogedon.rss_feed_aggregator.api.response.FeedResponse;

import java.util.List;

public interface FeedService {
    FeedResponse saveFeed(CreateFeedRequest request, String userId);
    List<FeedResponse> getAllFeeds();
    FeedFollowResponse followFeed(FeedFollowRequest request, String userId);
    List<FeedFollowResponse> getUserFollowFeeds(String userId);
    FeedDetailsResponse getDetailedFeedResponse(String feedId);
}
