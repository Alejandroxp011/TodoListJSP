<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Task</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/styles.css">
</head>
<body>
<div class="header">
    <h1> Todo App</h1>
</div>

<h2>Edit TODO</h2>
<jsp:include page="form.jsp">
    <jsp:param name="action" value="edit"/>
    <jsp:param name="buttonText" value="Update Task"/>
</jsp:include>

</body>
</html>