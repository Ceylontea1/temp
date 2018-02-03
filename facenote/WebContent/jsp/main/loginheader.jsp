<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>친구요청 버튼</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<link rel="stylesheet"	
	href="${pageContext.request.contextPath}/css/bootstrap.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/main.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">

<script>
	var opener;
	function openAlarm() {
		opener = window.open(
				'${pageContext.request.contextPath}/alarm/getALlist.do',
				'alarm', 'width=800,height=300,menubar=no');
	}
</script>

</head>
<body>
	<c:if test="${alarmCount==null}">
		<c:set var="alarmCount" value="${0}" />
	</c:if>
	<div class="header">
		<div class="container">
			<div class="row" id="fotter">
				<div class="col-md-3">
					<a href="${pageContext.request.contextPath}/mypage.do">
						<img src="${pageContext.request.contextPath}/img/facenote.png" />
					</a>
				</div>
			
		<div class="col-md-4">
			<label>FACENOTE에 오신것을 환영합니다.</label>
		</div>
		<div class="col-md-5">
			<div class="pull-right" id="upform">
					<form action="${pageContext.request.contextPath}/login.do" method="post" name="frm">
							<table>
						
					    <tr>
					    	<td>
							<th> 로그인</th>
					   		</td>
					   	</tr>
					   		 <tr>
							<td style="width: 70px;"><h4>이메일(ID)</h4></td>
							<td style="width: 500px;"><input class="form-control" type="email" id="userEmail" name="userEmail" placeholder="이메일을 입력하세요." required></td>
						
							<td style="width: 110px;"><h5>비밀번호</h5></td>
							<td colspan="5"><input class="form-control" type="password" id="userPassword1" name="userPassword1" maxlength="20" placeholder="비밀번호를 입력하세요." required></td>
					
							<td colspan="2" style="text-align: center">
							<button class="btn btn-primary" type="submit">로그인<i class="fa fa-check spaceLeft"></i></button>
					    </td>
				      </tr>
							</table>
						</form>
						</div>
					</div>	
				</div>
			</div>
		</div>
		

</body>
</html>