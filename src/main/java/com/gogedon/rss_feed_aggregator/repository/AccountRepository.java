package com.gogedon.rss_feed_aggregator.repository;

import com.gogedon.rss_feed_aggregator.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByApiKey(String apiKey);

    Optional<Account> findByUsername(String username);
    Optional<Account> findByPassword(String password);
}
