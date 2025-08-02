package domain;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Flight {
    @Id
    @GeneratedValue
    private long id;
    private String flightnumber;

    @Column(name = "origin") // because "from" is a SQL keyword
    private String from;

    @Column(name = "destination") // because "to" is a SQL keyword
    private String to;

    private LocalDate date;

    public Flight() {}

    public Flight(String flightnumber, String from, String to, LocalDate date) {
        this.flightnumber = flightnumber;
        this.from = from;
        this.to = to;
        this.date = date;
    }

    public Long getId() { return id; }

    public String getFlightnumber() { return flightnumber; }
    public void setFlightnumber(String flightnumber) { this.flightnumber = flightnumber; }

    public String getFrom() { return from; }
    public void setFrom(String from) { this.from = from; }

    public String getTo() { return to; }
    public void setTo(String to) { this.to = to; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", flightnumber='" + flightnumber + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", date=" + date +
                '}';
    }
}
