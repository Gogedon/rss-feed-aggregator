package com.gogedon.rss_feed_aggregator.service;

import com.gogedon.rss_feed_aggregator.domain.Account;
import com.gogedon.rss_feed_aggregator.exceptions.NotFoundException;
import com.gogedon.rss_feed_aggregator.mapper.AccountMapper;
import com.gogedon.rss_feed_aggregator.repository.AccountRepository;
import com.gogedon.rss_feed_aggregator.request.LoginRequest;
import com.gogedon.rss_feed_aggregator.request.RegisterRequest;
import com.gogedon.rss_feed_aggregator.response.LoginResponse;
import com.gogedon.rss_feed_aggregator.response.RegisterResponse;
import com.gogedon.rss_feed_aggregator.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public RegisterResponse register(RegisterRequest request) {
        Account savedAccount = accountRepository.save(Account.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .build());
        return AccountMapper.mapToRegisterResponse(savedAccount);
    }

    @Override
    public LoginResponse login(LoginRequest login) {
        Account account = accountRepository.findByUsername(login.getUsername())
                .orElseThrow(() ->
                        new NotFoundException(String.format("User with username %s not found", login.getUsername())));
        String jwtToken = jwtTokenUtil.createToken(account);
        return LoginResponse.builder()
                .username(account.getUsername())
                .jwtToken(jwtToken)
                .build();
    }
}
