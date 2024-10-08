package com.gogedon.rss_feed_aggregator.service;

import com.gogedon.rss_feed_aggregator.domain.Feed;
import com.gogedon.rss_feed_aggregator.repository.FeedRepository;
import com.gogedon.rss_feed_aggregator.request.CreateFeedRequest;
import com.gogedon.rss_feed_aggregator.request.FeedFollowRequest;
import com.gogedon.rss_feed_aggregator.response.FeedFollowResponse;
import com.gogedon.rss_feed_aggregator.response.FeedResponse;
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
        Feed feed = feedRepository.findById(Long.valueOf(request.getFeedId()))
                .orElseThrow(() -> new EntityNotFoundException("Feed not found with id " + request.getFeedId()));
        feed.getFollowerUserIds().add(creatorUserId);
        feedRepository.save(feed);
        return mapToFeedFollowResponse(feed, creatorUserId);
    }

    @Override
    public List<FeedFollowResponse> getUserFollowFeeds(String followerUserId) {
        return mapToFeedFollowResponses(new HashSet<>(feedRepository.findFeedsByFollowerId(followerUserId)), followerUserId);
    }
}