<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
    <script>
      $(function(){
    	  opener.location.href = "${url}";
    	  window.close();
      });
    </script>

</head>
<body>
</body>
</html>