<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Todo</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            color: #333;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            text-align: center;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 600px;
            width: 100%;
        }
        h1 {
            color: #007bff;
            margin-bottom: 20px;
        }
        form {
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        label {
            margin: 10px 0 5px;
            color: #555;
            font-size: 16px;
            text-align: left;
            width: 100%;
            max-width: 500px;
        }
        input[type="text"],
        textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            margin-bottom: 20px;
        }
        textarea {
            height: 100px;
            resize: vertical;
        }
        .button-container {
            display: flex;
            justify-content: space-between;
            width: 100%;
            max-width: 500px;
        }
        input[type="submit"],
        input[type="button"] {
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        input[type="button"] {
            background-color: #6c757d;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
        input[type="button"]:hover {
            background-color: #5a6268;
        }
        .error {
            color: red;
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Add Todo</h1>
        <form action="todos" method="post">
            <input type="hidden" name="action" value="add">
            <label for="title">Title:</label>
            <input type="text" id="title" name="title">
            <label for="description">Description:</label>
            <textarea id="description" name="description"></textarea>
            <div class="button-container">
                <input type="submit" value="Add Todo">
                <input type="button" value="Cancel" onclick="window.location.href='todos'">
            </div>
            <c:if test="${not empty error}">
                <p class="error">${error}</p>
            </c:if>
        </form>
    </div>
</body>
</html>
