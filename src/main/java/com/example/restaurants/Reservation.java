package com.example.restaurants;

import com.example.restaurants.Customer;
import jakarta.persistence.*;

//import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    private LocalDateTime reservationTime;
    private int partySize;
    private String status; // e.g., "Pending", "Confirmed", "Cancelled"

    // Constructors, Getters, and Setters

    public Reservation() {}

    public Reservation(Customer customer, LocalDateTime reservationTime, int partySize, String status) {
        this.customer = customer;
        this.reservationTime = reservationTime;
        this.partySize = partySize;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public LocalDateTime getReservationTime() { return reservationTime; }
    public void setReservationTime(LocalDateTime reservationTime) { this.reservationTime = reservationTime; }

    public int getPartySize() { return partySize; }
    public void setPartySize(int partySize) { this.partySize = partySize; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
