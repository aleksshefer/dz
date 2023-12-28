package ru.productstar.outcoming;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

import ru.productstar.outcoming.model.Account;
import ru.productstar.outcoming.service.AccountDao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;


@SpringBootTest
class RealAccountDaoTest {
    @Autowired
    private AccountDao accountDao;

    @Autowired
    private DataSource dataSource;

    @Test
    void createAccountTest() throws SQLException {
        Account account = new Account("name", 111L);

        Optional<Account> createdAccountOptional = accountDao.createAccount(account);
        assertThat(createdAccountOptional).isNotEmpty();
        Account createdAccount = createdAccountOptional.get();
        assertThat(createdAccount.getId()).isEqualTo(1L);
        assertThat(createdAccount.getName()).isEqualTo("name");
        assertThat(createdAccount.getBalance()).isEqualTo(100L);

        try (
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement()
        ) {
            ResultSet resultSet = statement.executeQuery("select * from accounts where name = 'name'");
            assertThat(resultSet).isNotNull();

            String name = resultSet.getString(2);
            long balance = resultSet.getLong(3);
            assertThat()
        }
    }

}
