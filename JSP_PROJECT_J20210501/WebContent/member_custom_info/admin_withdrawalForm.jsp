<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴 시키기</title>
</head>
<body>
	<form action="admin_withdrawalPro.do?id=${member.member_id }&pw=${member.member_pw }&wd=${member.withdrawal }" method="post">
		<c:if test="${member.withdrawal == 'N' }">
			<table>
				<tr>
					<td><strong>${member.member_id }</strong>를 탈퇴시키겠습니까?</td>
				</tr>
				<tr>
					<td><input type="submit" value="탈퇴" />
					<input type="reset" value="취소" onclick="javascript:window.close();" /></td>
				</tr>
			</table>
		</c:if>
		<c:if test="${member.withdrawal == 'Y' }">
			<table>
				<tr>
					<td><strong>${member.member_id }</strong>를 복구시키겠습니까?</td>
				</tr>
				<tr>
					<td><input type="submit" value="복구" />
					<input type="reset" value="취소" onclick="javascript:window.close();" /></td>
				</tr>
			</table>
		</c:if>
	</form>
</html>