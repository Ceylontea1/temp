<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<title>login Page</title>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
<body>
<%@ include file = "../main/loginheader.jsp" %>
	
	<div class="container">
		<form action="${pageContext.request.contextPath}/login.do" method="post" name="frm">
			<table class="table table-bordered table-hover" style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th colspan="2"><h4> 로그인 </h4></th>
					</tr>
				</thead>
				<tr>
					<td style="width: 110px;"><h5>이메일(ID)</h5></td>
					<td style="width: 500px;"><input class="form-control" type="email" id="userEmail" name="userEmail" placeholder="이메일을 입력하세요." required></td>
				</tr>
				<tr>
					<td style="width: 110px;"><h5>비밀번호</h5></td>
					<td colspan="5"><input class="form-control" type="password" id="userPassword1" name="userPassword1" maxlength="20" placeholder="비밀번호를 입력하세요." required></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center">
						<button class="btn btn-primary" type="submit">로그인<i class="fa fa-check spaceLeft"></i></button>
						
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