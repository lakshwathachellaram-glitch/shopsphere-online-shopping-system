package com.shopsphere.shopsphere;

import java.time.LocalDateTime;

public class Order {

    private int orderId;
    private String customerName;
    private String email;
    private double total;
    private LocalDateTime orderDate;

    public Order(int orderId, String customerName, String email, double total) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.email = email;
        this.total = total;
        this.orderDate = LocalDateTime.now();
    }

    public int getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getEmail() {
        return email;
    }

    public double getTotal() {
        return total;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }
}