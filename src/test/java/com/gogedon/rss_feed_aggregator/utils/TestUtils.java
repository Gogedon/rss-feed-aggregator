package com.gogedon.rss_feed_aggregator.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;

public class TestUtils {

    public static final Instant STATIC_INSTANT = Instant.parse("2024-08-12T00:00:00Z");

    public static String getStringValueOf(String filePath) throws IOException {
        return FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8)
                .replaceAll("\\s", "");
    }

}
