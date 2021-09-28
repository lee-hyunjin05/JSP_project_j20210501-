<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>닉네임 중복 확인</title>
<link rel="stylesheet" href="css/confirm.css" type="text/css" />
<script type="text/javascript">
	function wincl() {
		opener.document.frm.nickname.value = "${nickname }";
		window.close();
	}

	function chk() {
		if (!frm.nickname.value) {
			alert("닉네임을 입력하세요.");
			frm.id.focus();

			return false;
		}

		return true;
	}
</script>
</head>
<body>
	<c:if test="${result == 0 }">
		<p>입력한 아이디 : ${nickname }</p>
		<p>사용할 수 있는 닉네임입니다.</p>
		<input type="button" value="닫기" onclick="wincl()" />
	</c:if>
	<c:if test="${result != 0 }">
		<p>입력한 아이디 : ${nickname }</p>
		<p>이미 사용 중인 닉네임입니다.</p>
		<form name="frm" onsubmit="return chk()">
			<p>닉네임 : <input type="text" name="nickname" /></p>
			<div id="center"><input type="submit" value="확인" class="button"><input type="button" value="닫기" onclick="window.close()" class="button"></div>	
		</form>
	</c:if>
</body>
</html>