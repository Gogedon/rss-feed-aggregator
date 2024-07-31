package com.gogedon.rss_feed_aggregator.service;

import com.gogedon.rss_feed_aggregator.domain.Account;
import com.gogedon.rss_feed_aggregator.domain.Feed;
import com.gogedon.rss_feed_aggregator.repository.AccountRepository;
import com.gogedon.rss_feed_aggregator.repository.FeedRepository;
import com.gogedon.rss_feed_aggregator.request.CreateFeedRequest;
import com.gogedon.rss_feed_aggregator.request.FeedFollowRequest;
import com.gogedon.rss_feed_aggregator.response.FeedFollowResponse;
import com.gogedon.rss_feed_aggregator.response.FeedResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.gogedon.rss_feed_aggregator.mapper.FeedMapper.*;

@Service
public class FeedServiceImpl implements FeedService {

    @Autowired
    private FeedRepository feedRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    @Transactional
    public FeedResponse saveFeed(CreateFeedRequest request, String username) {
        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Account not found for username " + username));
        Feed newFeed = feedRepository.save(mapToFeed(request, account));
        return mapToFeedResponse(newFeed);
    }

    @Override
    public FeedFollowResponse followFeed(FeedFollowRequest request, String accountUsername) {
        Account account = accountRepository.findByUsername(accountUsername)
                .orElseThrow(() -> new EntityNotFoundException("Account not found for username " + accountUsername));
        Feed feed = feedRepository.findById(Long.valueOf(request.getFeedId()))
                .orElseThrow(() -> new EntityNotFoundException("Feed not found with id " + request.getFeedId()));
        account.getFollowedFeeds().add(feed);
        accountRepository.save(account);
        return mapToFeedFollowResponse(feed, account.getUsername());
    }

    @Override
    public List<FeedFollowResponse> getUserFollowFeeds(String accountUsername) {
        Account account = accountRepository.findByUsername(accountUsername)
                .orElseThrow(() -> new EntityNotFoundException("Account not found for username " + accountUsername));
        return mapToFeedFollowResponses(account.getFollowedFeeds(), account.getUsername());
    }

    @Override
    public List<FeedResponse> getAllFeeds() {
        return mapToFeedResponses(feedRepository.findAll());
    }

}