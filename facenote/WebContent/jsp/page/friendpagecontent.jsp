<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<%@ page import = "java.util.ArrayList, model.ContentsDto" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script src = "js/script.js"></script>

</head>
<body>
	<div class = "content"><!-- scope 0 : 모두 공개, 1 : 친구 공개 -->
		<c:forEach var = "content" items = "${ contents }" varStatus = "status">
			<%-- <div style=" cursor: pointer;" onclick="location.href='${pageContext.request.contextPath}/FaceNote/content.do?contentid=${ content.contentnum });'"> --%>
			<div style=" cursor: pointer;"onclick = "readContent(${pageContext.request.contextPath}/FaceNote/content.do?contentid=${ content.contentnum });">
				<c:set var = "scope" value = "${ content.scope }" />
				<c:if test = "${ scope eq '0' || scope eq '1' }">			
					to : ${ contentLocation.get(status.index) }
					writer : 
						<a href = "${pageContext.request.contextPath}/friendpage.do?friendmail=${ content.writer }" target = "_parent">${ writer.get(status.index) }</a><br>
					content : ${ content.content }<br>
					<c:if test = "${ content.imagepath ne null }">
						<img src = "${pageContext.request.contextPath}/${ content.imagepath }" width = "100" height = "100">
					</c:if>
					<br>
					regdate : ${ content.regdate }<br>
					good : ${ content.good }<br>
					<hr>
					<br>
				</c:if>
			</div>
		</c:forEach>
	</div>

</body>
</html>