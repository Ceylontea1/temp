<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 <c:if test="${result==0}"> 
	<meta http-equiv="Refresh"
	content="0;url=${pageContext.request.contextPath}/friend/searchfriendlist.do">
	<script>
		alert('친구 삭제에 실패하였습니다.');
	</script>
</c:if>
<c:if test="${result==2}">
	<meta http-equiv="Refresh"
	content="0;url='${pageContext.request.contextPath}/friend/searchfriendlist.do'">
   	<script>
   		alert('친구 삭제하였습니다.');
   		
   	</script>
</c:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>