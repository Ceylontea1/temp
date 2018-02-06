<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="http://code.jquery.com/jquery-2.1.0.min.js"></script>
    
<script>

$(function() {
    $("#imgInp").on('change', function(){
        readURL(this);
    });
});

function readURL(input) {
    if (input.files && input.files[0]) {
    var reader = new FileReader();

    reader.onload = function (e) {
            $('#image').attr('src', e.target.result);
        }

      reader.readAsDataURL(input.files[0]);
      doDisplayImage();
    }
}

function doDisplayImage(){
    var con = document.getElementById("imagesection");
/*     if(con.style.display=='none'){ */
        con.style.display = 'block';
/*     }else{
        con.style.display = 'none';
    } */
}
$(function() {
    $("#reservationCheck").on('change', function(){
        readURL(this);
    });
});
function doDisplayReservationsection(){
	var section = document.getElementById("reservationsection");
	var check = document.getElementById("reservationCheck");
	if(check.checked){
		alert ( check.checked );
		section.style.display = 'block';
	}else{
		alert ( check.checked );
		section.style.dispaly = 'none';
	}
}


function checkvalue() { 

	if(document.frm1.content.value == "") {
		alert ( "내용이 없습니다.\n\n내용을 작성해 주세요." );
		return; 
	};
		document.frm1.submit(); 
} 



</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div style="align: center;">
	<h1>게시물 수정</h1>
		<form name="frm1" action="${pageContext.request.contextPath}/FaceNote/updateContentPro.do?pageemail=${ ConDto.email }&contentid=${ConDto.contentnum}" method="post" enctype="multipart/form-data">
			<input type="hidden" name="contentid" value="${ConDto.contentnum }">
			<input type="hidden" name="good" value="${ConDto.good}">
			<table border="1" style="text-align: center;">
				<tr>
					<td><img src="#">&nbsp;&nbsp;작성자 : ${ConDto.writer}</td>
					<td><input type="file" id="imgInp" value="사진 업로드" name="imagepath"></td>
				</tr>
				<c:if test="${ConDto.imagepath ne 'null'}">
					<tr>
						<td colspan="3" align="center">
							<img id="image" src="${pageContext.request.contextPath}/${ConDto.imagepath}" alt="your image" />그림 경로 >> ${ConDto.imagepath}
						</td>
					</tr>
				</c:if>
				<tr>
					<td colspan="3"><textarea name="content" rows="35" cols="100">${ConDto.content}</textarea></td>
				</tr>
				<tr>
					<td colspan="3">공개설정<select name="scope">
											<c:if test="${ConDto.scope eq '0'}">
												<option id="public" value="0" selected>전체공개</option>
												<option id="friend" value="1">친구만</option>
												<option id="private" value="2">비공개</option>
											</c:if>
											<c:if test="${ConDto.scope eq '1'}">
												<option id="public" value="0">전체공개</option>
												<option id="friend" value="1" selected>친구만</option>
												<option id="private" value="2">비공개</option>
											</c:if>
											<c:if test="${ConDto.scope eq '2'}">
												<option id="public" value="0" >전체공개</option>
												<option id="friend" value="1">친구만</option>
												<option id="private" value="2" selected>비공개</option>
											</c:if>
										</select>
					<input type="button" value="완료" onClick="checkvalue();">&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="취소" onClick="javascript:history.go(-1);">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>