package com.food.kumhara.dto;

import javax.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "`order`")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;


	@Column(name = "order_date", updatable = false)
    private Date orderDate;

    @Column(name = "total_amount", nullable = false, precision = 10, scale = 2)
    private Double totalAmount;

    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "payment_mode", length = 50)
    private String paymentMode;


    // Default constructor
    public Order() {}

    // Parameterized constructor
    public Order(int orderId, Date orderDate, double totalAmount, String status, String paymentMode) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.status = status;
        this.paymentMode = paymentMode;
    }

    // Getters and Setters
    public User getUser() {
    	return user;
    }
    
    public void setUser(User user) {
    	this.user = user;
    }
    
    public Restaurant getRestaurant() {
    	return restaurant;
    }
    
    public void setRestaurant(Restaurant restaurant) {
    	this.restaurant = restaurant;
    }
    
    public void setTotalAmount(Double totalAmount) {
    	this.totalAmount = totalAmount;
    }
    
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }
    
    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return status;
    }
}