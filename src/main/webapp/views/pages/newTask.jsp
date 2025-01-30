<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="error.jsp" %>
<!DOCTYPE html>
<html>
<head>
  <title>Add New Task</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css">
</head>
<body>
<div class="header">
  <h1>New TODO</h1>
</div>

<form action="${pageContext.request.contextPath}/TodoList" method="post">
  <label>
    <input type="text" name="title" placeholder="Title" required>
  </label>
  <label>
    <input type="text" name="description" placeholder="Description" required>
  </label>
  <label>
    <select name="status">
      <option value="false">Pending</option>
      <option value="true">Completed</option>
    </select>
  </label>
  <label>
    <input type="date" name="targetDate">
  </label>
  <input type="hidden" name="action" value="add">
  <button type="submit">Add Task</button>
</form>

</body>
</html>