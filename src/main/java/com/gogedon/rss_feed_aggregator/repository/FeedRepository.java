package com.gogedon.rss_feed_aggregator.repository;

import com.gogedon.rss_feed_aggregator.domain.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedRepository extends JpaRepository<Feed, Long> {
}
