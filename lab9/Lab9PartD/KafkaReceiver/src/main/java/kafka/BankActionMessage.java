package kafka;

public class BankActionMessage {
    private String action;       // "create", "deposit", "withdraw"
    private long accountNumber;
    private String customerName; // only used on "create"
    private double amount;

    public BankActionMessage() {}

    public BankActionMessage(String action, long accountNumber, String customerName, double amount) {
        this.action = action;
        this.accountNumber = accountNumber;
        this.customerName = customerName;
        this.amount = amount;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "BankActionMessage{" +
                "action='" + action + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", customerName='" + customerName + '\'' +
                ", amount=" + amount +
                '}';
    }
}
