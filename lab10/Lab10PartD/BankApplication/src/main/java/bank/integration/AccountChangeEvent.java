package bank.integration;

public class AccountChangeEvent {
    private long accountNumber;
    private String operation;
    private double amount;

    public AccountChangeEvent() {
    }

    public AccountChangeEvent(long accountNumber, String operation, double amount) {
        this.accountNumber = accountNumber;
        this.operation = operation;
        this.amount = amount;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public String getOperation() {
        return operation;
    }

    public double getAmount() {
        return amount;
    }
}
