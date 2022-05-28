<%@ page language="java" isErrorPage="true"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Error Page</title>
</head>
<body>
<h2>
    Error Page<br/>
    <i>Error <%= exception %></i>
</h2>
<br>
<a href="${pageContext.request.contextPath}/">Home</a>
</body>
</html>