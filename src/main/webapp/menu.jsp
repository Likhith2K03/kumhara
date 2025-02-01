<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List, com.food.kumhara.dto.Menu, com.food.kumhara.dto.Cart" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Restaurant Menu - Kumhara</title>
    <link rel="stylesheet" href="resources/css/styles.css">
    <link rel="stylesheet" href="resources/css/menu-styles.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap"
        rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>

<body>
    <nav class="navbar">
        <a href="home" class="nav-brand">Kumhara</a>
        <div class="nav-user">
            <% 
                Cart cart = (Cart) session.getAttribute("cart");
                int cartCount = cart != null ? cart.getItemCount() : 0;
            %>
            <a href="cart" class="cart-icon-wrapper">
                <span class="material-icons">shopping_cart</span>
                <span class="cart-counter"><%= cartCount %></span>
            </a>
        </div>
    </nav>

    <main class="container">
        <section class="menu-header">
            <div class="restaurant-info">
                <h1>
                    <%= request.getAttribute("restaurantName") %>
                </h1>
                <p class="restaurant-meta">
                    <span class="cuisine">
                        <%= request.getAttribute("cuisineType") %>
                    </span>
                    <span class="rating">★ <%= request.getAttribute("rating") %></span>
                    <span class="eta">
                        <%= request.getAttribute("eta") %> mins
                    </span>
                </p>
            </div>
            <div class="menu-filters">
                <input type="text" placeholder="Search menu items..." class="search-input">
            </div>
        </section>

        <section class="menu-list">
            <% @SuppressWarnings("unchecked") List<Menu> menuList = 
            (List<Menu>)request.getAttribute("menuList");
            if(menuList != null && !menuList.isEmpty()) {
            for(Menu item : menuList) {
            %>
			<div class="menu-item">
			    <div class="menu-image">
			        <img src="<%= item.getImagePath() != null ? item.getImagePath() :
			            "resources/images/default-food.jpg" %>"
			        alt="<%= item.getItemName() %>">
			    </div>
			    <div class="menu-info">
			        <h3>
			            <%= item.getItemName() %>
			        </h3>
			        <p class="description">
			            <%= item.getDescription() %>
			        </p>
			        <% if(item.getRatings() !=null) { %>
			            <div class="ratings">
			                <span class="stars">★</span>
			                <span class="rating-value">
			                    <%= String.format("%.1f", item.getRatings()) %>
			                </span>
			            </div>
			            <% } %>
			                <div class="menu-meta">
			                    <span class="price">₹<%= String.format("%.2f", item.getPrice()) %>
			                    </span>
			                </div>
			    </div>
			    <div class="menu-action">
			        <% if(item.getIsAvailable()) { %>
			            <form action="cart/add" method="post" class="add-to-cart-form">
			                <input type="hidden" name="menuId" value="<%= item.getMenuId() %>">
			                <input type="hidden" name="quantity" value="1">
			                <button type="submit" class="btn btn-add">ADD</button>
			            </form>
			        <% } else { %>
			            <span class="out-of-stock">Out of Stock</span>
			        <% } %>
			    </div>
			</div>
			<% } } else { %>
		    <div class="no-items">
		        <p>No menu items available at the moment.</p>
		    </div>
		    <% } %>
        </section>
    </main>

    <footer class="footer">
        <div class="footer-content">
            <p>&copy; 2024 Kumhara. All rights reserved.</p>
        </div>
    </footer>
</body>

</html>