package com.example.restaurants;

import com.example.restaurants.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

    // Custom query methods (optional)
    List<MenuItem> findByCategory(String category);
}
