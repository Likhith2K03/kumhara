package com.food.kumhara.servlet;

import java.io.IOException;

import com.food.kumhara.dao.MenuDAO;
import com.food.kumhara.dao.impl.MenuDAOImpl;
import com.food.kumhara.dto.Cart;
import com.food.kumhara.dto.Menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cart/*")
public class CartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MenuDAO menuDAO;

    public void init() {
        menuDAO = new MenuDAOImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getPathInfo();
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        try {
            switch (action) {
                case "/add":
                    addToCart(request, cart);
                    break;
                case "/update":
                    updateCartItem(request, cart);
                    break;
                case "/remove":
                    removeFromCart(request, cart);
                    break;
                case "/clear":
                    cart.clearCart();
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                    return;
            }
            
            // Redirect back to the menu page or cart page based on action
            String referer = request.getHeader("Referer");
            response.sendRedirect(referer != null ? referer : "menu");
            
        } catch (IllegalStateException e) {
            // Handle case when trying to add items from different restaurant
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("menu.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Display cart contents
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        
        request.setAttribute("cart", cart);
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }

    private void addToCart(HttpServletRequest request, Cart cart) {
        int menuId = Integer.parseInt(request.getParameter("menuId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        
        Menu menu = menuDAO.findById(menuId);
        if (menu != null && menu.getIsAvailable()) {
            cart.addItem(menu, quantity);
        }
    }

    private void updateCartItem(HttpServletRequest request, Cart cart) {
        int menuId = Integer.parseInt(request.getParameter("menuId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        cart.updateItemQuantity(menuId, quantity);
    }

    private void removeFromCart(HttpServletRequest request, Cart cart) {
        int menuId = Integer.parseInt(request.getParameter("menuId"));
        cart.removeItem(menuId);
    }
} 