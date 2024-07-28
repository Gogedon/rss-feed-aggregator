package com.gogedon.rss_feed_aggregator.service;

import com.gogedon.rss_feed_aggregator.domain.Account;
import com.gogedon.rss_feed_aggregator.exceptions.NotFoundException;
import com.gogedon.rss_feed_aggregator.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    public static final String ACCOUNT_WITH_ID_NOT_FOUND = "Account with id %d not found.";
    public static final String ACCOUNT_WITH_USERNAME_NOT_FOUND = "Account with username %s not found.";
    public static final String ACCOUNT_WITH_API_KEY_NOT_FOUND = "Account with apiKey %s not found.";

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account getAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(ACCOUNT_WITH_ID_NOT_FOUND, id)));
    }

    @Override
    public Account getAccountByUsername(String username) {
        return accountRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException(String.format(ACCOUNT_WITH_USERNAME_NOT_FOUND, username)));
    }

    @Override
    public Account updateAccount(Long id, Account account) {
        Account existingAccount = accountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(ACCOUNT_WITH_ID_NOT_FOUND, id)));
        existingAccount.setUsername(account.getUsername());
        existingAccount.setUpdatedAt(account.getUpdatedAt());
        existingAccount.setCreatedAt(account.getCreatedAt());
        existingAccount.setApiKey(account.getApiKey());
        existingAccount.setFeeds(account.getFeeds());
        return accountRepository.save(existingAccount);
    }

    @Override
    public void deleteAccount(Long id) {
        accountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(ACCOUNT_WITH_ID_NOT_FOUND, id)));
        accountRepository.deleteById(id);
    }

    @Override
    public Account getAccountByApiKey(String apiKey) {
        return accountRepository.findByApiKey(apiKey)
                .orElseThrow(() -> new NotFoundException(String.format(ACCOUNT_WITH_API_KEY_NOT_FOUND, apiKey)));
    }
}