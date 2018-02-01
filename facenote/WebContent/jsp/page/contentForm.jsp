<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.9.1.min.js"></script>

<script type="text/javascript">


/* $(document).ready(function () { 
	// 페이지 document 로딩 완료 후 스크립트 실행 
	$("#imagesection").click(function () { 
		status = $("#hidden").css("display"); 
		if (status == "none") { 
			$("#hidden").css("display", ""); 
		} else { 
			$("#hidden").css("display", "none"); 
		} 
	}); 
}); */

function modify(){
	
	var frm = document.getElementById("frm");
	var contentid = document.getElementById("contentid").value;
	alert("오냐?");
	frm.action = "${pageContext.request.contextPath}/FaceNote/updatecontent.do?contentid=" + contentid;
	frm.method = "post";
	frm.submit();	
}

function uplike(){
	var frm = document.getElementById("frm");

	/* frm.action = "${pageContext.request.contextPath}/FaceNote/upLike.do?contentid=${ConDto.contentid}" ; */
	frm.action = "${pageContext.request.contextPath}/FaceNote/upLike.do" ;
	frm.method = "post"
	frm.submit();
}

function writercomment(){
	
	var frm = document.getElementById("frm");
	
	frm.action = "${pageContext.request.contextPath}/FaceNote/writeReply.do" ;
	frm.method = "post"
	frm.submit();
}

</script>
<title>Content</title>
</head>
<body>
	<div align="center">
		<form id="frm">
			<input type="hidden" name="contentid" value="${ConDto.contentid}">
			<c:if test="${empty ConDto}">
				<h1>표시할 내용이 없습니다.</h1>
				<!-- <a href="javascript:history.go(-1);">이전페이지로</a> -->
				<a href="javascript:self.close();">창닫기</a>
			</c:if>
			<c:if test="${!empty ConDto}">
				페이지 E-mail : ${pageemail}<br>
				로그인 E-mail : ${loginemail}<br>
				<input type="hidden" name="contentid" value="${ConDto.contentid}">
				<table border="1" style="with: 500px; text-align: center;">
					<tr>
						<c:if test="${ConDto.email eq ConDto.writer }">
							<td><a href="#" target="_blank" title="${Writer.email}">${Writer.name}</a></td>
						</c:if>
						<c:if test="${ConDto.email ne ConDto.writer }">
							<td><a href="#" target="_blank" title="${Writer.email}">${Writer.name}</a>
								-> <a href="#" target="_blank" title="${HostEmail.email}">${HostEmail.name}</a></td>
						</c:if>
						<td>${ConDto.regdate}</td>
					</tr>
					<tr>
						<td colspan="2" align="center"><img src="${ConDto.imagepath}"></td>
					</tr>
					<tr>
						<td colspan="2">${ConDto.content}</td>
					</tr>
					<tr>
						<td>좋아요 : ${ConDto.good}<input type="hidden" name="likecnt" value="${ConDto.good}"></td>

						<td><a href="#" onClick="uplike()">좋아요</a></td>
					</tr>
					<tr>
						<td colspan="2">댓글</td>
					</tr>
					<c:if test="${Replycnt ne 0}">
						<c:forEach var="reply" items="${ReDtoList}">
							<tr>
								<td>${reply.writer}</td>
								<td>${reply.content}</td>
							</tr>
						</c:forEach>
					</c:if>
					<tr>
						<td colspan="3"><input type="text" name="reply">&nbsp;&nbsp;<input type="button" value="댓글 쓰기" onClick="writercomment()"></td>
					</tr>
					<tr>
						<td colspan="2">
						<c:if test="${ConDto.writer eq loginemail or ConDto.email eq loginemail }">
								<input type="button" name="mod" value="수정" onClick="modify()">&nbsp;&nbsp;
								<input type="button" name="del" value="삭제" onClick="#">&nbsp;&nbsp;
						</c:if>
								<input type="button" name="close" value="닫기" onClick="javascript:self.close();">
						</td>
					</tr>
				</table>
			</c:if>
		</form>
	</div>
</body>
</html>