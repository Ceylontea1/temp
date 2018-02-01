<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import = "model.UsersDto" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
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
<div class="container">
<form name = "informationForm" action = "edit.do" method = "post" onsubmit = "return checkPassword();">
 	
	<input type = "hidden" name = "DBPassword" value = ${ loginUser.password }>
	<input type = "hidden" name = "profileImagePath" value = ${ loginUser.imagepath }>
	<table  class="table table-hover">
		<tr>
			<td rowspan = "2">
			<label>	프로필 사진</label>
			</td>
			<td>
				<img src = "${pageContext.request.contextPath}/${ loginUser.imagepath }" width = "150" height = "150">
			</td>
		</tr>
		<tr>
			
			<td>
				<a href = "#" onclick = "changeProfileImage();">변경하기</a>
			</td>
		</tr>
		<tr>
			<td>
				<label>	EMAIL</label>
			</td>
			<td>
				<label>	${ loginUser.email }</label>
			</td>
		</tr>
		<tr>
			<td></td>
			<td>
			<label>	비밀번호 변경</label>
			</td>
		</tr>
		
		<tr>	
			<td>
			
				<label for="exampleInputPassword1">새 비밀번호</label>
			</td>
			<td>
			<div class="form-group">
				<input type = "password" name ="newPassword" class="form-control"  placeholder = "비밀번호 변경을 원하시면 입력해주세요.">
			</div>
			</td>
		</tr>
		
		<tr>
			<td>
			
				<label>비밀번호 확인</label>
			</td>
			<td>
			<div class="form-group">
				<input type = "password" name = "newPasswordConfirm"class="form-control" placeholder = "비밀번호 변경을 원하시면 입력해주세요.">
			</div>
			</td>
			
		</tr>
		<tr>
			<td>
			<label>생일</label>
			</td>
			<td>
			<label>${ loginUser.birth }</label>
			</td>
		</tr>
		<tr>
			<td>
			 <label>전화번호</label>
			</td>
			<td>
			
		  <div class="col-xs-7">
			  <div class="form-group phone-number">
			    <div class="col-xs-3">
			 		<input type = "text" name = "phone0" value = "${ phoneSplit[0] }" class="form-control"  type="tel" maxlength = "3" size = "3">
				   </div>
				   <div class="col-xs-1">
						<label>-</label>
				   </div>	
				 <div class="col-xs-3">
					<input type = "text" name = "phone1" value = "${ phoneSplit[1] }" class="form-control"  type="tel" maxlength = "4" size = "4">
				 </div>
				     <div class="col-xs-1">
						<label>-</label>
				   </div>
				 <div class="col-xs-4">
					<input type = "text" name = "phone2" value = "${ phoneSplit[2] }" class="form-control" type="tel" maxlength = "4" size = "4">
			     </div>
				</div>
			  </div>	
			</td>
		</tr>
		<tr>
			<td>
			   <label>가입일</label>
			</td>
			<td>
				<label>${ loginUser.regdate }</label>
			</td>
		</tr>
		<tr>
			<td>
			<label>비밀번호 입력</label>
			</td>
			<td>
				<div class="form-group">
					<input type = "password" name = "formPassword" class="form-control" placeholder = "현재 비밀번호 입력해주세요." required>
				</div>			
			</td>
		</tr>
		<tr>
			<td colspan = "2" align = "center">
			<button type="submit" class="btn btn-primary">
					<span class="glyphicon glyphicon-home" aria-hidden="true"></span>확인</button>
			</td>
		</tr>
	</table>

</form>
</div>

</body>
</html>