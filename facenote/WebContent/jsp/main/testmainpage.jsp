<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file = "../main/loginheader.jsp" %>

<a href = "${pageContext.request.contextPath}/jsp/main/login.jsp">login</a>
<a href = "${pageContext.request.contextPath}/jsp/main/join.jsp">create</a>
<a href = "${pageContext.request.contextPath}/jsp/main/findeamil.jsp">id찾기</a>
<a href = "${pageContext.request.contextPath}/jsp/main/findpassword.jsp">비밀번호 찾기</a>

</body>
</html>