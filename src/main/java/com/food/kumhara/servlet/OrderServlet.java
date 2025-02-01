package com.food.kumhara.servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.food.kumhara.dao.OrderDAO;
import com.food.kumhara.dao.OrderItemDAO;
import com.food.kumhara.dao.impl.OrderDAOImpl;
import com.food.kumhara.dao.impl.OrderItemDAOImpl;
import com.food.kumhara.dto.Cart;
import com.food.kumhara.dto.CartItem;
import com.food.kumhara.dto.Order;
import com.food.kumhara.dto.OrderItem;
import com.food.kumhara.dto.User;

@WebServlet("/order/place")
public class OrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private OrderDAO orderDAO;
    private OrderItemDAO orderItemDAO;

    public void init() {
        orderDAO = new OrderDAOImpl();
        orderItemDAO = new OrderItemDAOImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        
        try {
            // Get user and cart from session
            User user = (User) session.getAttribute("user");
            Cart cart = (Cart) session.getAttribute("cart");
            
            if (user == null || cart == null || cart.isEmpty()) {
                response.sendRedirect(request.getContextPath() + "/cart");
                return;
            }

            // Create new order
            Order order = new Order();
            order.setUser(user);
            order.setRestaurant(cart.getRestaurant());
            order.setOrderDate(new Date(System.currentTimeMillis()));
            order.setTotalAmount(cart.getTotalAmount() + 45.00); // Including delivery and platform fee
            order.setStatus("PENDING");
            order.setPaymentMode(request.getParameter("paymentMode"));
            
            // Save order
            order = orderDAO.save(order);

            // Create and save order items
            List<CartItem> cartItems = cart.getCartItems();
            for (CartItem cartItem : cartItems) {
                OrderItem orderItem = new OrderItem();
                orderItem.setOrder(order);
                orderItem.setMenu(cartItem.getMenu());
                orderItem.setQuantity(cartItem.getQuantity());
                orderItem.setTotalPrice(cartItem.getTotalPrice());
                orderItemDAO.save(orderItem);
            }

            // Clear cart after successful order
            session.removeAttribute("cart");

            // Redirect to order confirmation page
            response.sendRedirect(request.getContextPath() + "/order/confirmation?orderId=" + order.getOrderId());

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Failed to place order. Please try again.");
            request.getRequestDispatcher("/checkout.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/cart");
    }
} 