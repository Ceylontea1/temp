<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">

<!-- font awesome -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css" media="screen">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/custom.css">
<title>join Page</title>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
<script type="text/javascript">
	
	function registerCheckFunction() {
		var userEmail = $('#userEmail').val();
		$.ajax({
			type: 'POST',
			url: '../../UserRegisterCheckServlet',
			data: {
				userEmail: userEmail
				},
			success: whenSuccess
		});
	}
	function whenSuccess(result){
		if(result == 1){
			$('#checkMessage').html('사용할 수 있는 아이디입니다.');
			$('#checkType').attr('class', 'modal-content panel-success');
		} else {
			$('#checkMessage').html('사용할 수 없는 아이디입니다.');
			$('#checkType').attr('class', 'modal-content panel-warning');
		}
		$('#checkModal').modal("show");
	}
	
	function emailRegisterCheckFunction() {
		var userEmail = $('#userEmail').val();
		$.ajax({
			type: 'POST',
			url: '../../SendMail',
			// 동기화
			/* async   : false, */
			data: {
				userEmail: userEmail
				},
			success: function(data){
				alert('이메일 발송');
			}
		});
	}


		function emailCheck(userEmail) { 
			var regex=/([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
			return regex.test(userEmail);
		}
		
		function passwordCheck(userPassword1) {
			var regex = /^.*(?=.{8,20})(?=.*[0-9])(?=.*[a-zA-Z]).*$/;
			return regex.test(userPassword1);
		}
		
/* 		function birthCheck(userBirth){
			var regex =  ^(\d+)[/|\|\s]+[0|1](\d)[/|\|\s]+([0|1|2|3]\d)$;
			return regex.test(userBirth);
		} */
		
		function emailCheckFunction() {
			var userEmail = $('#userEmail').val();
			if(!emailCheck(userEmail)) {
				$('#userEmailError').html('이메일 형식이 올바르지 않습니다.');
			} else {
				$('#userEmailError').html('');
			}
		}
		
		function passwordCheckFunction() {
			var userPassword1 = $('#userPassword1').val();
			var userPassword2 = $('#userPassword2').val();
			if(!passwordCheck(userPassword1)) {
				$('#passwordCheckMessage').html('비밀번호 형식이 올바르지 않습니다.');
			} else if(userPassword1 != userPassword2) {
				$('#passwordCheckMessage').html('비밀번호가 일치하지 않습니다');
			} else {
				$('#passwordCheckMessage').html('');
			}
		}
		
/* 		function birthCheckFunction() {
			var userBirth = $('#userBirth').val();
			if(!birthCheck(userBirth)) {
				$('#birthCheckError').html('생년월일 형식이 올바르지 않습니다.');
			} else {
				$('#birthCheckError').html('');
			}
		} */
		
		function submitCheck(){
			var userPassword1 = $('#userPassword1').val();
			var userPassword2 = $('#userPassword2').val();
			var userEmail = $('#userEmail').val();
			if(!passwordCheck(userPassword1)) {
				return;
/* 			} else if(!emailCheck(userEmail)) {
				return; */
			} else {
				document.frm.submit();
			}
		}
	</script>
</head>
<body>
<%@ include file = "loginheader.jsp" %>
	<%
		String userEmail = null;
		if (session.getAttribute("userEmail") != null){
			userEmail = (String)session.getAttribute("userEmail");
		}

	%>
	<div class="container">
		<form action="${pageContext.request.contextPath}/join.do" method="post" name="frm">
			<table class="table table-bordered table-hover" style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th colspan="6"><h4>회원 등록</h4></th>
					</tr>
				</thead>
				<tr>
					<td style="width: 110px;"><h5>이메일(ID)</h5></td>
					<td style="width: 500px;"><input onkeyup="emailCheckFunction();" class="form-control" type="email" id="userEmail" name="userEmail" maxlength="20" placeholder="이메일을 입력하세요." required></td>
					<td style="width: 10px;"><button class="btn btn-success" onclick="registerCheckFunction();" type="button" >중복체크<i class="fa fa-mail-forward spaceLeft"></i></button></td>
					<td style="width: 10px;"><button class="btn btn-success" onclick="emailRegisterCheckFunction();" type="button">메일인증<i class="fa fa-mail-forward spaceLeft"></i></button></td>
				</tr>
				<tr>
					<td style="width: 110px;"><h5>비밀번호</h5></td>
					<td colspan="5"><input onkeyup="passwordCheckFunction();" class="form-control" type="password" id="userPassword1" name="userPassword1" maxlength="20" placeholder="비밀번호 (영문+숫자혼합 8자리 이상)" required></td>
				</tr>
				<tr>
					<td style="width: 110px;"><h5>비밀번호확인</h5></td>
					<td colspan="5"><input onkeyup="passwordCheckFunction();" class="form-control" type="password" id="userPassword2" name="userPassword2" maxlength="20" placeholder="비밀번호확인." required></td>
				</tr>
				<tr>
					<td style="width: 110px;"><h5>이름</h5></td>
					<td colspan="5"><input class="form-control" type="text" id="userName" name="userName" maxlength="20" placeholder="이름을 입력하세요." required></td>
				</tr>
				<tr>
					<td style="width: 110px;"><h5>생년월일</h5></td>
					<td colspan="5">						
						<!-- 생일 나중에 박스 형식으로 바꿔주세요. -->
						<input style="width: 60px; text-align: center;" type="text" id="userPhone1" name="birthYear" maxlength="4" placeholder="0000" required> 년
						<input style="width: 60px; text-align: center;" type="text" id="userPhone2" name="birthMonth" maxlength="2" placeholder="00" required> 월
						<input style="width: 60px; text-align: center;" type="text" id="userPhone3" name="birthDay" maxlength="2" placeholder="00" required> 일
					</td>
				</tr>
				<tr>
					<td style="width: 110px;"><h5>폰번호</h5></td>
					<td colspan="5">
						<div class="form-group" style="text-align: left; margin: 0 auto; margin-top: 4px;">
							<input style="width: 55px; text-align: center;" type="text" id="userPhone1" name="userPhone1" maxlength="5" placeholder="010" required> -
							<input style="width: 60px; text-align: center;" type="text" id="userPhone2" name="userPhone2" maxlength="5" placeholder="0000" required> -
							<input style="width: 60px; text-align: center;" type="text" id="userPhone3" name="userPhone3" maxlength="5" placeholder="0000" required>
						</div>
					</td>
				</tr>
				<tr>
					<td style="width: 110px;"><h5>성별</h5></td>
					<td colspan="5">
						<div class="form-group" style="text-align: left; margin: 0 auto;">
							<div class="btn-group" data-toggle="buttons">
								<label class="btn btn-primary active"> 
									<input type="radio" name="userGender" autocomplete="off" value="남자" checked>남자
								</label> 
								<label class="btn btn-primary"> 
									<input type="radio" name="userGender" autocomplete="off" value="여자">여자
								</label>
								<label class="btn btn-primary"> 
									<input type="radio" name="userGender" autocomplete="off" value="기타">기타
								</label>
							</div>
						</div>
				</tr>
				<tr>
					<td style="text-align: left" colspan="6">
					<div class="form-group">
						<label>약관 동의</label>
							<div data-toggle="buttons">
								<label class="btn btn-primary active">
									<span class="fa fa-check"></span><input id="agree" type="checkbox" autocomplete="off" checked>
								</label>
							<a href="#">이용약관</a>에 동의합니다.
						</div>
					</div>
					</td>
				</tr>
				<tr>
					<td colspan="6" style="text-align: center">
						<button class="btn btn-primary" type="submit" onclick="submitCheck();">회원가입<i class="fa fa-check spaceLeft"></i></button>
						<button class="btn btn-danger" onclick = "history.go(-1);">가입취소<i class="fa fa-times spaceLeft"></i></button>
					</td>
				</tr>
				<tr>
					<td style="text-align: center" colspan="6">
						<h5 style="color: red;" id="passwordCheckMessage"></h5>
						<h5 style="color: red;" id="userEmailError"></h5>
						<h5 style="color: red;" id="birthCheckError"></h5>
						<!-- <input class="btn btn-primary pull-right" type="button" value="등록" onclick="submitCheck();"> -->
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<%
	//messageContent라는 String이 있습니다
    //session에서 messageContent를 가져온 이후 그것이 null이 아니라면
    //session에서 가져온 messageContent를 현재 변수에 저장
    //messageType도 동일
	    String messageContent = null;
        if (session.getAttribute("messageContent") != null) {
            messageContent = (String) session.getAttribute("messageContent");
        }
        String messageType = null;
        if (session.getAttribute("messageType") != null) {
            messageType = (String) session.getAttribute("messageType");
        }
        if (messageContent != null) {
            //가져온 messageContent가 있다면 modal로 popup창을 만들어줘야함
    %>
    <div class="modal fade" id="messageModal" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="vertical-alignment-helper">
        <div class="modal-dialog vertical-align-center">
            <div class="modal-content
                <!-- div의 class에 지정해주는데 messageType에 따라서 modal색을 다르게 해주고 싶기 때문에 이렇게 코드를 짭니다 -->
                <%if (messageType.equals("오류 메시지"))
                    out.println("panel-warning");
                else
                    out.println("panel-success");%>">
                <div class="modal-header panel-heading">
                    <button type="button" class="close" data-dismiss="modal">
                        <span aria-hidden="true">×</span> <span class="sr-only">Close</span>
                    </button>
                    <h4 class="modal-title">
                        <%=messageType%>
                    </h4>
                </div>
                <div class="modal-body">
                    <%=messageContent%>
                </div>
            </div>
        </div>
        </div>
    </div>
    <script>
    //div class안의 messageModal
        $('#messageModal').modal("show");
    </script>
    <%
    //다 끝나면 Attribute를 삭제해줘야함
        session.removeAttribute("messageContent");
        session.removeAttribute("messageType");
        }
    %>
    <div class="modal fade" id="checkModal" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="vertical-alignment-helper">
        <div class="modal-dialog vertical-align-center">
            <div id="checkType" class="modal-content panel-info">
                <div class="modal-header panel-heading">
                    <button type="button" class="close" data-dismiss="modal">
                        <span aria-hidden="true">×</span> <span class="sr-only">Close</span>
                    </button>
                    <h4 class="modal-title">Check Message</h4>
                </div>
                <!-- 여기에 비밀번호가 일치하는지 하지 않는지를 실시간으로 나타내줌 -->
                <div class="modal-body" id="checkMessage"></div>
            </div>
        </div>
        </div>
    </div>
    


</body>
</html>