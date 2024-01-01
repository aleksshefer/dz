package ru.productstar.outcoming;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.productstar.outcoming.model.Account;
import ru.productstar.outcoming.service.AccountDaoImpl;

import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class DIAccountDaoImplTest {
    @Autowired
    private AccountDaoImpl accountDaoImpl;

    @Test
    void createAccountTest() throws SQLException {
        String generatedName = UUID.randomUUID().toString();
        Account account = new Account(generatedName, 111L);

        Optional<Account> createdAccountOptional = accountDaoImpl.createAccount(account);
        assertThat(createdAccountOptional).isNotEmpty();
        Account createdAccount = createdAccountOptional.get();


        assertThat(generatedName).isEqualTo(createdAccount.getName());
        assertThat(111L).isEqualTo(createdAccount.getBalance());
        assertThat(1L).isEqualTo(createdAccount.getId());

    }
}
