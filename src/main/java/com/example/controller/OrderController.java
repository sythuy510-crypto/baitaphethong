package com.example.controller;

import com.example.model.Order;
import com.example.dto.OrderRequest;
import com.example.dto.OrderCreatedEvent;
import com.example.repo.OrderRepository;
import com.example.service.InProcessOrderProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired(required = false)
    private InProcessOrderProducer inProcessProducer;

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest request) {
        try {
            Order order = new Order(request.getCustomerName(), request.getProductId(), 
                                   request.getQuantity(), request.getTotalPrice());
                Order saved = orderRepository.save(order);
                order.setPhone(request.getPhone());
                order.setAddress(request.getAddress());
                // tracking number may be supplied or generated; if provided use it
                if (request.getTrackingNumber() != null && !request.getTrackingNumber().isBlank()) {
                    order.setTrackingNumber(request.getTrackingNumber());
                } else {
                    // generate a simple tracking code
                    order.setTrackingNumber("TRK-" + System.currentTimeMillis());
                }
            
            // Publish event via producer (if available)
            if (inProcessProducer != null) {
                    OrderCreatedEvent event = new OrderCreatedEvent(saved.getId(), saved.getProductId(), 
                                                                   saved.getQuantity(), saved.getCustomerName(), 
                                                                   saved.getTotalPrice(), order.getPhone(), order.getAddress(), order.getTrackingNumber());
                inProcessProducer.publishOrderCreated(event);
            }
            
            return ResponseEntity.ok("Đơn hàng của bạn đã được ghi nhận. ID: " + saved.getId());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Lỗi: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Order>> listOrders() {
        return ResponseEntity.ok(orderRepository.findAll());
    }

    @GetMapping("/{id}/status")
    public ResponseEntity<?> getOrderStatus(@PathVariable Long id) {
        return orderRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
