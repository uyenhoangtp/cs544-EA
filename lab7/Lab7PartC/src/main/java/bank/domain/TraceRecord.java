package bank.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class TraceRecord {
    @Id
    @GeneratedValue
    private long id;
    private LocalDateTime timestamp;
    private String message;

    public TraceRecord() {
    }

    public TraceRecord(String message) {
        this.timestamp = LocalDateTime.now();
        this.message = message;
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
