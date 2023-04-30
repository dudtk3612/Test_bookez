<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<script type="text/javascript">
		function checkData(){
			//PW check
			if(document.fr.pw.value == ""){
				alert("비밀번호를 입력하세요.");
				document.fr.pw.focus();
				return false;
			}// if end
		}// function end
	
	</script>
</head>
<body>
	<h1>UpdateForm(MVC).jsp</h1>
	<fieldset>
	<legend>Itwill Member Info</legend>
		<form action="./MemberUpdateProAction.me" method="post" name="fr" onsubmit="return checkData();">
			ID: <input type="text" name="id" value="${dto.id }" readonly><br>
			PW: <input type="password" name="pw" placeholder="비밀번호를 입력하세요."><br>
			Name: <input type="text" name="name" value="${dto.name }" ><br>
			Age: <input type="text" name="age" value="${dto.age }" ><br>
			E-mail: <input type="text" name="email" value="${dto.email }" readonly><br>
			<c:if test="${dto.gender eq '여' }">
				<c:set var="genWom" value="checked"/>
			</c:if>
			Gender: 
				Male<input type="radio" name="gender" value="남"
					<c:if test="${dto.gender eq '남' }">
						checked
					</c:if>
					>
				Female<input type="radio" name="gender" value="여" 
						<c:if test="${dto.gender eq '여' }">
							checked
						</c:if>
						><br>
			
		<input type="submit" value="UpdateInfo">
		<a href="./Main.me"><input type="button" value="돌아가기"></a>
		</form>
	</fieldset>

</body>
</html>