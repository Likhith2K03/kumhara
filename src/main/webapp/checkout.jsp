<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.food.kumhara.dto.User" %>
<%@ page import="java.util.List, com.food.kumhara.dto.Cart, com.food.kumhara.dto.CartItem" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Checkout - Kumhara</title>
    <link rel="stylesheet" href="resources/css/styles.css">
    <link rel="stylesheet" href="resources/css/checkout-styles.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<body>
    <nav class="navbar">
        <a href="home" class="nav-brand">Kumhara</a>
    </nav>

    <main class="checkout-container">
        <% 
        User user = (User) session.getAttribute("user");
        if(user == null) {
            response.sendRedirect("login");
            return;
        }
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart != null && !cart.isEmpty()) { 
        %>
            <div class="checkout-content">
                <% if (request.getAttribute("error") != null) { %>
                    <div class="error-alert">
                        <span class="material-icons">error_outline</span>
                        <p><%= request.getAttribute("error") %></p>
                        <button class="close-alert" onclick="this.parentElement.style.display='none'">
                            <span class="material-icons">close</span>
                        </button>
                    </div>
                <% } %>
                <!-- Delivery Details Section -->
                <section class="checkout-section delivery-details">
                    <h2>Delivery Details</h2>
                    <div class="checkout-form">
                        <div class="form-group">
                            <label for="address">Delivery Address</label>
                            <textarea id="address" name="address" required 
                                    placeholder="Enter your complete delivery address"></textarea>
                        </div>
                        
                        <div class="form-row">
                            <div class="form-group">
                                <label for="phone">Phone Number</label>
                                <input type="tel" id="phone" name="phone" required 
                                       placeholder="Enter contact number">
                            </div>
                            <div class="form-group">
                                <label for="landmark">Landmark (Optional)</label>
                                <input type="text" id="landmark" name="landmark" 
                                       placeholder="Any nearby landmark">
                            </div>
                        </div>

                        <div class="delivery-instructions">
                            <label for="instructions">Delivery Instructions (Optional)</label>
                            <textarea id="instructions" name="instructions" 
                                    placeholder="Any specific instructions for delivery"></textarea>
                        </div>
                    </div>
                </section>

                <!-- Order Summary Section -->
                <section class="checkout-section order-summary">
                    <h2>Order Summary</h2>
                    <div class="restaurant-info">
                        <h3>From <%= cart.getRestaurant().getName() %></h3>
                        <p class="delivery-eta">Estimated delivery: <%= cart.getRestaurant().getEta() %> mins</p>
                    </div>

                    <div class="order-items">
                        <% for(CartItem item : cart.getCartItems()) { %>
                            <div class="order-item">
                                <div class="item-info">
                                    <span class="item-quantity"><%= item.getQuantity() %>×</span>
                                    <span class="item-name"><%= item.getMenu().getItemName() %></span>
                                </div>
                                <span class="item-price">₹<%= String.format("%.2f", item.getTotalPrice()) %></span>
                            </div>
                        <% } %>
                    </div>

                    <div class="price-breakdown">
                        <div class="price-row">
                            <span>Item Total</span>
                            <span>₹<%= String.format("%.2f", cart.getTotalAmount()) %></span>
                        </div>
                        <div class="price-row">
                            <span>Delivery Fee</span>
                            <span>₹40.00</span>
                        </div>
                        <div class="price-row">
                            <span>Platform Fee</span>
                            <span>₹5.00</span>
                        </div>
                        <div class="price-row total">
                            <span>To Pay</span>
                            <span>₹<%= String.format("%.2f", cart.getTotalAmount() + 45.00) %></span>
                        </div>
                    </div>

                    <div class="payment-methods">
                        <h3>Payment Method</h3>
                        <form action="order/place" method="post" id="orderForm">
                            <input type="hidden" name="address" id="formAddress">
                            <input type="hidden" name="phone" id="formPhone">
                            <input type="hidden" name="landmark" id="formLandmark">
                            <input type="hidden" name="instructions" id="formInstructions">
                            <label class="payment-option">
                                <input type="radio" name="paymentMode" value="COD" checked required>
                                <span>Cash on Delivery</span>
                            </label>
                            <label class="payment-option">
                                <input type="radio" name="paymentMode" value="ONLINE" required>
                                <span>Pay Online</span>
                            </label>
                            <button type="submit" class="place-order-btn" onclick="return validateAndSubmit()">Place Order</button>
                        </form>
                    </div>
                </section>
            </div>
        <% } else { %>
            <div class="empty-checkout">
                <img src="resources/images/empty-cart.png" alt="Empty Cart">
                <h2>Your cart is empty</h2>
                <p>Add items to your cart to proceed with checkout</p>
                <a href="home" class="browse-btn">Browse Restaurants</a>
            </div>
        <% } %>
    </main>
    <script>
        function validateAndSubmit() {
            // Get values from the form fields
            const address = document.getElementById('address').value;
            const phone = document.getElementById('phone').value;
            const landmark = document.getElementById('landmark').value;
            const instructions = document.getElementById('instructions').value;
            
            // Validate required fields
            if (!address.trim()) {
                alert('Please enter delivery address');
                return false;
            }
            if (!phone.trim()) {
                alert('Please enter phone number');
                return false;
            }
            
            // Set values to hidden fields
            document.getElementById('formAddress').value = address;
            document.getElementById('formPhone').value = phone;
            document.getElementById('formLandmark').value = landmark;
            document.getElementById('formInstructions').value = instructions;
            
            return true;
        }
    </script>
</body>
</html> 