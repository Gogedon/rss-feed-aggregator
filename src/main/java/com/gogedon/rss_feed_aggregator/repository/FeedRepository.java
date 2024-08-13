package com.gogedon.rss_feed_aggregator.repository;

import com.gogedon.rss_feed_aggregator.domain.Feed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface FeedRepository extends JpaRepository<Feed, String> {

    @Query(value = "SELECT * FROM feed WHERE :followerId IN followers_user_id", nativeQuery = true)
    List<Feed> findFeedsByFollowerId(@Param("followerId") String followerId);

}
