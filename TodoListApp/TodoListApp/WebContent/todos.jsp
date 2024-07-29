<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Your Todos</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            color: #333;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            min-height: 100vh;
        }
        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            width: 90%;
            max-width: 1200px;
            margin: 20px 0;
        }
        h1 {
            color: #007bff;
            margin: 0;
        }
        .logout-button, .add-todo-button {
            background-color: #007bff; /* Primary color */
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            text-decoration: none;
            display: inline-block;
            text-align: center;
        }
        .logout-button:hover, .add-todo-button:hover {
            background-color: #0056b3;
        }
        .add-todo-container {
            width: 90%;
            max-width: 1200px;
            margin: 20px 0;
            display: flex;
            justify-content: flex-start; /* Align to the left */
        }
        .table-container {
            width: 90%;
            max-width: 1200px;
            margin: 20px 0;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #007bff;
            color: #fff;
            font-weight: bold;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        tr:hover {
            background-color: #e9ecef;
        }
        td a {
            text-decoration: none;
            color: #007bff;
            margin-right: 10px;
        }
        td a:hover {
            color: #0056b3;
        }
    </style>
    <script>
        function confirmDelete(event) {
            if (!confirm("Are you sure you want to delete this todo?")) {
                event.preventDefault();
            }
        }
    </script>
</head>
<body>
    <div class="header">
        <h1>Your Todos</h1>
        <form action="logout" method="post">
            <button type="submit" class="logout-button">Logout</button>
        </form>
    </div>
    <div class="add-todo-container">
        <a href="addTodo.jsp" class="add-todo-button">Add New Todo</a>
    </div>
    <div class="table-container">
        <table>
            <thead>
                <tr>
                    <th>Title</th>
                    <th>Description</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="todo" items="${todos}">
                    <tr>
                        <td>${todo.title}</td>
                        <td>${todo.description}</td>
                        <td>
                            <a href="todos?action=edit&id=${todo.id}">Edit</a>
                            <a href="todos?action=delete&id=${todo.id}" onclick="confirmDelete(event)">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
