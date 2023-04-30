<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<script type="text/javascript">
	// 아이디 / 비밀번호 오류 확인하는 함수 작성
	</script>	
</head>
<body>
	<h1>loginForm.jsp (MVC)</h1>
	<fieldset>
	<legend>Itwill login</legend>
		<form action="./MemberLoginAction.me" method="post">
		<!-- 이전페이지의 정보를 전달한다 ! -->
<%-- 		<input type="hidden" name="oldURL" value="<%=request.getParameter("oldURL") %>"> --%>
			ID: <input type="text" name="id"> <br>
			PW: <input type="password" name="pw"> <br>
			<hr>
			<input type="submit" value="Login">
			<input type="button" value="Sign in" onclick="location.href='MemberJoin.me'">
		</form>
	</fieldset>

</body>
</html>