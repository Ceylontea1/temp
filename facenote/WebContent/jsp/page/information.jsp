<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import = "model.UsersDto" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script>
	function checkPassword() {

		if((document.informationForm.newPassword.value) != ""){
			if((document.informationForm.newPassword.value) != (document.informationForm.newPasswordConfirm.value)){
				alert("변경 할 비밀번호의 값이 서로 다릅니다.");
				document.informationForm.newPassword.focus();
				return false;
			}
		}
		
		if((document.informationForm.formPassword.value) != (document.informationForm.DBPassword.value)){
			alert("현재 비밀번호가 다릅니다.");
			document.informationForm.formPassword.focus();
			return false;
		}
		
		alert("변경되었습니다.");
		 
		return true;
	}
	
	function changeProfileImage(){
		var img = new Image();
		img.src = document.informationForm.profileImagePath.value;
		
		var x = 300 / img.width;
		var winWidth = 800;
		var winHeight = 500;
		var imgWidth = 800;
		var imgHeight = img.height * x;
		
		var OpenWindow = 
			window.open('${ pageContext.request.contextPath }/changeprofileimage.do', '_blank', 'width = 800, height = 500, menubars=no, scrollbars=auto');	 
	}
</script>

</head>
<body>
<%@ include file = "../main/header.jsp" %>
<form name = "informationForm" action = "edit.do" method = "post" onsubmit = "return checkPassword();">
	<input type = "hidden" name = "DBPassword" value = ${ loginUser.password }>
	<input type = "hidden" name = "profileImagePath" value = ${ loginUser.imagepath }>
	<table>
		<tr>
			<td rowspan = "2">
				프로필 사진
			</td>
			<td>
				<img src = "${ loginUser.imagepath }" width = "150" height = "150">
			</td>
		</tr>
		<tr>
			<td>
				<a href = "#" onclick = "changeProfileImage();">변경하기</a>
			</td>
		</tr>
		<tr>
			<td>
				EMAIL
			</td>
			<td>
				${ loginUser.email }
			</td>
		</tr>
		<tr>
			<td colspan = "2">
				비밀번호 변경
			</td>
		</tr>
		<tr>
			<td>
				새 비밀번호
			</td>
			<td>
				<input type = "password" name = "newPassword" placeholder = "비밀번호 변경을 원하시면 입력해주세요.">
			</td>
		</tr>
		<tr>
			<td>
				비밀번호 확인
			</td>
			<td>
				<input type = "password" name = "newPasswordConfirm" placeholder = "비밀번호 변경을 원하시면 입력해주세요.">
			</td>
		</tr>
		<tr>
			<td>
				생일
			</td>
			<td>
				${ loginUser.birth }
			</td>
		</tr>
		<tr>
			<td>
				전화번호
			</td>
			<td>
				<input type = "text" name = "phone0" value = "${ phoneSplit[0] }" maxlength = "3" size = "3">-
				<input type = "text" name = "phone1" value = "${ phoneSplit[1] }" maxlength = "4" size = "4">-
				<input type = "text" name = "phone2" value = "${ phoneSplit[2] }" maxlength = "4" size = "4">
			</td>
		</tr>
		<tr>
			<td>
				가입일
			</td>
			<td>
				${ loginUser.regdate }
			</td>
		</tr>
		<tr>
			<td>
				비밀번호 입력
			</td>
			<td>
				<input type = "password" name = "formPassword" required>
			</td>
		</tr>
		<tr>
			<td colspan = "2" align = "right">
				<input type = "submit" value = "확인">
			</td>
		</tr>
	</table>
</form>

</body>
</html>