package com.gogedon.rss_feed_aggregator.repository;

import com.gogedon.rss_feed_aggregator.domain.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Authorities, Long> {

    boolean existsByAuthority(String authority);

    Optional<Authorities> findByAuthority(String authority);
}
