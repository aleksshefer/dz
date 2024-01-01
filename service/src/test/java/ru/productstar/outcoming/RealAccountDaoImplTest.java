package ru.productstar.outcoming;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

import ru.productstar.outcoming.model.Account;
import ru.productstar.outcoming.service.AccountDaoImpl;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;


@SpringBootTest
class RealAccountDaoImplTest {
    @Autowired
    private AccountDaoImpl accountDaoImpl;

    @Autowired
    private DataSource dataSource;

    @Test
    void createAccountTest() throws SQLException {
        String generatedName = UUID.randomUUID().toString();
        Account account = new Account(generatedName, 111L);

        Optional<Account> createdAccountOptional = accountDaoImpl.createAccount(account);
        assertThat(createdAccountOptional).isNotEmpty();
        Account createdAccount = createdAccountOptional.get();

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement
                        = connection.prepareStatement("select * from accounts where name = ?")
        ) {
            preparedStatement.setString(1, generatedName);
            ResultSet resultSet = preparedStatement.executeQuery();

            assertThat(resultSet.next()).isTrue();

            long idToCheck = resultSet.getLong(1);
            String nameToCheck = resultSet.getString(2);
            long balanceToCheck = resultSet.getLong(3);
            assertThat(nameToCheck).isEqualTo(createdAccount.getName());
            assertThat(balanceToCheck).isEqualTo(createdAccount.getBalance());
            assertThat(idToCheck).isEqualTo(createdAccount.getId());
        }
    }

}
