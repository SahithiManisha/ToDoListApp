package com.todolist.controller;

import com.todolist.model.User;
import com.todolist.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


public class RegisterServlet extends HttpServlet {

    private final UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Create User object
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        if (userService.registerUser(user)) {
		    response.sendRedirect("login.jsp");
		} else {
		    // Handle registration failure (e.g., show an error message)
		    response.sendRedirect("register.jsp?error=Registration failed");
		}
    }
}
