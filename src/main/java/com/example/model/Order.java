package com.example.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private String productId;
    private int quantity;
    private double totalPrice;
    private String phone;
    private String address;
    private String trackingNumber;

    private Instant createdAt;
    private boolean emailSent = false;
    private boolean inventoryUpdated = false;
    private boolean logged = false;

    public Order() {
        this.createdAt = Instant.now();
    }

    public Order(String customerName, String productId, int quantity, double totalPrice) {
        this.customerName = customerName;
        this.productId = productId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.createdAt = Instant.now();
    }

    public Order(String customerName, String productId, int quantity, double totalPrice, String phone, String address, String trackingNumber) {
        this.customerName = customerName;
        this.productId = productId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.phone = phone;
        this.address = address;
        this.trackingNumber = trackingNumber;
        this.createdAt = Instant.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

    public boolean isEmailSent() { return emailSent; }
    public void setEmailSent(boolean emailSent) { this.emailSent = emailSent; }

    public boolean isInventoryUpdated() { return inventoryUpdated; }
    public void setInventoryUpdated(boolean inventoryUpdated) { this.inventoryUpdated = inventoryUpdated; }

    public boolean isLogged() { return logged; }
    public void setLogged(boolean logged) { this.logged = logged; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getTrackingNumber() { return trackingNumber; }
    public void setTrackingNumber(String trackingNumber) { this.trackingNumber = trackingNumber; }
}
