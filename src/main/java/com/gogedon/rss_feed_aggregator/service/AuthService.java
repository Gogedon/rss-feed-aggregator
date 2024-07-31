package com.gogedon.rss_feed_aggregator.service;

import com.gogedon.rss_feed_aggregator.domain.Account;
import com.gogedon.rss_feed_aggregator.request.LoginRequest;
import com.gogedon.rss_feed_aggregator.request.RegisterRequest;
import com.gogedon.rss_feed_aggregator.response.LoginResponse;
import com.gogedon.rss_feed_aggregator.response.RegisterResponse;

import java.util.List;

public interface AuthService {
    RegisterResponse register(RegisterRequest request);
    LoginResponse login(LoginRequest login);
}