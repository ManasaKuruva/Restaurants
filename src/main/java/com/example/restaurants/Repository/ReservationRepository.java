package com.example.restaurants.Repository;

import com.example.restaurants.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    // Custom query to find reservations by customer ID
    List<Reservation> findByCustomerId(Long customerId);

    // Custom query to find reservations by status
    List<Reservation> findByStatus(String status);
}
