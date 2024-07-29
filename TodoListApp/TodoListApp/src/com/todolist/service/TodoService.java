package com.todolist.service;

import com.todolist.model.Todo;
import com.todolist.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TodoService {

	// Retrieve todos by user ID
	public List<Todo> getTodosByUserId(int userId) {
		List<Todo> todos = new ArrayList<>();
		String sql = "SELECT * FROM todos WHERE user_id = ?";
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, userId);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					Todo todo = new Todo();
					todo.setId(resultSet.getInt("id"));
					todo.setTitle(resultSet.getString("title"));
					todo.setDescription(resultSet.getString("description"));
					todo.setUserId(resultSet.getInt("user_id"));
					todos.add(todo);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return todos;
	}

	// Add a new todo for a user
	public boolean addTodoForUser(Todo todo) {
		String sql = "INSERT INTO todos (title, description, user_id) VALUES (?, ?, ?)";
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, todo.getTitle());
			statement.setString(2, todo.getDescription());
			statement.setInt(3, todo.getUserId());
			return statement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateTodoForUser(Todo todo) {
		String sql = "UPDATE todos SET title = ?, description = ? WHERE id = ? AND user_id = ?";
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, todo.getTitle());
			statement.setString(2, todo.getDescription());
			statement.setInt(3, todo.getId());
			statement.setInt(4, todo.getUserId());
			return statement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteTodoForUser(int todoId, int userId) {
		String sql = "DELETE FROM todos WHERE id = ? AND user_id = ?";
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, todoId);
			statement.setInt(2, userId);
			return statement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
