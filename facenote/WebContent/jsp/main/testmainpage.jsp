<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file = "../main/header.jsp" %>

<a href = "${pageContext.request.contextPath}/jsp/main/login.jsp">login</a>
<a href = "${pageContext.request.contextPath}/jsp/main/join.jsp">create</a>
</body>
</html>