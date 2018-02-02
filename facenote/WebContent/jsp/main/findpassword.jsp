<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action = "${pageContext.request.contextPath}/findpassword.do" method = "post">
	비밀번호 찾기<br>
	이메일 <input type = "text" name = "email">
	<input type = "submit" value = "확인">
</form>

</body>
</html>