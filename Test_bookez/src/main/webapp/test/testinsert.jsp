<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원가입 테스트 페이지</h1>
	<form action="./TestJoinAction.ez" method="get">
		  	 아이디 : <input type="text" name="user_id"><br>
		  	 비밀번호 : <input type="password" name="pw"><br>
		  	 이름 : <input type="text" name="name"><br>
		  	 닉네임 : <input type="text" name="nickname"><br>
		  	 전화번호 : <input type="text" name="tel"><br>
		  	 이메일 : <input type="text" name="email"> <br>
		  	 프로필 사진 : <input type="text" name="photo"> <br>
		  	 주소 : <input type="text" name="address"> <br>
		  	 <hr>
		  	 <input type="submit" value="회원가입">		  	 
		  </form>	  	
</body>
</html>