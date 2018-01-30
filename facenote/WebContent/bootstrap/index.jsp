<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="../css/bootstrap.css">
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/bootstrap-theme.min.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script src="../js/bootstrap.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.6/angular.min.js"></script>

<style>
.navbar-inverse .navbar-brand{
	color:white;
}

.btn {
 	background-color: white;
 	border-color: white;
 	color: black;
}
.btn:hover {
	background-color: red;
}
</style>
</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="index.jsp">K 의 블로그</a>
		</div>
		
		
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="main.jsp">메인</a></li>
				<li><a href="bbs.jsp">게시판</a></li>
			</ul>
				<form class="navbar-form navbar-right" role="search">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="Search">
					</div>
					<button type="submit" class="btn btn-default">search</button>
				</form>
					<div class="navbar-form navbar-right">
						<button class="btn btn" data-target="#layerpop" data-toggle="modal">글쓰기</button><br/>
							<div class="modal fade" id="layerpop" >
							  <div class="modal-dialog">
							    <div class="modal-content">
							      <!-- header -->
							      <div class="modal-header">
							        <!-- 닫기(x) 버튼 -->
							        <button type="button" class="close" data-dismiss="modal">×</button>
							        <!-- header title -->
							        <h4 class="modal-title">Header</h4>
							      </div>
							      <!-- body -->
							      <div class="modal-body">
							            Body
							      </div>
							      <!-- Footer -->
							      <div class="modal-footer">
							        Footer
							        <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
							      </div>
							    </div>
							  </div>
							</div>
					</div>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">접속하기<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<!-- <li class="active"><a href="login.jsp">로그인</a> -->
						<li>
							<button class="btn btn-default" data-target="#layerpop" data-toggle="modal">로그인</button><br/>
								<div class="modal fade" id="layerpop" >
								  <div class="modal-dialog">
								    <div class="modal-content">
								      <!-- header -->
								      <div class="modal-header">
								        <!-- 닫기(x) 버튼 -->
								        <button type="button" class="close" data-dismiss="modal">×</button>
								        <!-- header title -->
								        <h4 class="modal-title">Header</h4>
								      </div>
								      <!-- body -->
								      <div class="modal-body">
								            Body
								      </div>
								      <!-- Footer -->
								      <div class="modal-footer">
								        Footer
								        <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
								      </div>
								    </div>
								  </div>
								</div>
						</li>
						<li><a href="join.jsp">회원가입</a></li>
					</ul>
					
				</li>
			</ul>
		</div>
	</nav>
	
	<div class="container">
		<div class="row">
			<div class="jumbotron">
				<h1>블로그</h1>
				<p>..............??</p>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-4 col-md-4 col-sm-6">
				<h2>bootstrap</h2>
				<p>UI 프레임 워크</p>
			</div>

			<div class="col-lg-4 col-md-4 col-sm-6">
				<h2>AngularJS</h2>
				<p>JavaScript SPA/MVC 프레임 워크</p>
			</div>

			<div class="col-lg-4 col-md-4 col-sm-6">
				<h2>Jquery</h2>
				<p>JavaScript 프레임 워크</p>
			</div>
			<div>

			</div>
		</div>
	</div>
	<hr />
	<hr />
	
	<div>
		<p>&copy; Demo Website</p>
	</div>
</body>
</html>