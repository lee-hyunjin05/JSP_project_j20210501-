<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../view_part/header.jsp"%>
<%@ include file="../view_part/aside.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴</title>
<link rel="stylesheet" href="css/mypageLogin.css" type="text/css" />
</head>
<body>
	<div id="wrapper">
		<section id="section">
		<h1>회원탈퇴</h1>
			<div>
				<form action="withdrawalPro.do?id=${member.member_id }" method="post">
					<p>
						<strong>${member.member_id }</strong><br />
						이 아이디의 탈퇴를 원하시면 비밀번호를 입력하세요.
					</p>
					<table class="content_table">
						<tr>
							<th>아이디</th>
							<td>${member.member_id }</td>
						</tr>
						<tr>
							<th>비밀번호</th>
							<td>
								<input type="password" name="pw" autofocus="autofocus"/>
								<input class="button" type="submit" value="탈퇴" />
								<input class="button" type="reset" value="취소" onclick="javascript:history.back();" />
							</td>
						</tr>
					</table>
				</form>
			</div>
		</section>
	</div>
</body>
</html>
<%@ include file="../view_part/footer.jsp"%>