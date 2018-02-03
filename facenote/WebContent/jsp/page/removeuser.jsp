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
<%@ include file = "../main/header.jsp" %>

<div class="container">
	<form action = "userremove.do" method = "post" name = "userremove">
		회원탈퇴를 하시려면 비밀번호를 다시 입력해주세요.<br>
		비밀번호 <input type = "password" name = "password" required><br>
		<input type = "button" onclick = "history.go(-1);" value = "취소"> 
		<input type = "submit" value = "확인">
	</form>
</div>

</body>
</html>