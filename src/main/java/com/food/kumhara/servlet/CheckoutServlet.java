package com.food.kumhara.servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.food.kumhara.dto.User;
import com.food.kumhara.dto.Cart;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		// Check if user is logged in
		User user = (User) session.getAttribute("user");
		if (user == null) {
			response.sendRedirect("login");
			return;
		}
		
		// Check if cart exists and is not empty
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart == null || cart.isEmpty()) {
			response.sendRedirect("cart");
			return;
		}
		
		// Pre-fill user details if available
		request.setAttribute("userPhone", user.getPhone());
		request.setAttribute("userAddress", user.getAddress());
		
		request.getRequestDispatcher("checkout.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
