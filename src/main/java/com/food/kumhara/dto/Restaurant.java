package com.food.kumhara.dto;

import javax.persistence.*;

@Entity
@Table(name = "restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    private int restaurantId;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "address", nullable = false, length = 255)
    private String address;

    @Column(name = "phone", length = 15)
    private String phone;

    @Column(name = "rating")
    private Float rating;

    @Column(name = "cuisine_type", length = 100)
    private String cuisineType;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @Column(name = "eta")
    private Integer eta;

    @Column(name = "admin_user_id")
    private Integer adminUserId;

    @Column(name = "image_path", length = 255)
    private String imagePath;

    // Default constructor
    public Restaurant() {}

    // Parameterized constructor
    public Restaurant(int restaurantId, String name, String address, String phone, float rating, String cuisineType, boolean isActive, int eta, int adminUserId, String imagePath) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.rating = rating;
        this.cuisineType = cuisineType;
        this.isActive = isActive;
        this.eta = eta;
        this.adminUserId = adminUserId;
        this.imagePath = imagePath;
    }

    // Getters and Setters
    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public int getEta() {
        return eta;
    }

    public void setEta(int eta) {
        this.eta = eta;
    }

    public int getAdminUserId() {
        return adminUserId;
    }

    public void setAdminUserId(int adminUserId) {
        this.adminUserId = adminUserId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    
    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return name;
    }
}