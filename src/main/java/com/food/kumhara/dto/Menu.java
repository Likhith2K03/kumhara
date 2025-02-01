package com.food.kumhara.dto;

import javax.persistence.*;

@Entity
@Table(name = "menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private int menuId;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @Column(name = "item_name", nullable = false, length = 100)
    private String itemName;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private Double price;

    @Column(name = "ratings")
    private Float ratings;

    @Column(name = "is_available", nullable = false)
    private Boolean isAvailable;

    @Column(name = "image_path", length = 255)
    private String imagePath;

    // Default constructor
    public Menu() {}

    // Parameterized constructor
    public Menu(int menuId, String itemName, String description, double price, float ratings, boolean isAvailable, String imagePath) {
        this.menuId = menuId;
        this.itemName = itemName;
        this.description = description;
        this.price = price;
        this.ratings = ratings;
        this.isAvailable = isAvailable;
        this.imagePath = imagePath;
    }

    // Getters and Setters
    
    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return itemName;
    }

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Float getRatings() {
		return ratings;
	}

	public void setRatings(Float ratings) {
		this.ratings = ratings;
	}

	public Boolean getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
}