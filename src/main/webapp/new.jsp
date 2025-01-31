<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="error.jsp" %>
<!DOCTYPE html>
<html>
<head>
  <title>Add New Task</title>
  <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/styles.css">
</head>
<body>
<div class="header">
  <h1> Todo App</h1>
</div>

<h2>New TODO</h2>
<jsp:include page="form.jsp">
  <jsp:param name="action" value="add"/>
  <jsp:param name="buttonText" value="Add Task"/>
</jsp:include>

</body>
</html>