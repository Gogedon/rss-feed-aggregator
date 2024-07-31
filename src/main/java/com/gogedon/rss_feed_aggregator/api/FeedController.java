package com.gogedon.rss_feed_aggregator.api;

import com.gogedon.rss_feed_aggregator.request.CreateFeedRequest;
import com.gogedon.rss_feed_aggregator.request.FeedFollowRequest;
import com.gogedon.rss_feed_aggregator.response.FeedFollowResponse;
import com.gogedon.rss_feed_aggregator.response.FeedResponse;
import com.gogedon.rss_feed_aggregator.service.FeedService;
import com.gogedon.rss_feed_aggregator.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feed")
@RequiredArgsConstructor
public class FeedController {

    @Autowired
    private FeedService feedService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping
    public FeedResponse createFeed(@RequestBody CreateFeedRequest request,
                                   @RequestHeader(name = "Authorization") String authorizationHeader) {
        return feedService.saveFeed(request, jwtTokenUtil.getUsernameFromToken(authorizationHeader));
    }

    @PostMapping("/follow")
    public FeedFollowResponse followFeed(@RequestBody FeedFollowRequest request,
                                         @RequestHeader(name = "Authorization") String authorizationHeader) {
        return feedService.followFeed(request, jwtTokenUtil.getUsernameFromToken(authorizationHeader));
    }

    @GetMapping("/follow")
    public List<FeedFollowResponse> getUserFollowFeeds(@RequestHeader(name = "Authorization") String authorizationHeader) {
        return feedService.getUserFollowFeeds(jwtTokenUtil.getUsernameFromToken(authorizationHeader));
    }

    @GetMapping
    public List<FeedResponse> getAllFeeds() {
        return feedService.getAllFeeds();
    }

}

