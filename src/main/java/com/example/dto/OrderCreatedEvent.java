package com.example.dto;

public class OrderCreatedEvent {
    private Long orderId;
    private String productId;
    private int quantity;
    private String customerName;
    private double totalPrice;
    private String phone;
    private String address;
    private String trackingNumber;

    public OrderCreatedEvent() {}

    public OrderCreatedEvent(Long orderId, String productId, int quantity, String customerName, double totalPrice) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.customerName = customerName;
        this.totalPrice = totalPrice;
    }

    public OrderCreatedEvent(Long orderId, String productId, int quantity, String customerName, double totalPrice, String phone, String address, String trackingNumber) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.customerName = customerName;
        this.totalPrice = totalPrice;
        this.phone = phone;
        this.address = address;
        this.trackingNumber = trackingNumber;
    }

    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }

    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getTrackingNumber() { return trackingNumber; }
    public void setTrackingNumber(String trackingNumber) { this.trackingNumber = trackingNumber; }
}
