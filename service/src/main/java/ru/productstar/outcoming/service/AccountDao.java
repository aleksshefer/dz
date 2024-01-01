package ru.productstar.outcoming.service;

import ru.productstar.outcoming.model.Account;

import java.sql.SQLException;
import java.util.Optional;

public interface AccountDao {
    public Optional<Account> createAccount(Account account) throws SQLException;
}
