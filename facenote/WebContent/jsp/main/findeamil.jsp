<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action = "${pageContext.request.contextPath}/findemail.do" method = "post">
	이메일 찾기<br>
	이름 : <input type = "text" name = "name"><br>
	전화번호 : <input type = "text" name = "phone1">-<input type = "text" name = "phone2">-<input type = "text" name = "phone3"><br>
	<input type = "submit" value = "확인">
</form>

</body>
</html>