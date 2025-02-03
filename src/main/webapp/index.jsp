<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="error.jsp" %>
<%@ page import="org.example.todolist.domain.entity.TodoItem" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Todo App</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">
</head>
<body>
<%@include file="header.jsp" %>
<h2>List of Todos</h2>
<a href="${pageContext.request.contextPath}/new.jsp">
    <button type="button">Add New Task</button>
</a>
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
                <button type="submit"><%= item.isCompleted() ? "Mark as Pending" : "Mark as Completed" %></button>
            </form>
            <form method="post" style="display:inline;" action="edit.jsp">
                <%
                  session.setAttribute("item", item);
                %>
                <input type="hidden" name="action" value="edit">
                <button type="submit">Edit</button>
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
<%@include file="footer.jsp" %>
</body>
</html>