<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script>
	function closeWindow(){
		self.close();
	}
	
	function changeWindow(){
		opener.location.href = document.tempForm.href.value;
		self.close();
	}
</script>

</head>
<body>
<div class="container">	
	<c:if test="${alarmCount==0}">
		<table class="table table-hover">
			<tr>
				<td colspan="4">
					<h5>${loginUser.name}알림</h5>
				</td>
			</tr>
			<tr>
				<td>프로필사진</td>
				<td>이름</td>
				<td>내용</td>
				<td></td>
			</tr>
			
			
				<tr>
					<td colspan="4">알림글이 없습니다.</td>
				</tr>
				<tr>
				
					<td colspan="3" align="center"><button type="button" class="btn btn-primary" onClick=" closeWindow();">
					<span class="glyphicon glyphicon-home" aria-hidden="true"></span>홈</button></td>
				
				</tr>

		</table>
	</c:if>
	<c:if test="${alarmCount>0}">
		<table class="table table-hover">
			<tr>
				<td colspan="4">
					<h5>${SessionUser.name}알림</h5>
				</td>
			</tr>	
			<tr>
				<td>프로필사진</td>
				<td>이름</td>
				<td>내용</td>
				<td></td>
			</tr>
				<c:set var="num" value="${0}"/><!--각 폼에 -->
				
			<c:forEach var="list" items="${alarmlist}">
				
		<form name = "tempForm">
			<c:choose>
				<c:when test="${list.content==3}">
					<!--친구생일 조건문-->
					<c:set var="content" value="오늘은 ${list.name}님의 생일 입니다." />
					<c:set var="buttonNM" value="${list.name}님페이지가기" />
					<c:set var="href" value="${pageContext.request.contextPath}/friendpage.do?friendmail=${list.friendemail }&count=${list.count}" />
					<c:set var="hiddenNM" value="#" />
					<c:set var="hidden" value="${list.count}" />
					<c:set var="buttonimg" value="glyphicon glyphicon-heart-empty"/>
					<!-- 카운트 히든에 들어갈 값 -->
				</c:when>
				<c:when test="${list.content==2}">
					<!--친구수락 조건문-->
					<c:set var="content" value="${list.name}님이 친구요청 하였습니다." />
					<c:set var="buttonNM" value="친구수락" />
					<c:set var="href"
						value="${pageContext.request.contextPath}/friend/friendreqagree.do?friendID=${list.friendemail }&count=${list.count}" />
					<c:set var="hiddenNM" value="friendID" />
					<c:set var="hidden" value="${list.count}" />
					<c:set var="buttonimg" value="glyphicon glyphicon-user"/>
					<!-- 카운트 히든에 들어갈 값 -->
				</c:when>
				<c:when test="${list.content==1}">
					<!--친구수락 조건문-->
					<c:set var="content" value="${list.name}님이 새글을 작성 하였습니다." />
					<c:set var="buttonNM" value="${list.name}새글 보러가기" />
					<c:set var="href" value="#" />
					<c:set var="hiddenNM" value="#" />
					<c:set var="hidden" value="${list.count}" />
					<c:set var="buttonimg" value="glyphicon glyphicon-ok"/>
					<!-- 카운트 히든에 들어갈 값 -->
				</c:when>
			</c:choose>
			<input type = "hidden" name = "href" value = "${ href }">
		
		</form>
		<form action="${href}" method = "post" target = "_parent" onsubmit = "changeWindow();">
					<tr>
						<td><img src="${list.frimg}" width="100" height="100"></td>
						<td><a href="#">${list.name}</a></td>
						<td>${content}</td>
						<td><%-- <input type="submit" value="${buttonNM}"> --%>
						<button type="submit" class="btn btn-primary">
					<span class="${buttonimg}" aria-hidden="true"></span>${buttonNM}</button>
						<%-- <input type="button" value="" onclick ="changeWindow();"> --%>
						<input type="hidden" name="${hiddenNM}" value="${list.friendemail}" />
						<input type="hidden" name="count" value="${hidden}" /></td>
					</tr>
		</form>
			</c:forEach>
			<tr>
					
					<td colspan="3" align="center">
					<div class="center-block"><button type="button" class="btn btn-primary" onClick="closeWindow();">
					<span class="glyphicon glyphicon-home" aria-hidden="true"></span>홈</button>
					</div>
					</td>
					
			</tr>

		</table>
	</c:if>
	</div>
</body>
</html>