<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
				$('#blah').attr('src', e.target.result);
			}
	
			reader.readAsDataURL(input.files[0]);
		}
	}
        
	function closeWindow(){
		window.close();
	}
</script>

</head>

<body>
<form id="previewForm" action = "editprofileimage.do?email=${ loginUser.email }" method = "post" enctype="multipart/form-data">
	<table>
		<tr>
			<td>
				변경 전
			</td>
			<td>
				변경 후
			</td>
		</tr>
		<tr>
			<td>
				<img src = "${pageContext.request.contextPath}/${ loginUser.imagepath }" width = "350">
			</td>
			<td>				
				<img id="blah" src="#" width = "350">
			</td>
		</tr>
		<tr>
			<td colspan = "2" align = "right">
				변경할 이미지 선택
				<input type='file' id="imgInp" name = "newProfileImage">
			</td>
		</tr>
		<tr>
			<td colspan = "2" align = "right">
				<input type = "submit" value = "확인">
				<input type = "button" value = "취소" onclick = "closeWindow();">
			</td>
		</tr>
	</table>
</form>
</body>
</html>