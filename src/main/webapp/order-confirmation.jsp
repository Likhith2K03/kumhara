<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.food.kumhara.dto.Order" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order Confirmation - Kumhara</title>
    <link rel="stylesheet" href="../resources/css/styles.css">
    <link rel="stylesheet" href="../resources/css/order-confirmation-styles.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<body>
    <nav class="navbar">
        <a href="../home" class="nav-brand">Kumhara</a>
    </nav>

    <main class="confirmation-container">
        <div class="confirmation-box">
            <span class="material-icons success-icon">celebration</span>
            <h1>Order Placed Successfully!</h1>
            <p class="order-id">Order ID: #<%= request.getAttribute("orderId") %></p>
            <p class="confirmation-message">
                Thank you for your order! We're getting everything ready.
                Track your order status below.
            </p>
            <div class="order-status">
                <h3>Order Status</h3>
                <div class="status-timeline">
                    <div class="progress-bar"></div>
                    <div class="status-item active">
                        <span class="material-icons">receipt</span>
                        <p>Order Placed</p>
                    </div>
                    <div class="status-item">
                        <span class="material-icons">restaurant</span>
                        <p>Preparing</p>
                    </div>
                    <div class="status-item">
                        <span class="material-icons">delivery_dining</span>
                        <p>On the Way</p>
                    </div>
                    <div class="status-item">
                        <span class="material-icons">home</span>
                        <p>Delivered</p>
                    </div>
                </div>
            </div>
            <div class="action-buttons">
                <a href="../orders" class="btn btn-primary">
                    <span class="material-icons">local_shipping</span>
                    Track Order
                </a>
                <a href="../home" class="btn btn-secondary">
                    <span class="material-icons">home</span>
                    Back to Home
                </a>
            </div>
        </div>
    </main>
</body>
</html> 