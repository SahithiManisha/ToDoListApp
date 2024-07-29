package com.todolist.controller;

import com.todolist.model.Todo;
import com.todolist.service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class TodoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6883061270308977230L;
	private TodoService todoService;

	@Override
	public void init() throws ServletException {
		todoService = new TodoService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");

		if (userId == null) {
			response.sendRedirect("login.jsp");
			return;
		}

		String action = request.getParameter("action");

		if ("edit".equals(action)) {
			int todoId = Integer.parseInt(request.getParameter("id"));
			Todo todo = todoService.getTodosByUserId(userId).stream().filter(t -> t.getId() == todoId).findFirst()
					.orElse(null);
			request.setAttribute("todo", todo);
			request.getRequestDispatcher("/editTodo.jsp").forward(request, response);
		} else if ("delete".equals(action)) {
			int todoId = Integer.parseInt(request.getParameter("id"));
			todoService.deleteTodoForUser(todoId, userId);
			response.sendRedirect("todos");
		} else {
			List<Todo> todos = todoService.getTodosByUserId(userId);
			request.setAttribute("todos", todos);
			request.getRequestDispatcher("/todos.jsp").forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");

		if (userId == null) {
			response.sendRedirect("login.jsp");
			return;
		}

		if ("add".equals(action)) {
			String title = request.getParameter("title");
			String description = request.getParameter("description");

			if (title == null || title.isEmpty()) {
				request.setAttribute("error", "Title cannot be empty");
				request.getRequestDispatcher("/addTodo.jsp").forward(request, response);
				return;
			}

			Todo todo = new Todo();
			todo.setTitle(title);
			todo.setDescription(description);
			todo.setUserId(userId);

			boolean isAdded = todoService.addTodoForUser(todo);
			if (isAdded) {
				response.sendRedirect("todos");
			} else {
				request.setAttribute("error", "Error adding todo");
				request.getRequestDispatcher("/addTodo.jsp").forward(request, response);
			}
		} else if ("update".equals(action)) {
			int todoId = Integer.parseInt(request.getParameter("id"));
			String title = request.getParameter("title");
			String description = request.getParameter("description");

			if (title == null || title.isEmpty()) {
				request.setAttribute("error", "Title cannot be empty");
				request.getRequestDispatcher("/editTodo.jsp").forward(request, response);
				return;
			}

			Todo todo = new Todo();
			todo.setId(todoId);
			todo.setTitle(title);
			todo.setDescription(description);
			todo.setUserId(userId);

			boolean isUpdated = todoService.updateTodoForUser(todo);
			if (isUpdated) {
				response.sendRedirect("todos");
			} else {
				request.setAttribute("error", "Error updating todo");
				request.getRequestDispatcher("/editTodo.jsp").forward(request, response);
			}
		}
	}
}
