package com.gogedon.rss_feed_aggregator;

import com.gogedon.rss_feed_aggregator.config.ContainersConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(ContainersConfig.class)
class RssFeedAggregatorApplicationTests {

	@Test
	void contextLoads() {
	}


}
