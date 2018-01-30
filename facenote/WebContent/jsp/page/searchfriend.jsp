<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>친구 찾기 페이지</title>
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
function myFunction(){
	document.getElementById("search").submit();
}

</script>
</head>
<body>
<%@ include file = "/WEB-INF/Friend/RequestFriend.jsp" %>
<div class="container">
<c:set var="user" value="${user}"/><!-- USERID -->
<table class="table table-hover">
<form class="form-inline" action="${pageContext.request.contextPath}/friend/searchfriend.do" id="search" >
<tr><td>
	
	<input type="hidden" value="${user}" name="userID">
		<div class="form-group">
		<label for="name">이름으로 친구찾기 </label> <input type="text" class="form-control"  name="namesearch" value="" placeholder="친구의 이름를 적어주세요">
		<div class="pull-right">	
			<button type="button" class="btn btn-primary"onclick="myFunction()"><span class="glyphicon glyphicon-search">검색</span></button></div><br>
		</div>
</td></tr>
<tr><td>
	<div class="form-group">
	<label for="phone">전화번호로 친구찾기 </label> <input type="text" class="form-control" name="phonesearch" value="" placeholder="친구의 전화번호를 적어주세요">
			<div class="pull-right">
				<button type="button" class="btn btn-primary"onclick="myFunction()"><span class="glyphicon glyphicon-search">검색</span> </button><br>
			</div>
	</div>	
</td></tr>
<tr><td>
	<div class="form-group">
				<label for="id">아이디로 친구찾기</label> <input	type="text" class="form-control"  name="idsearch" value="" placeholder="친구의 아이디를 적어주세요">
		<div class="pull-right">			
				<button type="button" class="btn btn-primary"onclick="myFunction()"><span class="glyphicon glyphicon-search">검색</span> </button><br>
		</div>
	</div>
	
</td></tr>
</table>
</form>
	
	<table class="table table-hover">
	
	<tr>
		<td>친구사진</td><td>이름</td><td>아이디</td><td>친구요청</td>
    </tr>
 
  <c:if test="${listSize==0 }">
  
  <tr><td colspan="4">검색된 친구가 없습니다.</tr>
 

  </c:if>
  
  
  <c:if test="${listSize>0}">
   <c:forEach var="list" items="${list}">
  	<form action="${pageContext.request.contextPath}/friend/requestfriend.do" method="get">
    <tr>
    <td><input type="hidden" value="${list.email}"name="friendID"/>
     <img src= "${list.imagepath}" width="150"height="150"> </td>
     <td><a href="#">${list.name}</a></td><td>${list.email}</td>
     <td><input type="submit" value="친구신청" ></td>
    </tr>
 	</form>
    </c:forEach>
   
    </c:if>
    <form>	
	 <tr><td colspan="4"><button type="button" class="btn btn-primary" onClick="document.location.href='${pageContext.request.contextPath}/mypage.do'">
					<span class="glyphicon glyphicon-home" aria-hidden="true"></span>확인</button></td></tr>
	
</table>
</div>	
</body>
</html>