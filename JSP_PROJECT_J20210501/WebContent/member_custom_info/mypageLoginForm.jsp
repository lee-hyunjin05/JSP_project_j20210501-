<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../view_part/header.jsp"%>
<%@ include file="../view_part/aside.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지 로그인</title>
<link rel="stylesheet" href="css/write_del_up.css" type="text/css" />
</head>
<body>
	<div id="wrapper">
		<section id="section">
			<form action="mypageLoginPro.do" method="post" name="frm">
			<h1>회원정보확인</h1>
			<p>
				<strong>${member.member_id }</strong> 님의 정보를 안전하게 보호하기 위해 비밀번호를 다시 한번 확인합니다.
			</p>
				<table class="del_table">
					<tr>
						<th>아이디</th>
						<td>${member.member_id }</td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td>
							<input type="password" name="pw" placeholder="비밀번호 입력" required="required" autofocus="autofocus"/>
							<input class="button" type="submit" value="확인" />
							<input class="button" type="reset" value="취소" onclick="javascript:history.back();" />	
						</td>
					</tr>
				</table>
		</form>
		</section>
	</div>
</body>
</html>
<%@ include file="../view_part/footer.jsp"%>