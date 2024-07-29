package com.todolist.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class SessionCheckFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        String loginURI = httpRequest.getContextPath() + "/login.jsp";
        String registerURI = httpRequest.getContextPath() + "/register.jsp";
        String loginServletURI = httpRequest.getContextPath() + "/login";
        String registerServletURI = httpRequest.getContextPath() + "/register";
        String todosURI = httpRequest.getContextPath() + "/todos";
        String rootURI = httpRequest.getContextPath() + "/";

        boolean isLoggedIn = (session != null && session.getAttribute("userId") != null);
        boolean isLoginRequest = httpRequest.getRequestURI().equals(loginURI);
        boolean isRegisterRequest = httpRequest.getRequestURI().equals(registerURI);
        boolean isLoginServletRequest = httpRequest.getRequestURI().equals(loginServletURI);
        boolean isRegisterServletRequest = httpRequest.getRequestURI().equals(registerServletURI);
        boolean isRootRequest = httpRequest.getRequestURI().equals(rootURI);

        if (isLoggedIn && isRootRequest) {
            httpResponse.sendRedirect(todosURI);
        } else if (!isLoggedIn && isRootRequest) {
            httpResponse.sendRedirect(loginURI);
        } else if (isLoggedIn || isLoginRequest || isRegisterRequest || isLoginServletRequest || isRegisterServletRequest) {
            chain.doFilter(request, response);
        } else {
            httpResponse.sendRedirect(loginURI);
        }
    }

    @Override
    public void destroy() {
    }
}
