<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List, com.food.kumhara.dto.User, com.food.kumhara.dto.Restaurant, com.food.kumhara.dto.Cart" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Kumhara - Food Delivery</title>
    <link rel="stylesheet" href="resources/css/styles.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap"
        rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>

<body>
    <nav class="navbar">
        <a href="home" class="nav-brand">Kumhara</a>
        <% 
            User user = (User) session.getAttribute("user");
            if(user == null) { 
        %>
        <div class="nav-auth">
            <a href="login" class="btn btn-login">Login</a>
            <a href="signup" class="btn btn-signup">Sign Up</a>
        </div>
        <% } else { %>
        <div class="nav-user">
            <div class="user-menu">
                <span class="user-name">Hi, <%= user.getName() %></span>
                <div class="dropdown-content" id="userDropdown">
                    <a href="profile">My Profile</a>
                    <a href="orders">My Orders</a>
                    <a href="logout">
                        <span class="material-icons">logout</span>
                        <span>Logout</span>
                    </a>
                </div>
            </div>
            <% 
                Cart cart = (Cart) session.getAttribute("cart");
                int cartCount = cart != null ? cart.getItemCount() : 0;
            %>
            <a href="cart" class="cart-icon-wrapper">
                <span class="material-icons">shopping_cart</span>
                <span class="cart-counter"><%= cartCount %></span>
            </a>
        </div>
        <% } %>
    </nav>

    <header class="hero">
        <div class="hero-content">
            <h1>Delicious Food Delivered To Your Doorstep</h1>
            <p>Order from your favorite restaurants with just a few clicks</p>
            <div class="search-bar">
                <input type="text" placeholder="Search for restaurants or dishes...">
                <button class="btn btn-search">Search</button>
            </div>
        </div>
    </header>

    <main class="container">
        <section class="featured-section">
            <h2>Featured Categories</h2>
            <div class="category-grid">
                <div class="category-card">
                    <img src="https://images.unsplash.com/photo-1628840042765-356cda07504e?w=900&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MjR8fHBpenphfGVufDB8fDB8fHww"
                        alt="Pizza">
                    <h3>Pizza</h3>
                </div>
                <div class="category-card">
                    <img src="https://images.unsplash.com/photo-1568901346375-23c9450c58cd?w=900&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8YnVyZ2VyfGVufDB8fDB8fHww"
                        alt="Burger">
                    <h3>Burger</h3>
                </div>
                <div class="category-card">
                    <img src="https://images.unsplash.com/photo-1579584425555-c3ce17fd4351?w=900&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8c3VzaGl8ZW58MHx8MHx8fDA%3D"
                        alt="Sushi">
                    <h3>Sushi</h3>
                </div>
                <div class="category-card">
                    <img src="https://images.unsplash.com/photo-1551024601-bec78aea704b?w=900&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8ZGVzc2VydHxlbnwwfHwwfHx8MA%3D%3D"
                        alt="Dessert">
                    <h3>Dessert</h3>
                </div>
            </div>
        </section>

        <section class="restaurants-section">
            <h2>Popular Restaurants</h2>
            <div class="restaurant-grid">
                <% @SuppressWarnings("unchecked") List<Restaurant> restaurants = 
                (List<Restaurant>)request.getAttribute("restaurants");
                    for (Restaurant restaurant : restaurants) {
                    %>
                    <div class="restaurant-card"
                        onclick="window.location.href='menu?restaurantId=<%= restaurant.getRestaurantId() %>'">
                        <div class="restaurant-image">
                            <img src="<%= restaurant.getImagePath() %>" alt="<%= restaurant.getName() %>">
                        </div>
                        <div class="restaurant-info">
                            <h3>
                                <%= restaurant.getName() %>
                            </h3>
                            <p class="cuisine">
                                <%= restaurant.getCuisineType() %>
                            </p>
                            <div class="restaurant-meta">
                                <span class="rating">★ <%= restaurant.getRating() %></span>
                                <span class="eta">
                                    <%= restaurant.getEta() %> mins
                                </span>
                            </div>
                        </div>
                    </div>
                    <% } %>
            </div>
        </section>
    </main>

    <footer class="footer">
        <div class="footer-content">
            <div class="footer-section">
                <h3>About Kumhara</h3>
                <p>Your favorite food delivery platform</p>
            </div>
            <div class="footer-section">
                <h3>Quick Links</h3>
                <ul>
                    <li><a href="#">About Us</a></li>
                    <li><a href="#">Contact</a></li>
                    <li><a href="#">Terms &amp; Conditions</a></li>
                    <li><a href="#">Privacy Policy</a></li>
                </ul>
            </div>
            <div class="footer-section">
                <h3>Contact Us</h3>
                <p>Email: support@kumhara.com</p>
                <p>Phone: +1 234 567 890</p>
            </div>
        </div>
        <div class="footer-bottom">
            <p>&copy; 2024 Kumhara. All rights reserved.</p>
        </div>
    </footer>

    <script>
        document.addEventListener('DOMContentLoaded', function() {
            // Cache DOM elements
            const userMenu = document.querySelector('.user-menu');
            const dropdown = document.getElementById('userDropdown');
            
            // Toggle dropdown function
            function toggleDropdown(event) {
                if (event) event.stopPropagation();
                dropdown.classList.toggle('show');
                userMenu.classList.toggle('active');
            }
            
            // Close dropdown function
            function closeDropdown() {
                dropdown.classList.remove('show');
                userMenu.classList.remove('active');
            }
            
            // Event listeners
            userMenu?.addEventListener('click', toggleDropdown);
            
            // Close dropdown when clicking outside
            document.addEventListener('click', function(event) {
                const isClickInside = userMenu?.contains(event.target);
                if (!isClickInside && dropdown?.classList.contains('show')) {
                    closeDropdown();
                }
            });
            
            // Close dropdown on escape key
            document.addEventListener('keydown', function(event) {
                if (event.key === 'Escape' && dropdown?.classList.contains('show')) {
                    closeDropdown();
                }
            });
        });
    </script>
</body>

</html>