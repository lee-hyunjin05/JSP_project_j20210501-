<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" href="css/signup.css" type="text/css" />
<script type="text/javascript">
	function winop_id() {
		if (!frm.id.value) {
			alert("아이디를 입력하세요.");
			frm.id.focus();
			
			return false;
		}
		
		window.open("confirmIDPro.do?id=" + frm.id.value, "", "width=500 height=200");
	}
	
	function winop_nick() {
		if (!frm.nickname.value) {
			alert("닉네임을 입력하세요.");
			frm.nickname.focus();
			
			return false;
		}
		
		window.open("confirmNickPro.do?nickname=" + frm.nickname.value, "", "width=500 height=200");
	}

	function chk() {
		if (frm.passwd.value != frm.repasswd.value) {
			alert("암호가 다릅니다.");
			frm.passwd.focus();

			return false;
		}

		return true;
	}
</script>
</head>
<body>
	<div id="wrapper">
		<h2>회원가입</h2>
		<form action="signUpPro.do" name="frm" onsubmit="return chk()">
			<table>
				<tr>
					<th>아이디</th>
					<td>
						<input type="text" name="id" required="required" id="id"/>
						<input type="button" value="중복체크" required="required" onclick="winop_id()" id="button"/>
					</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="pw" required="required" /></td>
				</tr>
				<tr>
					<th>비밀번호 확인</th>
					<td><input type="password" name="repasswd" required="required" /></td>
				</tr>
				<tr>
					<th>이름</th>
					<td><input type="text" name="name" required="required" /></td>
				</tr>
				<tr>
					<th>성별</th>
					<td>
						남자<input type="radio" name="gender" value="남자" />
						여자<input type="radio" name="gender" value="여자" />
					</td>
				</tr>
				<tr>
					<th>닉네임</th>
					<td>
						<input type="text" name="nickname" required="required" id="nickname"/>
						<input type="button" value="중복체크" onclick="winop_nick()" required="required" id="button"/>
					</td>
				</tr>
				<tr>
					<th>생년월일</th>
					<td>
						<select name="year">
							<c:forEach var="i" begin="1930" end="2030" step="1">
								<option value="${i }" ${i == 1990 ? "selected='selected'" : '' } >${i }</option>
							</c:forEach>
						</select>
						<select name="month">
							<c:forEach var="i" begin="1" end="12" step="1">
								<option value="${i }">${i }</option>
							</c:forEach>
						</select>
						<select name="day">
							<c:forEach var="i" begin="1" end="31" step="1">
								<option value="${i }">${i }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th>전화번호</th>
					<td>
						<input type="tel" name="tel1" pattern="\d{2, 3}" maxlength="3" id="tel" />
						-
						<input type="tel" name="tel2" pattern="\d{3, 4}" maxlength="4" id="tel" />
						-
						<input type="tel" name="tel3" pattern="\d{4}" maxlength="4" id="tel" />
					</td>
				</tr>
				<tr>
					<th>자기소개</th>
					<td><textarea name="intro" cols="30" rows="10"></textarea></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: right;">
						<input type="submit" value="확인" id="confirm"/><br>
						<input type="reset" value="취소" onclick="javascript:history.back();" id="cancle"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>