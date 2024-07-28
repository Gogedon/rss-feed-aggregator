package com.gogedon.rss_feed_aggregator.service;

import com.gogedon.rss_feed_aggregator.domain.Feed;

import java.util.List;

public interface FeedService {
    Feed saveFeed(Feed feed, String accountUsername);
    List<Feed> getAllFeeds();
    Feed getFeedById(Long id);
    Feed updateFeed(Long id, Feed feed);
    void deleteFeed(Long id);
}
