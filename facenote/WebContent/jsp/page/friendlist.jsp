<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>친구목록</title>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>

<%@ include file = "../main/header.jsp" %>
	
<div class="container">
<c:if test="${friendcount==0}">
	<table border="1" class="table table-hover">
		<tr><td>친구목록</td></tr>
		<tr><td>프로필사진</td><td>친구이름</td><td>친구 끊기</td></tr>
	<form action="${pageContext.request.contextPath}/friend/firendreqinit.do">
		<tr><td>친구가  없습니다.</td></tr>
		<tr><td><input type="submit" value="확인"/></td></tr>
	</form>
	</table>
</c:if>
</div>
<div class="container">
<c:if test="${friendcount>0}">

	<table  class="table table-hover">
		<tr><td>프로필사진</td><td>친구이름</td><td>친구 끊기</td></tr>
	<c:forEach var="list" items="${FreindList}">
		<c:set var="ID" value="${list.email}"/>
	<form action="${pageContext.request.contextPath}/friend/deletefriend.do">
	<input type="hidden" name="friendID" value="${ID}"/>
	<tr><td><img src="${list.imagepath}" width="100" height="100"/></td><td><a href="#">${list.name}</a></td>
	<td><input type="submit" value="친구 끊기"/></td></tr>
	</form>
	</c:forEach>
	<tr><td colspan="3">
	<input type="button" onclick="document.location.href='${pageContext.request.contextPath}/mypage.do'"
			 value="확인"/></td></tr>

	</table>
</c:if>
</div>
</body>
</html>