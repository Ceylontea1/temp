<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<%@ page import = "java.util.ArrayList, model.ContentsDto" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script src = "https://code.jquery.com/jquery-3.1.1.min.js"></script>

<title>Insert title here</title>

</head>
<body>
<%@ include file = "../main/header.jsp" %>
<table>
	<tr>
		<td rowspan = "2">
			<img src = "${ friend.imagepath }" width = "150" height = "150">
		</td>
		<td>${ friend.name }</td>
		<td></td>
	</tr>
	<tr>
		<td><a href = "${pageContext.request.contextPath}/album.do?email=${ friend.email }">사진첩</a></td>
		<c:choose>		
			<c:when test = "${ friendState eq '요청' }">	
				<td>친구신청</td>
			</c:when>
			<c:when test = "${ friendState eq '친구' }">
				<td>친구목록</td>
			</c:when>
		</c:choose>
		
	</tr>
</table>
	<iframe src = "friendpagecontent.do?friendmail=${ friend.email }" width = "80%" height = "500" frameborder = "0">
	
	</iframe>

</body>
</html>