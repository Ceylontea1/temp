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
<%@ include file = "/WEB-INF/Friend/RequestFriend.jsp" %>
	<div class="container">
<c:if test="${friendcount==0}">
	<table class="table table-hover">
		<tr><td colspan="3">친구목록</td></tr>
		<tr><td>프로필사진</td><td>친구이름</td><td>친구 끊기</td></tr>
	<form action="${pageContext.request.contextPath}/friend/firendreqinit.do">
		<tr><td colspan="3">친구가  없습니다.</td></tr>
		<tr><td colspan="3"><button type="button" class="btn btn-primary" onclick="document.location.href='${pageContext.request.contextPath}/mypage.do'">
	<span class="glyphicon glyphicon-ok"aria-hidden="true">확인</span></button></tr>
	</form>
	</table>
</c:if>
</div>
<div class="container">
<c:if test="${friendcount>0}">

	<table  class="table table-hover">
		<tr><td colspan="3">친구목록</td></tr>
		<tr><td>프로필사진</td><td>친구이름</a></td><td>친구 끊기</td></tr>
	
	<c:forEach var="list" items="${FreindList}">
		<c:set var="ID" value="${list.email}"/>
	<form action="${pageContext.request.contextPath}/friend/deletefriend.do">
	<input type="hidden" name="friendID" value="${ID}"/>
	<tr><td><img src="${list.imagepath}" width="100" height="100"/></td><td><a href="${pageContext.request.contextPath}/friendpage.do?friendmail=${list.email}">${list.name}</a></td>
	<td><button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>친구 끊기</button></td></tr>
	</form>
	</c:forEach>
	<%-- <form action="${pageContext.request.contextPath}/friend/firendreqinit.do">
	<tr><td colspan="3"><input type="submit" value="확인"/></td></tr>
	</form> --%>
	<tr><td colspan="3">
	<button type="button" class="btn btn-primary" onclick="document.location.href='${pageContext.request.contextPath}/mypage.do'">
	<span class="glyphicon glyphicon-home"aria-hidden="true"></span>확인</button></td></tr>

	</table>
</c:if>
</div>
</body>
</html>