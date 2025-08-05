package accounts.repository;

import accounts.domain.Account;
import accounts.repositories.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryTests {
    @Autowired
    TestEntityManager entityManager;
    @Autowired
    AccountRepository accountRepository;

    @Test
    public void whenFindByAccountHolder_thenReturnAccount(){
        //given
        Account account = new Account("123", 100.0, "123444444");
        entityManager.persist(account);
        entityManager.flush();
        //when
        Account found = accountRepository.findByAccountHolder("123444444");
        //then
        assertThat(found.getAccountHolder(), equalTo(account.getAccountHolder()));
    }
}
