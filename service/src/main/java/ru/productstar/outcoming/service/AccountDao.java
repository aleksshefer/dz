package ru.productstar.outcoming.service;

import org.springframework.stereotype.Component;
import ru.productstar.outcoming.model.Account;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Optional;

@Component
public class AccountDao {
    private final DataSource dataSource;

    public AccountDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Optional<Account> createAccount(Account account) throws SQLException {
        long id;

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("insert into accounts (name, balance) values (?, ?)", Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setString(1, account.getName());
            preparedStatement.setLong(2, account.getBalance());
            preparedStatement.executeUpdate();

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    id = resultSet.getLong(1);
                    account.setId(id);
                    return Optional.of(account);
                }
            }
        }
        return Optional.empty();
    }
}
