package bank.kafka;

import bank.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class BankKafkaListener {
    @Autowired
    private IAccountService accountService;

    @KafkaListener(topics = "bank-topic", groupId = "bank-group")
    public void handleMessage(BankActionMessage msg) {
        System.out.println("Received command: " + msg.getAction());

        switch (msg.getAction()) {
            case "create" -> accountService.createAccount(msg.getAccountNumber(), msg.getCustomerName());
            case "deposit" -> accountService.deposit(msg.getAccountNumber(), msg.getAmount());
            case "withdraw" -> accountService.withdraw(msg.getAccountNumber(), msg.getAmount());
            default -> System.out.println("Unknown action: " + msg.getAction());
        }
    }
}
