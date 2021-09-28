<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/mb_pf.css" type="text/css">
</head>
<body>
	<form action="dmwritePro.do" method="post">
		<table class="dm_table">
			<tr>
				<td>
					&nbsp;보내는 사람&nbsp;:&nbsp;${member_sender.nickname } (${member_sender.member_id })
					<input type="hidden" name="sender_id" value="${member_sender.member_id }">
				</td>
			</tr>
			<tr>
				<td>
					&nbsp;받는 사람&nbsp;:&nbsp;${member_receiver.nickname } (${member_receiver.member_id })
					<input type="hidden" name="receiver_id" value="${member_receiver.member_id }">
				</td>
			</tr>
			<tr>
				<td><textarea rows="20" cols="60" name="content" required="required"></textarea></td>
			</tr>
			<tr>
				<td class="table_line">
					<input type="submit" value="DM 전송">
					<input type="reset" value="다시작성">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>