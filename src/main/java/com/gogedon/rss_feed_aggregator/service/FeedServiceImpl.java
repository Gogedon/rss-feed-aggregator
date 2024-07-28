package com.gogedon.rss_feed_aggregator.service;

import com.gogedon.rss_feed_aggregator.domain.Account;
import com.gogedon.rss_feed_aggregator.domain.Feed;
import com.gogedon.rss_feed_aggregator.exceptions.NotFoundException;
import com.gogedon.rss_feed_aggregator.repository.AccountRepository;
import com.gogedon.rss_feed_aggregator.repository.FeedRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FeedServiceImpl implements FeedService {

    public static final String FEED_WITH_ID_NOT_FOUND = "Feed with id %d not found.";

    @Autowired
    private FeedRepository feedRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    @Transactional
    public Feed saveFeed(Feed feed, String accountUsername) {
        Account account = accountRepository.findByUsername(accountUsername)
                .orElseThrow(() -> new EntityNotFoundException("Account not found for username " + accountUsername));
        account.getFeeds().add(feed);
        accountRepository.save(account);
        return feed;
    }

    @Override
    public List<Feed> getAllFeeds() {
        return feedRepository.findAll();
    }

    @Override
    public Feed getFeedById(Long id) {
        return feedRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(FEED_WITH_ID_NOT_FOUND, id)));
    }

    @Override
    public Feed updateFeed(Long id, Feed feed) {
        Feed existingFeed = feedRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(FEED_WITH_ID_NOT_FOUND, id)));
        existingFeed.setName(feed.getName());
        existingFeed.setUrl(feed.getUrl());
        return feedRepository.save(existingFeed);
    }

    @Override
    public void deleteFeed(Long id) {
        feedRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(FEED_WITH_ID_NOT_FOUND, id)));
        feedRepository.deleteById(id);
    }
}