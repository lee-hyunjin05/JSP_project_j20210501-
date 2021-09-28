<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/findid.css" type="text/css" />
<title>아이디 찾기</title>
</head>
<body>
	<div id="wrapper">
		<h3>
			<span>아이디 찾기</span>
		</h3>
		<hr>
		<form action="findIDPro.do" name="frm">
			<table class="find">
				<tr>
					<th>이름</th>
					<td><input type="text" id="input" name="name" placeholder="이름" required="required" autofocus="autofocus" size="22" /></td>
				</tr>
				<tr>
					<th>생년월일</th>
					<td><input type="text" id="input" name="birth" placeholder="6자리 입력" required="required" maxlength="6" size="22" /></td>
				</tr>
				<tr>
					<th>전화번호</th>
					<td>
						<input type="tel" name="tel1" pattern="\d{2, 3}" maxlength="3" id="tel" />
						-
						<input type="tel" name="tel2" pattern="\d{3, 4}" maxlength="4"  id="tel" />
						-
						<input type="tel" name="tel3" pattern="\d{4}" maxlength="4"  id="tel" />
					</td>
					
				</tr>
				<tr >
					<td colspan="2">
						<input type="submit" class="button" value="아이디 찾기" />
						<input type="reset" value="취소" onclick="javascript:history.back();" id="cancel"/>
					</td>
				</tr>
			</table>
		</form>
		<hr />
	</div>
</body>
</html>