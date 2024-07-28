package com.gogedon.rss_feed_aggregator.service;

import com.gogedon.rss_feed_aggregator.domain.Account;

import java.util.List;

public interface AccountService {
    Account saveAccount(Account account);
    List<Account> getAllAccounts();
    Account getAccountById(Long id);
    Account getAccountByUsername(String username);
    Account updateAccount(Long id, Account account);
    void deleteAccount(Long id);

    Account getAccountByApiKey(String apiKey);
}