package com.gogedon.rss_feed_aggregator.service;

import com.gogedon.rss_feed_aggregator.config.ContainersConfig;
import com.gogedon.rss_feed_aggregator.mockbuilders.FeedBuilder;
import com.gogedon.rss_feed_aggregator.mockbuilders.FeedResponseBuilder;
import com.gogedon.rss_feed_aggregator.repository.FeedRepository;
import com.gogedon.rss_feed_aggregator.response.FeedResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@Import(ContainersConfig.class)
public class FeedServiceTest {

    @MockBean
    private FeedRepository feedRepository;

    @Autowired
    private FeedServiceImpl feedService;

    @Test
    void shouldGetAllFeeds() {
        //Given
        String userId = "userId";
        String feedName = "feedName";
        String feedUrl = "feedUrl";
        //When
        when(feedRepository.findAll())
                .thenReturn(
                        List.of(
                                FeedBuilder.generate(userId, feedName, feedUrl)));

        //Then
        List<FeedResponse> actual = feedService.getAllFeeds();
        FeedResponse expected = FeedResponseBuilder.generate(userId, feedName, feedUrl);
        assertThat(actual).containsExactly(expected);
    }

}
