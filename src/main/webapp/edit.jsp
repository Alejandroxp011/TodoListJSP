<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Task</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/styles/styles.css">
</head>
<body>
<%@include file="header.jsp" %>
<h2>Edit TODO</h2>

<%@include file="form.jsp" %>
<%@include file="footer.jsp" %>
</body>
</html>