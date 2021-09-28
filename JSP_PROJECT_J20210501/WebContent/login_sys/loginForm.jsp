<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet" href="css/login.css" type="text/css" />
</head>
<body>
	<div id="wrapper">
		<h3>
			<span>로그인</span>
		</h3>
		<hr>
		<form action="loginPro.do" method="post">
			<div class="login">
				<input type="text" id="input" name="id" placeholder="아이디" required="required" autofocus="autofocus"/><br />
				<input type="password" id="input" name="pw" placeholder="비밀번호" required="required" /><br />
				<input type="submit" class="button" value="로그인" />
				<a href="main.do" class="button">메인으로</a>
			</div>
		</form>
		<hr />
		<div class="group">
			<a href="findIDForm.do">아이디 찾기</a>
			|
			<a href="findPWForm.do">비밀번호 찾기</a>
			|
			<a href="agreeForm.do">회원가입</a>
		</div>
	</div>
</body>
</html>