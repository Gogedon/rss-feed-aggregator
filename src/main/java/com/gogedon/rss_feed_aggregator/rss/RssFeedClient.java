package com.gogedon.rss_feed_aggregator.rss;


import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.io.IOException;
import java.net.URL;

public class RssFeedClient {

    public static SyndFeed getFeedDetails(String url) {
        try {
                URL feedSource = new URL(url);
                SyndFeedInput input = new SyndFeedInput();
                return input.build(new XmlReader(feedSource));
        } catch (FeedException | IOException e) {
                throw new RuntimeException(e);
        }
    }
}
