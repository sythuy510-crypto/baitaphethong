package com.example.service;

import com.example.dto.OrderCreatedEvent;
import com.example.model.Order;
import com.example.repo.OrderRepository;
import com.example.repo.InventoryRepository;
import com.example.model.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderProcessingService {
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private InventoryRepository inventoryRepository;

    @Transactional
    public void processOrder(OrderCreatedEvent event) {
        try {
            // Load order
            Order order = orderRepository.findById(event.getOrderId()).orElse(null);
            if (order == null) {
                System.err.println("Order not found: " + event.getOrderId());
                return;
            }

            // Simulate email send
            System.out.println("[ORDER " + order.getId() + "] Sending email to " + order.getCustomerName() + "...");
            Thread.sleep(800);
            order.setEmailSent(true);
            orderRepository.save(order);
            System.out.println("[ORDER " + order.getId() + "] ✓ Email sent");

            // Update inventory
            System.out.println("[ORDER " + order.getId() + "] Updating inventory...");
            Inventory inv = inventoryRepository.findById(order.getProductId())
                    .orElse(new Inventory(order.getProductId(), 0));
            inv.setQuantity(inv.getQuantity() - order.getQuantity());
            inventoryRepository.save(inv);
            order.setInventoryUpdated(true);
            orderRepository.save(order);
            System.out.println("[ORDER " + order.getId() + "] ✓ Inventory updated");

            // Simulate logging
            System.out.println("[ORDER " + order.getId() + "] Logging...");
            Thread.sleep(300);
            order.setLogged(true);
            orderRepository.save(order);
            System.out.println("[ORDER " + order.getId() + "] ✓ Logged");

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Order processing interrupted: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error processing order: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
