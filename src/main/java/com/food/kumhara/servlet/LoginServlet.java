package com.food.kumhara.servlet;

import java.io.IOException;

import com.food.kumhara.dao.UserDAO;
import com.food.kumhara.dao.impl.UserDAOImpl;
import com.food.kumhara.dto.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
	private User user;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        userDAO = new UserDAOImpl();
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		user = userDAO.findByEmail(username);
		if(user != null && username.equals(user.getEmail()) && password.equals(user.getPassword())) {
			// Store user in session
			request.getSession().setAttribute("user", user);
			response.sendRedirect("home");
		} else {
			request.setAttribute("error", "Invalid email or password");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

}
