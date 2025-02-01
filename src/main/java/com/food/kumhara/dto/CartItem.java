package com.food.kumhara.dto;

public class CartItem {
    private Menu menu;
    private int quantity;
    private double itemPrice;
    private double totalPrice;

    // Default constructor
    public CartItem() {}

    // Constructor with menu and quantity
    public CartItem(Menu menu, int quantity) {
        this.menu = menu;
        this.quantity = quantity;
        this.itemPrice = menu.getPrice();
        calculateTotalPrice();
    }

    // Getters and Setters
    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
        this.itemPrice = menu.getPrice();
        calculateTotalPrice();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        calculateTotalPrice();
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    private void calculateTotalPrice() {
        this.totalPrice = this.quantity * this.itemPrice;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "menu=" + menu.getItemName() +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                '}';
    }
} 