package com.gogedon.rss_feed_aggregator.api;

import com.gogedon.rss_feed_aggregator.domain.Feed;
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
    public Feed createFeed(@RequestBody Feed feed, @RequestHeader(name = "Authorization") String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            String username = jwtTokenUtil.getUsernameFromToken(token);
            return feedService.saveFeed(feed, username);
        }
        throw new RuntimeException("JWT Token is missing or invalid");
    }

    @GetMapping
    public List<Feed> getAllFeeds() {
        return feedService.getAllFeeds();
    }

    @GetMapping("/{id}")
    public Feed getFeedById(@PathVariable Long id) {
        return feedService.getFeedById(id);
    }

    @PutMapping("/{id}")
    public Feed updateFeed(@PathVariable Long id, @RequestBody Feed feed) {
        return feedService.updateFeed(id, feed);
    }

    @DeleteMapping("/{id}")
    public void deleteFeed(@PathVariable Long id) {
        feedService.deleteFeed(id);
    }

}
