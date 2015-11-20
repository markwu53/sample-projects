<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spring MVC</title>
</head>
<body>
    <h1>MySQL Schemas:</h1>
    <% for (String schema: (List<String>) request.getAttribute("schemas")) { %>
    <a href="<%=schema%>"><%=schema %></a><br/>
    <% } %>
</body>
</html>