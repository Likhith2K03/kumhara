package com.food.kumhara.dto;

import javax.persistence.*;

@Entity
@Table(name = "order_item")
public class OrderItem {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderitem_id")
    private int orderItemId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "total_price", nullable = false, precision = 10, scale = 2)
    private Double totalPrice;


    // Default constructor
    public OrderItem() {
    }

    // Parameterized constructor
    public OrderItem(int orderItemId, int quantity, Double totalPrice) {
        this.orderItemId = orderItemId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    // Getters and Setters
    public Order getOrder() {
    	return order;
    }
    
    public void setOrder(Order order) {
    	this.order = order;
    }
    
    public Menu getMenu() {
    	return menu;
    }
    
    public void setMenu(Menu menu) {
    	this.menu = menu;
    }
    
    public int getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}