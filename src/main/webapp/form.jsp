<%@ page import="org.example.todolist.entity.TodoItem" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    TodoItem todoItem = (TodoItem) request.getAttribute("todoItem");
    String action = request.getParameter("action");
    String title = (todoItem != null && todoItem.getTitle() != null) ? todoItem.getTitle() : "";
    String description = (todoItem != null && todoItem.getDescription() != null) ? todoItem.getDescription() : "";
    boolean isCompleted = (todoItem != null) && todoItem.isCompleted();
    String targetDate = (todoItem != null && todoItem.getTargetDate() != null) ? todoItem.getTargetDate().toString() : "";
%>
<form action="<%= request.getContextPath() %>/TodoList" method="post">
    <label>
        <input type="text" name="title" placeholder="Title"
               value="<%= title %>" required>
    </label>
    <label>
        <input type="text" name="description" placeholder="Description"
               value="<%= description %>" required>
    </label>
    <label>
        <select name="status">
            <option value="false" <%= isCompleted ? "selected" : "" %>>Pending</option>
            <option value="true" <%= isCompleted ? "selected" : "" %>>Completed</option>
        </select>
    </label>
    <label>
        <input type="date" name="targetDate"
               value="<%= targetDate %>">
    </label>
    <input type="hidden" name="action" value="<%= action %>">
    <button type="submit">Save</button>
</form>