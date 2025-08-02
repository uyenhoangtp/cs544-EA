package bank.dto;

import bank.domain.Account;
import bank.domain.Customer;

import java.util.List;
import java.util.stream.Collectors;

public class AccountAdapter {
    public static Account getAccountFromDTO(AccountDTO dto) {
        Account account = new Account(dto.getAccountNumber());
        account.setCustomer(new Customer(dto.getCustomerName()));
        return account;
    }

    public static AccountDTO getDTOFromAccount(Account account) {
        return new AccountDTO(account.getAccountnumber(), account.getCustomer().getName());
    }

    public static List<AccountDTO> getDTOList(List<Account> accounts) {
        return accounts.stream().map(AccountAdapter::getDTOFromAccount).collect(Collectors.toList());
    }
}
