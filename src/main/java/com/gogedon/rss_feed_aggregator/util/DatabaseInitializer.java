package com.gogedon.rss_feed_aggregator.util;

import com.gogedon.rss_feed_aggregator.domain.Account;
import com.gogedon.rss_feed_aggregator.domain.Authorities;
import com.gogedon.rss_feed_aggregator.domain.RoleTypes;
import com.gogedon.rss_feed_aggregator.repository.AccountRepository;
import com.gogedon.rss_feed_aggregator.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) {
        // Initialize roles
        Arrays.stream(RoleTypes.values())
                .forEach(role -> {
                    if (!roleRepository.existsByAuthority(role.name())) {
                        roleRepository.save(Authorities.builder().authority(role.name()).build());
                    }
                });

        // Create base account
        if (accountRepository.findByUsername("baseUser").isEmpty()) {
            Account account = Account.builder()
                .username("baseUser")
                .password(passwordEncoder.encode("1")) // Encode the password
                .build();
            Account createdAcc = accountRepository.save(account);
            System.out.println("successfully created base account: " + createdAcc);
            Authorities authoritiesAdmin = roleRepository.findByAuthority(RoleTypes.ADMIN.name()).orElseThrow();
            Authorities authoritiesUser = roleRepository.findByAuthority(RoleTypes.USER.name()).orElseThrow();
            createdAcc.setAuthorities(new HashSet<>(Arrays.asList(authoritiesAdmin, authoritiesUser)));
            Account acc = accountRepository.save(createdAcc);
            System.out.println("Gave account with authorities: " + acc);
        }

    }
}
