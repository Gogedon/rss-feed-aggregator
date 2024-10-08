package com.gogedon.rss_feed_aggregator.controller;

import com.gogedon.rss_feed_aggregator.request.CreateFeedRequest;
import com.gogedon.rss_feed_aggregator.request.FeedFollowRequest;
import com.gogedon.rss_feed_aggregator.response.FeedFollowResponse;
import com.gogedon.rss_feed_aggregator.response.FeedResponse;
import com.gogedon.rss_feed_aggregator.service.FeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.gogedon.rss_feed_aggregator.keycloak.KeycloakUtil.getKeycloakUserId;

@RestController
@RequestMapping("/api/feed")
@RequiredArgsConstructor
public class FeedController {

   @Autowired
    private FeedService feedService;

    @GetMapping
    public List<FeedResponse> getAllFeeds() {
        return feedService.getAllFeeds();
    }

    @PostMapping
    public FeedResponse createFeed(@RequestBody CreateFeedRequest request) {
        return feedService.saveFeed(request, getKeycloakUserId());
    }
    @PostMapping("/follow")
    public FeedFollowResponse followFeed(@RequestBody FeedFollowRequest request) {
        return feedService.followFeed(request, getKeycloakUserId());
    }

    @GetMapping("/follow")
    public List<FeedFollowResponse> getUserFollowFeeds() {
        return feedService.getUserFollowFeeds(getKeycloakUserId());
    }

}

