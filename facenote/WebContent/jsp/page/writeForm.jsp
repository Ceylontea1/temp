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
/* test이다 */
$(function() {
    $("#imgInp").on('change', function(){
        readURL(this);
    });
});

function readURL(input) {
    if (input.files && input.files[0]) {
    	alert ( input.files );
    	alert ( input.files[0] );
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
<%@ include file = "../main/header.jsp" %>
	<div style="align: center;">
		<h1 align="center">게시물 작성</h1>
		<form name="frm1" action="${pageContext.request.contextPath}/FaceNote/writerContentPro.do?pageemail=${ pageUser.email }" method="post" enctype="multipart/form-data">		
			<table border="1" style="text-align: center;">
				<tr>
				<c:if test="${loginUser.email eq pageUser.email}">
					<td><img src="${loginUser.imagepath}">&nbsp;&nbsp;${loginUser.name}</td>
				</c:if>
				<c:if test="${loginUser.email ne pageUser.email}">
					<td><img src="${loginUser.imagepath}">&nbsp;&nbsp;${loginUser.name} -> <img src="${pageUser.imagepath}">&nbsp;&nbsp;${pageUser.name}</td>
				</c:if>
				<td><input type="file" id="imgInp" value="사진 업로드" name="imagepath" ></td>
				<tbody id="imagesection" style="display:none">
				<tr align="center">
					<td colspan="3" >
						<img id="image" src="#" alt="your image" />
					</td>
				</tr>
				</tbody>
				<tr>
					<td colspan="2"><textarea name="content" rows="35" cols="100"></textarea></td>
				</tr>
				<tr>
					<td colspan="2">공개설정<select name="scope">
							<option value="0">전체공개</option>
							<option value="1">친구만</option>
							<option value="2">비공개</option>
					</select> <input type="button" value="글쓰기" onClick="checkvalue();">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>