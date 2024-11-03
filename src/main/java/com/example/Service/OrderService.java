package com.example.Service;

import com.example.restaurants.MenuItem;
import com.example.restaurants.MenuItemRepository;
import com.example.restaurants.Order;
import com.example.restaurants.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final MenuItemRepository menuItemRepository;

    // Constructor for dependency injection
    @Autowired
    public OrderService(OrderRepository orderRepository, MenuItemRepository menuItemRepository) {
        this.orderRepository = orderRepository;
        this.menuItemRepository = menuItemRepository;
    }

    // Getters for repositories (if needed elsewhere)
    public OrderRepository getOrderRepository() { return orderRepository; }
    public MenuItemRepository getMenuItemRepository() { return menuItemRepository; }

    // Business Logic Methods

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public Order placeOrder(Order order, List<Long> menuItemIds) {
        List<MenuItem> menuItems = menuItemRepository.findAllById(menuItemIds);
        order.setMenuItems(menuItems);
        order.setOrderTime(LocalDateTime.now());
        order.setStatus("Pending");
        double totalPrice = menuItems.stream().mapToDouble(MenuItem::getPrice).sum();
        order.setTotalPrice(totalPrice);
        return orderRepository.save(order);
    }

    public Order updateOrderStatus(Long id, String status) {
        Order order = getOrderById(id);
        order.setStatus(status);
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
        } else {
            throw new RuntimeException("Order with ID " + id + " does not exist.");
        }
    }

}
