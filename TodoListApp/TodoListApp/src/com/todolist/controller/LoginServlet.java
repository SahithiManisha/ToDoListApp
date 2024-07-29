package com.todolist.controller;

import com.todolist.model.User;
import com.todolist.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;


public class LoginServlet extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userService.authenticateUser(username, password);
		

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("userId", user.getId());
            response.sendRedirect("todos");
        } else {
            response.sendRedirect("login.jsp?error=true");
        }
    }
}
