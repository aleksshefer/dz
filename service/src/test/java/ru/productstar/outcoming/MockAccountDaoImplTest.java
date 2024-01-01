package ru.productstar.outcoming;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import ru.productstar.outcoming.model.Account;
import ru.productstar.outcoming.service.AccountDao;

import java.sql.SQLException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class MockAccountDaoImplTest {
    @Mock
    private AccountDao accountDao;
    private Account account;

    @BeforeEach
    public void init() throws SQLException {
        account = new Account("name", 1000L);
        account.setId(1L);
        when(accountDao.createAccount(account)).thenReturn(Optional.of(account));
    }

    @Test
    void createAccountTest() throws SQLException {
        Optional<Account> createdAccountOptional = accountDao.createAccount(account);
        assertThat(createdAccountOptional).isNotEmpty();
        Account createdAccount = createdAccountOptional.get();
        assertThat(createdAccount.getId()).isEqualTo(1L);
        assertThat(createdAccount.getName()).isEqualTo("name");
        assertThat(createdAccount.getBalance()).isEqualTo(1000L);
    }
}
