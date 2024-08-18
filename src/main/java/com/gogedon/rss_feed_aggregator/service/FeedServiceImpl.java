package com.gogedon.rss_feed_aggregator.service;

import com.gogedon.rss_feed_aggregator.api.response.FeedDetailsResponse;
import com.gogedon.rss_feed_aggregator.domain.Feed;
import com.gogedon.rss_feed_aggregator.repository.FeedRepository;
import com.gogedon.rss_feed_aggregator.api.request.CreateFeedRequest;
import com.gogedon.rss_feed_aggregator.api.request.FeedFollowRequest;
import com.gogedon.rss_feed_aggregator.api.response.FeedFollowResponse;
import com.gogedon.rss_feed_aggregator.api.response.FeedResponse;
import com.gogedon.rss_feed_aggregator.rss.RssFeedClient;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

import static com.gogedon.rss_feed_aggregator.mapper.FeedMapper.*;

@Service
public class FeedServiceImpl implements FeedService {

    @Autowired
    private FeedRepository feedRepository;

    @Override
    @Transactional
    public FeedResponse saveFeed(CreateFeedRequest request, String creatorUserId) {
        Feed newFeed = feedRepository.save(mapToFeed(request, creatorUserId));
        return mapToFeedResponse(newFeed);
    }

    @Override
    public List<FeedResponse> getAllFeeds() {
        return mapToFeedResponses(feedRepository.findAll());
    }

    @Override
    public FeedFollowResponse followFeed(FeedFollowRequest request, String creatorUserId) {
        Feed feed = feedRepository.findById(request.getFeedId())
                .orElseThrow(() -> new EntityNotFoundException("Feed not found with id " + request.getFeedId()));
        feed.getFollowerUserIds().add(creatorUserId);
        feedRepository.save(feed);
        return mapToFeedFollowResponse(feed, creatorUserId);
    }

    @Override
    public List<FeedFollowResponse> getUserFollowFeeds(String followerUserId) {
        return mapToFeedFollowResponses(new HashSet<>(feedRepository.findFeedsByFollowerId(followerUserId)), followerUserId);
    }

    @Override
    public FeedDetailsResponse getDetailedFeedResponse(String feedId) {
        Feed feed = feedRepository.findById(feedId).orElseThrow(() -> new EntityNotFoundException());
        return mapToDetailedFeedResponse(RssFeedClient.getFeedDetails(feed.getUrl()));
    }
}