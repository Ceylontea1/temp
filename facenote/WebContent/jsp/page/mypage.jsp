<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script src = "https://code.jquery.com/jquery-3.1.1.min.js"></script>

<title>Insert title here</title>

</head>
<body>
<%@ include file = "/WEB-INF/Friend/RequestFriend.jsp" %>

	<div class="container">

<table>
	<tr>
		<td rowspan = "2">
			<img src = "${ loginUser.imagepath }" width = "150" height = "150">
		</td>
		<td>${ loginUser.name }</td>
		<td><a href = "${pageContext.request.contextPath}/album.do?email=${ loginUser.email }">사진첩</a></td>
	</tr>
	<tr>
		<td>
			<a href = "${pageContext.request.contextPath}/information.do">내 정보</a>
		</td>
		<td>
			<a href = "${pageContext.request.contextPath}/friend/searchfriendlist.do">친구목록</a>
		</td>
	</tr>
</table>
	<iframe src = "mypagecontent.do?loginEmail=${ loginUser.email }" width = "80%" height = "500" frameborder = "0">
	
	</iframe>
	</div>

</body>
</html>