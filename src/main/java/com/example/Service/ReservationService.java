package com.example.Service;

import com.example.restaurants.Customer;
import com.example.restaurants.Repository.CustomerRepository;
import com.example.restaurants.Repository.ReservationRepository;
import com.example.restaurants.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final CustomerRepository customerRepository;

    // Constructor for dependency injection
    @Autowired
    public ReservationService(ReservationRepository reservationRepository, CustomerRepository customerRepository) {
        this.reservationRepository = reservationRepository;
        this.customerRepository = customerRepository;
    }

    // Getters for repositories
    public ReservationRepository getReservationRepository() { return reservationRepository; }
    public CustomerRepository getCustomerRepository() { return customerRepository; }

    // Business Logic Methods

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
    }

    public Reservation makeReservation(Long customerId, LocalDateTime reservationTime, int partySize) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        Reservation reservation = new Reservation(customer, reservationTime, partySize, "Pending");
        return reservationRepository.save(reservation);
    }

    public Reservation updateReservationStatus(Long id, String status) {
        Reservation reservation = getReservationById(id);
        reservation.setStatus(status);
        return reservationRepository.save(reservation);
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}
