<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List, com.food.kumhara.dto.Cart, com.food.kumhara.dto.CartItem" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Your Cart - Kumhara</title>
    <link rel="stylesheet" href="resources/css/styles.css">
    <link rel="stylesheet" href="resources/css/cart-styles.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0&icon_names=production_quantity_limits" />
</head>
<body>
    <nav class="navbar">
        <a href="home" class="nav-brand">Kumhara</a>
    </nav>

    <main class="container">
        <% 
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart != null && !cart.isEmpty()) { 
            List<CartItem> cartItems = cart.getCartItems();
        %>
            <div class="cart-header">
                <h1>Your Cart</h1>
                <p class="restaurant-name">From <%= cart.getRestaurant().getName() %></p>
            </div>

            <div class="cart-content">
                <div class="cart-items">
                    <% for(CartItem item : cartItems) { %>
                        <div class="cart-item">
                            <div class="item-image">
                                <img src="<%= item.getMenu().getImagePath() != null ? 
                                    item.getMenu().getImagePath() : "resources/images/default-food.jpg" %>" 
                                    alt="<%= item.getMenu().getItemName() %>">
                            </div>
                            <div class="item-details">
                                <h3><%= item.getMenu().getItemName() %></h3>
                                <p class="item-price">₹<%= String.format("%.2f", item.getItemPrice()) %></p>
                            </div>
                            <div class="item-actions">
                                <form action="cart/update" method="post" class="quantity-controls">
                                    <input type="hidden" name="menuId" value="<%= item.getMenu().getMenuId() %>">
                                    <button type="submit" name="quantity" value="<%= item.getQuantity() - 1 %>" 
                                            class="qty-btn" <%= item.getQuantity() <= 1 ? "disabled" : "" %>>−</button>
                                    <span class="quantity"><%= item.getQuantity() %></span>
                                    <button type="submit" name="quantity" value="<%= item.getQuantity() + 1 %>" 
                                            class="qty-btn">+</button>
                                </form>
                                <span class="item-total">₹<%= String.format("%.2f", item.getTotalPrice()) %></span>
                                <form action="cart/remove" method="post" class="remove-form">
                                    <input type="hidden" name="menuId" value="<%= item.getMenu().getMenuId() %>">
                                    <button type="submit" class="remove-btn">×</button>
                                </form>
                            </div>
                        </div>
                    <% } %>
                </div>

                <div class="cart-summary">
                    <h2>Bill Details</h2>
                    <div class="summary-item">
                        <span>Item Total</span>
                        <span>₹<%= String.format("%.2f", cart.getTotalAmount()) %></span>
                    </div>
                    <div class="summary-item">
                        <span>Delivery Fee</span>
                        <span>₹40.00</span>
                    </div>
                    <div class="summary-item">
                        <span>Platform Fee</span>
                        <span>₹5.00</span>
                    </div>
                    <div class="summary-item total">
                        <span>To Pay</span>
                        <span>₹<%= String.format("%.2f", cart.getTotalAmount() + 45.00) %></span>
                    </div>
                    <form action="checkout" method="get" onsubmit="return checkLogin()">
                        <button type="submit" class="checkout-btn">Proceed to Checkout</button>
                    </form>
                </div>
            </div>
        <% } else { %>
            <div class="empty-cart">
                <span class="material-symbols-outlined empty-cart-icon">production_quantity_limits</span>
                <h2>Your cart is empty</h2>
                <p>Add items from a restaurant to start your order</p>
                <a href="home" class="browse-btn">Browse Restaurants</a>
            </div>
        <% } %>
    </main>
    <script>
        function checkLogin() {
            <% if(session.getAttribute("user") == null) { %>
                window.location.href = 'login';
                return false;
            <% } %>
            return true;
        }
    </script>
</body>
</html> 