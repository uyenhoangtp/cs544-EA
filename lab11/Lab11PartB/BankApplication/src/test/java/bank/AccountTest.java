package bank;

import bank.domain.Account;
import bank.domain.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;

public class AccountTest {
    Account account = null;
    Account toAccount = null;

    @BeforeEach
    public void createAccount(){
        account = new Account();
        toAccount = new Account(1234);
        toAccount.setCustomer(new Customer("John"));
    }

    @Test
    public void testIncrement(){
//        Account account = new Account();
        account.deposit(100.0);
        assertThat( account.getBalance(), closeTo (100.0, 0.01));
    }

    @Test
    public void testWithdraw(){
        account.deposit(100.0);
        account.withdraw(50.0);
        assertThat(account.getBalance(), closeTo(50.0, 0.01));
    }

    @Test
    public void testTransferFunds(){
        account.deposit(100.0);
        account.transferFunds(toAccount, 50.0, "Transfer Funds");
        assertThat(account.getBalance(), closeTo(50.0,0.01));
        assertThat(toAccount.getBalance(), closeTo(50.0,0.01));
    }

}
