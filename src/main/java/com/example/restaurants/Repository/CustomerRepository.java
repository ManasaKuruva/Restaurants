package com.example.restaurants.Repository;


import com.example.restaurants.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // Custom query to find customer by email
    Customer findByEmail(String email);

    // Custom query to find customer by phone number
    Customer findByPhone(String phone);
}
