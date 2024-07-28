package com.gogedon.rss_feed_aggregator.service;

import com.gogedon.rss_feed_aggregator.domain.Account;
import com.gogedon.rss_feed_aggregator.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountUserDetailsManager implements UserDetailsManager {

  @Autowired
  private AccountRepository repository;

  @Override
  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return repository.findByUsername(username)
    .orElseThrow(
      () -> new UsernameNotFoundException("No user found with username = " + username));
  }

  @Override
  @Transactional
  public void createUser(UserDetails user) {
    repository.save((Account) user);
  }

  @Override
  @Transactional
  public void updateUser(UserDetails user) {
    repository.save((Account) user);
  }

  @Override
  @Transactional
  public void deleteUser(String username) {
    Account userDetails = repository.findByUsername(username)
      .orElseThrow(() -> new UsernameNotFoundException("No User found for username -> " + username));
    repository.delete(userDetails);
  }

  @Override
  @Transactional
  public void changePassword(String oldPassword, String newPassword) {
    Account userDetails = repository.findByPassword(oldPassword)
      .orElseThrow(() -> new UsernameNotFoundException("Invalid password "));
    userDetails.setPassword(newPassword);
    repository.save(userDetails);
  }

  @Override
  @Transactional(readOnly = true)
  public boolean userExists(String username) {
    return repository.findByUsername(username).isPresent();
  }
}