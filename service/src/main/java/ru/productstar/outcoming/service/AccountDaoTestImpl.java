package ru.productstar.outcoming.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.productstar.outcoming.model.Account;

import java.util.Optional;

@Service
@Profile("test")
public class AccountDaoTestImpl implements AccountDao {
    @Override
    public Optional<Account> createAccount(Account account) {
        account.setId(1L);
        return Optional.of(account);
    }
}
