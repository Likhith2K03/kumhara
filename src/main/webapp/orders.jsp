<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List, com.food.kumhara.dto.Order" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Your Orders - Kumhara</title>
    <link rel="stylesheet" href="resources/css/styles.css">
    <link rel="stylesheet" href="resources/css/orders-styles.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<body>
    <nav class="navbar">
        <a href="home" class="nav-brand">Kumhara</a>
    </nav>

    <main class="orders-container">
        <h1>Your Orders</h1>
        
        <%
        @SuppressWarnings("unchecked")
        List<Order> orders = (List<Order>) request.getAttribute("orders");
        if(orders != null && !orders.isEmpty()) { 
        %>
            <div class="orders-list">
                <% for(Order order : orders) { %>
                    <div class="order-card">
                        <div class="order-header">
                            <div class="order-info">
                                <h3><%= order.getRestaurant().getName() %></h3>
                                <p class="order-id">Order #<%= order.getOrderId() %></p>
                                <p class="order-date"><%= order.getOrderDate() %></p>
                            </div>
                            <div class="order-status <%= order.getStatus().toLowerCase() %>">
                                <%= order.getStatus() %>
                            </div>
                        </div>
                        
                        <div class="order-details">
                            <div class="amount-info">
                                <span class="material-icons">payments</span>
                                <div>
                                    <p class="label">Total Amount</p>
                                    <p class="amount">₹<%= String.format("%.2f", order.getTotalAmount()) %></p>
                                </div>
                            </div>
                            <div class="payment-info">
                                <span class="material-icons">account_balance_wallet</span>
                                <div>
                                    <p class="label">Payment Mode</p>
                                    <p><%= order.getPaymentMode() %></p>
                                </div>
                            </div>
                        </div>
                        
                        <div class="order-timeline">
                            <div class="timeline-track">
                                <div class="progress-bar" style="width: <%= getProgressWidth(order.getStatus()) %>%"></div>
                            </div>
                            <div class="timeline-points">
                                <div class="point <%= isCompleted(order.getStatus(), "PENDING") ? "completed" : "" %>">
                                    <span class="material-icons">receipt</span>
                                    <p>Order Placed</p>
                                </div>
                                <div class="point <%= isCompleted(order.getStatus(), "PREPARING") ? "completed" : "" %>">
                                    <span class="material-icons">restaurant</span>
                                    <p>Preparing</p>
                                </div>
                                <div class="point <%= isCompleted(order.getStatus(), "ON_THE_WAY") ? "completed" : "" %>">
                                    <span class="material-icons">delivery_dining</span>
                                    <p>On the Way</p>
                                </div>
                                <div class="point <%= isCompleted(order.getStatus(), "DELIVERED") ? "completed" : "" %>">
                                    <span class="material-icons">home</span>
                                    <p>Delivered</p>
                                </div>
                            </div>
                        </div>
                    </div>
                <% } %>
            </div>
        <% } else { %>
            <div class="empty-orders">
                <span class="material-icons">receipt_long</span>
                <h2>No orders yet</h2>
                <p>Looks like you haven't placed any orders yet</p>
                <a href="home" class="browse-btn">Browse Restaurants</a>
            </div>
        <% } %>
    </main>

    <%!
    private int getProgressWidth(String status) {
        switch(status) {
            case "PENDING": return 25;
            case "PREPARING": return 50;
            case "ON_THE_WAY": return 75;
            case "DELIVERED": return 100;
            default: return 0;
        }
    }

    private boolean isCompleted(String currentStatus, String checkStatus) {
        int current = getProgressWidth(currentStatus);
        int check = getProgressWidth(checkStatus);
        return current >= check;
    }
    %>
</body>
</html> 