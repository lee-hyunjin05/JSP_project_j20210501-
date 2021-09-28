<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../view_part/header.jsp"%>
<%@ include file="../view_part/aside.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<link rel="stylesheet" href="css/post_style1.css" type="text/css" />
</head>
<body>
	<div id="wrapper">
		<section id="section">
			<div>
				<table class="post_list">
					<tr>
						<td>
							<h1>기본정보</h1>
							<p>
								프로필사진, 이름, 이메일을 변경합니다.
								<a href="reInfoForm.do"><input type="submit" value="수정" class="button"></a>
							</p>
						</td>
					</tr>
					<tr>
						<td>
							<h1>비밀번호변경</h1>
							<p>
								비밀번호를 변경합니다.
								<a href="rePasswdForm.do?id=${member.member_id }"><input type="submit" value="수정" class="button"></a>
							</p>
						</td>
					</tr>
					<tr>
						<td>
							<h1>회원탈퇴</h1>
							<p>
								회원을 탈퇴합니다.
								<a href="withdrawalForm.do?id=${member.member_id }"><input type="submit" value="탈퇴" class="button"></a>
							</p>
						</td>
					</tr>
				</table>
			</div>
		</section>
	</div>
</body>
</html>
<%@ include file="../view_part/footer.jsp"%>