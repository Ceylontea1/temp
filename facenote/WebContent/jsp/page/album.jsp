<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script src = "js/script.js"></script>

</head>
<body>
<%@ include file = "../main/header.jsp" %>
<div class="container">
<table>
	<c:choose>
		<c:when test = "${ albumAccount.email eq loginUser.email }">
			<tr>
				<td rowspan = "2">
					<img src = "${ loginUser.imagepath }" width = "150" height = "150" onclick = "OnloadImg(this.src)">
				</td>
				<td>
					<a href = "${pageContext.request.contextPath}/mypage.do">${ loginUser.name }</a>
				</td>
			</tr>
			<tr>
				<td>
					<a href = "${pageContext.request.contextPath}/information.do">내 정보</a>
				</td>
				<td>
					<a href = "${pageContext.request.contextPath}/friend/searchfriendlist.do">친구목록</a>
				</td>
			</tr>
			<tr>
				<td colspan = "3">
					upload
				</td>
			</tr>
		</c:when>
		<c:when test = "${ albumAccount.email ne loginUser.email }">
			<tr>
				<td rowspan = "2">
					<img src = "${ albumAccount.imagepath }" width = "150" height = "150">
				</td>
				<td>
					<a href = "friendpage.do?friendmail=${ albumAccount.email }" target = "_parent">${ albumAccount.name }</a>
				</td>
			</tr>
			<tr>
				<td>
					<c:choose>		
						<c:when test = "${ friendState eq '친구' }">	
							<a href = "${pageContext.request.contextPath}/friend/requestfriend.do?friendID=${ friend.email }">친구신청</a>
						</c:when>
						<c:when test = "${ friendState eq '친구' }">
							<a href = "${pageContext.request.contextPath}/friend/searchfriendlist.do?email=${ friend.email }">친구목록</a>
						</c:when>
					</c:choose>
				</td>
			</tr>					
		</c:when>
	</c:choose>
</table>
	
	사진<p>

	<iframe src = "albumcontent.do?email=${ albumAccount.email }" width = "80%" height = "500" frameborder = "0">
	
	</iframe>
</div>
</body>
</html>