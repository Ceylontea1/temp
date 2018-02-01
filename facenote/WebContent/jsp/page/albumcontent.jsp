<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<c:forEach var = "content" items = "${ contents }" varStatus = "status">
		<div style="cursor: pointer;" onclick="window.open('<c:url value = "/FaceNote/content.do?contentid=${ content.contentnum }" />','창이름','width=800,height=700','menubars=no, scrollbars=auto')">
			<c:if test = "${ status.index % 3 == 0 && status.index > 0 }">
				<br>
			</c:if>
			<c:if test = "${ content.imagepath ne null }">
				<img src = "${pageContext.request.contextPath}/${ content.imagepath }" width = "150" height = "150" onclick = "window.open('<c:url value = "/FaceNote/content.do?contentid=${ content.contentnum }" />','창이름','width=800,height=700','menubars=no, scrollbars=auto')">
			</c:if>
		</div>
	</c:forEach>
</body>
</html>