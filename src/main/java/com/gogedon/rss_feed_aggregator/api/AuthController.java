package com.gogedon.rss_feed_aggregator.api;

import com.gogedon.rss_feed_aggregator.request.LoginRequest;
import com.gogedon.rss_feed_aggregator.request.RegisterRequest;
import com.gogedon.rss_feed_aggregator.response.LoginResponse;
import com.gogedon.rss_feed_aggregator.response.RegisterResponse;
import com.gogedon.rss_feed_aggregator.service.AuthService;
import com.gogedon.rss_feed_aggregator.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class AuthController {

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    AuthService authService;

    @PostMapping("register")
    public ResponseEntity<RegisterResponse> authenticate(@RequestBody RegisterRequest request) {
        return new ResponseEntity<>(authService.register(request), HttpStatus.OK);
    }

    @PostMapping("login")
    @Transactional
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginRequest request) {
        return new ResponseEntity<>(authService.login(request), HttpStatus.OK);
    }


}

