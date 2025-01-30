<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="error.jsp" %>
<%@ page import="org.example.todolist.TodoItem" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Todo App</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css">
</head>
<body>
<div class="header">
    <h1> Todo App</h1>
</div>

<h2>List of Todos</h2>
<form action="${pageContext.request.contextPath}/TodoList" method="post">
    <label>
        <input type="text" name="description" placeholder="Nueva tarea" required>
    </label>
    <input type="hidden" name="action" value="add">
    <button type="submit">new</button>
</form>
<table>
    <thead>
    <tr>
        <th>Title</th>
        <th>Description</th>
        <th>Status</th>
        <th>Due Date</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <%
        List<TodoItem> todoList = (List<TodoItem>) request.getAttribute("todoList");
        if (todoList != null) {
            for (TodoItem item : todoList) {
    %>
    <tr>
        <td><%= item.getTitle() %>
        </td>
        <td><%= item.getDescription() %>
        </td>
        <td><%= item.isCompleted() ? "Completed" : "Pending" %>
        </td>
        <td><%= item.getTargetDate() != null ? item.getTargetDate().toString() : "No due date" %>
        </td>
        <td>
            <form action="${pageContext.request.contextPath}/TodoList" method="post" style="display:inline;">
                <input type="hidden" name="id" value="<%= item.getId() %>">
                <input type="hidden" name="action" value="complete">
                <label>
                    <input type="checkbox"
                           onchange="<%  %>this.form.submit();<%  %>" <%= item.isCompleted() ? "checked" : "" %>>
                </label>
            </form>
            <form action="${pageContext.request.contextPath}/TodoList" method="post" style="display:inline;">
                <input type="hidden" name="id" value="<%= item.getId() %>">
                <input type="hidden" name="action" value="delete">
                <button type="submit">Delete</button>
            </form>
        </td>
    </tr>
    <%
            }
        }
    %>
    </tbody>
</table>
</body>
</html>