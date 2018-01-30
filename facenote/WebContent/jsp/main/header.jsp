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
					<div class="pull-right">
						<span>접속자 </span>
						<img src="${loginUser.imagepath}" class="img-rounded" alt="Cinque" width="79" height="80">
						<span>
							<a href="${pageContext.request.contextPath}/mypage.do">
								<font color="WHITE">
									${loginUser.name}
								</font>
							</a>
						</span>&nbsp;
						<span>
							<c:out value="${loginUser.email}" />
						</span>
					</div>
				</div>
				<div class="col-md-5">
					<div class="pull-right" id="upform">
						<button type="button" class="btn btn-primary "
							onClick="document.location.href='${pageContext.request.contextPath}/mypage.do'">
							<span class="glyphicon glyphicon-home" aria-hidden="true"></span>
							홈
						</button>
						<button type="button" class="btn btn-primary" onClick="document.location.href='${pageContext.request.contextPath}/logout.do'">
							<span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>
							로그아웃
						</button>
					</div>
					<div class="pull-right" id="rightform">
						<form>
							<button type="button" class="btn btn-primary" onClick="openAlarm();">
								<span class="glyphicon glyphicon-bell" aria-hidden="true"></span>
								알림(${alarmCount})
							</button>
							<button type="button" class="btn btn-primary"
								onClick="document.location.href='${pageContext.request.contextPath}/friend/searchfriendinit.do'">
								<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
								친구찾기
							</button>
							<button type="button" class="btn btn-primary"
								onClick="document.location.href='${pageContext.request.contextPath}/friend/searchfriendlist.do'">
								<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
								친구목록
							</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>