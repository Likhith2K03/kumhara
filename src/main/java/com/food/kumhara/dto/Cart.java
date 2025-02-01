package com.food.kumhara.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {
    private Restaurant restaurant;
    private Map<Integer, CartItem> cartItems; // menuId -> CartItem
    private double totalAmount;

    public Cart() {
        this.cartItems = new HashMap<>();
        this.totalAmount = 0.0;
    }

    public void addItem(Menu menu, int quantity) {
        // Check if adding from the same restaurant
        if (restaurant == null) {
            restaurant = menu.getRestaurant();
        } else if (restaurant.getRestaurantId() != menu.getRestaurant().getRestaurantId()) {
            throw new IllegalStateException("Cannot add items from different restaurants");
        }

        // Update quantity if item exists, otherwise add new item
        CartItem item = cartItems.get(menu.getMenuId());
        if (item != null) {
            item.setQuantity(item.getQuantity() + quantity);
        } else {
            item = new CartItem(menu, quantity);
            cartItems.put(menu.getMenuId(), item);
        }
        calculateTotalAmount();
    }

    public void updateItemQuantity(int menuId, int quantity) {
        CartItem item = cartItems.get(menuId);
        if (item != null) {
            if (quantity <= 0) {
                cartItems.remove(menuId);
            } else {
                item.setQuantity(quantity);
            }
            calculateTotalAmount();
        }
    }

    public void removeItem(int menuId) {
        cartItems.remove(menuId);
        calculateTotalAmount();
        if (cartItems.isEmpty()) {
            restaurant = null;
        }
    }

    public void clearCart() {
        cartItems.clear();
        totalAmount = 0.0;
        restaurant = null;
    }

    private void calculateTotalAmount() {
        this.totalAmount = cartItems.values().stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();
    }

    // Getters
    public Restaurant getRestaurant() {
        return restaurant;
    }

    public List<CartItem> getCartItems() {
        return new ArrayList<>(cartItems.values());
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public int getItemCount() {
        return cartItems.values().stream()
                .mapToInt(CartItem::getQuantity)
                .sum();
    }

    public boolean isEmpty() {
        return cartItems.isEmpty();
    }

    @Override
    public String toString() {
        return "Cart{" +
                "restaurant=" + (restaurant != null ? restaurant.getName() : "null") +
                ", itemCount=" + getItemCount() +
                ", totalAmount=" + totalAmount +
                '}';
    }
} 