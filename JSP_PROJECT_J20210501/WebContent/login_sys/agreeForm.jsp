<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>약관 동의</title>
<link rel="stylesheet" href="css/agree.css" type="text/css" />
<script type="text/javascript">
	function chk() {
		if (document.getElementById("agree").checked == true) {

			return true;
		} else {
			alert("약관에 동의한 후 회원가입이 가능합니다.");

			return false;
		}
	}
</script>
</head>
<body>
	<div id="wrapper">
		<div>약관 동의</div>
		<br />
		<form action="singUpForm.do" name="frm" onsubmit="return chk()">
			<table>
				<tr>
					<td><textarea cols="60" rows="15" readonly="readonly">
--------------------------------------------------------------
1. 회원 정보는 웹 사이트의 운영을 위해서만 사용됩니다.
2. 웹 사이트의 정상 운영을 방해하는 회원은 탈퇴 처리합니다.
--------------------------------------------------------------
						</textarea></td>
				</tr>
			</table>
			<div>
				<p>약관에 동의합니다.<input type="checkbox" name="agree" id="agree" /></p>
				<input type="submit" value="회원가입" />
				<input type="button" value="취소" onclick="javascript:history.back();" />
			</div>
		</form>
	</div>
</body>
</html>