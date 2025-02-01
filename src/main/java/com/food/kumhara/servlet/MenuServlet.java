package com.food.kumhara.servlet;

import java.io.IOException;
import java.util.List;

import com.food.kumhara.dao.MenuDAO;
import com.food.kumhara.dao.RestaurantDAO;
import com.food.kumhara.dao.impl.MenuDAOImpl;
import com.food.kumhara.dao.impl.RestaurantDAOImpl;
import com.food.kumhara.dto.Menu;
import com.food.kumhara.dto.Restaurant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/menu")
public class MenuServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
	private MenuDAO menuDAO;
	private RestaurantDAO restaurantDAO;
    
    public void init() {
        menuDAO = new MenuDAOImpl();
        restaurantDAO = new RestaurantDAOImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
        	Integer restaurantId = Integer.parseInt(request.getParameter("restaurantId"));
            Restaurant restaurant = restaurantDAO.findById(restaurantId);
            List<Menu> menuList = menuDAO.findByRestaurantId(restaurantId);
            
            if (restaurant != null) {
                request.setAttribute("restaurantName", restaurant.getName());
                request.setAttribute("cuisineType", restaurant.getCuisineType());
                request.setAttribute("rating", restaurant.getRating());
                request.setAttribute("eta", restaurant.getEta());
            }
            
            request.setAttribute("menuList", menuList);
            request.getRequestDispatcher("menu.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
} 