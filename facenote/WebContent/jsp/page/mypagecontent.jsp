<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>
	<div class = "content"><!-- scope 0 : 모두 공개, 1 : 친구 공개 -->
		<c:forEach var = "content" items = "${ contents }" varStatus = "status">		
			<c:set var = "scope" value = "${ content.scope }" />
<<<<<<< HEAD
			<c:if test = "${ scope eq '0' || scope eq '1' }">			
				to : ${ contentLocation.get(status.index) }
				writer : 
				<a href = "${pageContext.request.contextPath}/friendpage.do?friendmail=${ content.writer }" target = "_parent">${ writer.get(status.index) }</a><br>
				<div style="cursor: pointer;" onclick="window.open('<c:url value = "/FaceNote/content.do?contentid=${ content.contentnum }" />','창이름','width=800,height=700','menubars=no, scrollbars=auto')">
					content : ${ content.content }<br>
					<c:if test = "${ content.imagepath ne null }">
						${pageContext.request.contextPath}/${ content.imagepath }
						<img src = "${pageContext.request.contextPath}/${ content.imagepath }" width = "100" height = "100">
					</c:if>
					<br>
					regdate : ${ content.regdate }<br>
					good : ${ content.good }<br>
					<hr>
					<br>
				</div>
			</c:if>
			<c:if test = "${ loginUser.email eq content.writer }">
				<div style="cursor: pointer;" onclick="window.open('<c:url value = "/FaceNote/content.do?contentid=${ content.contentnum }" />','창이름','width=800,height=700','menubars=no, scrollbars=auto')">
					content : ${ content.content }<br>
					<c:if test = "${ content.imagepath ne null }">
						<img src = "${pageContext.request.contextPath}/${ content.imagepath }" width = "100" height = "100">
					</c:if>
					<br>
					regdate : ${ content.regdate }<br>
					<c:if test = "${ scope eq '0' || scope eq '1' }">
=======
			<c:choose>
				<c:when test = "${ scope eq '0' || scope eq '1' }">			
					to : ${ contentLocation.get(status.index) }
					writer : 
					<a href = "${pageContext.request.contextPath}/friendpage.do?friendmail=${ content.writer }" target = "_parent">${ writer.get(status.index) }</a><br>
					<div style="cursor: pointer;" onclick="window.open('<c:url value = "/FaceNote/content.do?contentid=${ content.contentnum }" />','창이름','width=800,height=700','menubars=no, scrollbars=auto')">
						content : ${ content.content }<br>
						<c:if test = "${ content.imagepath ne null }">
							<img src = "${pageContext.request.contextPath}/${ content.imagepath }" width = "100" height = "100">
						</c:if>
						<br>
						regdate : ${ content.regdate }<br>
>>>>>>> origin/imgpathtest
						good : ${ content.good }<br>
						<hr>
						<br>
					</div>
				</c:when>
				<c:when test = "${ loginUser.email eq content.writer }">
					<div style="cursor: pointer;" onclick="window.open('<c:url value = "/FaceNote/content.do?contentid=${ content.contentnum }" />','창이름','width=800,height=700','menubars=no, scrollbars=auto')">
						content : ${ content.content }<br>
						<c:if test = "${ content.imagepath ne null }">
							<img src = "${pageContext.request.contextPath}/${ content.imagepath }" width = "100" height = "100">
						</c:if>
						<br>
						regdate : ${ content.regdate }<br>
						<c:if test = "${ scope eq '0' || scope eq '1' }">
							good : ${ content.good }<br>
						</c:if>
						<hr>
						<br>
					</div>
				</c:when>
			</c:choose>
		</c:forEach>
	</div>

</body>
</html>