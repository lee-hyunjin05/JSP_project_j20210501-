<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../view_part/header.jsp"%>
<%@ include file="../view_part/aside.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 변경</title>
<link rel="stylesheet" href="css/post_style1.css" type="text/css" />
</head>
<body>
	<div id="wrapper">
		<section id="section">
		<h1>비밀번호 변경</h1>
			<div>
				<form action="rePasswdPro.do?id=${member.member_id }" method="post">
					<table class="post_list">
						<tr>
							<th>현재 비밀번호</th>
							<td><input type="password" name="cur_pw" autofocus="autofocus"/></td>
						</tr>
						<tr>
							<th>새 비밀번호</th>
							<td><input type="password" name="new_pw1" /></td>
						</tr>
						<tr>
							<th>비밀번호 확인</th>
							<td><input type="password" name="new_pw2" /></td>
						</tr>
						<tr>
							<td colspan="2"><input type="submit" value="수정" class="button"/>
								<input type="reset" value="취소" onclick="javascript:history.back();" class="button"/>
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