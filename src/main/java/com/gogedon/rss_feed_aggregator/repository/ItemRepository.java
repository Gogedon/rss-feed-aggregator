package com.gogedon.rss_feed_aggregator.repository;

import com.gogedon.rss_feed_aggregator.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
