<%@ page import="org.example.todolist.domain.entity.TodoItem" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Form</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/styles/form.css">
</head>
<body>
<%
    TodoItem todoItem = (TodoItem) session.getAttribute("item");
    String action = request.getParameter("action");
    String title = (todoItem != null && todoItem.getTitle() != null) ? todoItem.getTitle() : "";
    String description = (todoItem != null && todoItem.getDescription() != null) ? todoItem.getDescription() : "";
    boolean isCompleted = (todoItem != null) && todoItem.isCompleted();
    String targetDate = (todoItem != null && todoItem.getTargetDate() != null) ? todoItem.getTargetDate().toString() : "";
%>
<form action="<%= request.getContextPath() %>/TodoList" method="post">
    <input type="hidden" name="id" value="<%= todoItem != null ? todoItem.getId() : "" %>">
    <label>
        <input type="text" name="title" placeholder="Title"
               value="<%= title %>" required>
    </label>
    <label>
        <input type="text" name="description" placeholder="Description"
               value="<%= description %>" required>
    </label>
    <label>
        <select name="status" required>
            <option value="false" <%= isCompleted ? "selected" : "" %>>Pending</option>
            <option value="true" <%= isCompleted ? "selected" : "" %>>Completed</option>
        </select>
    </label>
    <label>
        <input type="date" name="targetDate"
               value="<%= targetDate %>" required>
    </label>
    <input type="hidden" name="action" value="<%= action %>" required>
    <button type="submit">Save</button>
</form>
</body>
</html>