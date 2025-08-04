package bank.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class TraceRecord {
    @Id
    @GeneratedValue
    private long id;

    private long accountNumber;
    private String operation;
    private double amount;
    private LocalDateTime dateTime;

    public TraceRecord() {
    }

    public TraceRecord(long accountNumber, String operation, double amount) {
        this.accountNumber = accountNumber;
        this.operation = operation;
        this.amount = amount;
        this.dateTime = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
